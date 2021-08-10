package nl.molens.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nl.molens.MolenUtils;
import nl.molens.MolenUtils.Dimensions;
import nl.molens.model.Molen;
import nl.molens.model.bs.MolenBS;
import nl.molens.model.bs.MolenBSResponse;
import nl.molens.model.bs.MolenRequest;
import nl.molens.service.MolenService;

@RestController
@RequestMapping("/api")
public class MolenAPIController {

  @Autowired MolenService molenService;

  @GetMapping("/molens/{page}/{molensPerPage}")
  public List<MolenBS> getMolenBSBetween(@PathVariable int page,@PathVariable int molensPerPage, @RequestParam( name = "query", required = false )String query)
  {
    if (query==null)
    return molenService.getMolenBSList(page*molensPerPage, molensPerPage);
    else
      return molenService.getMolenBSList(page*molensPerPage, molensPerPage,query);
  }

  @PostMapping({"/crud/molen/updateMolen", "/crud/molen/addMolen"})
  @ResponseBody
  public ResponseEntity<Object> updateMolen(@RequestBody MolenRequest molenRequest)
  {
    ResponseEntity<Object> response;

    var dimensions = (Dimensions)MolenUtils.session().getAttribute("newPhoto.dimensions");
    var contentType = (String)MolenUtils.session().getAttribute("newPhoto.contentType");
    var requestId = (String)MolenUtils.session().getAttribute("newPhoto.requestId");
    var molen = molenRequest.getMolen();
    if (dimensions!=null && molenRequest.getRequestId().equals(requestId))
    {
      MolenBSResponse molenBSResponse = new MolenBSResponse();
      try {
        molen.setFoto(new SerialBlob(IOUtils.toByteArray(dimensions.getInputStream())));
        molen.setFotoContentType(contentType);
        molen.setFotoHeight(dimensions.getCorrectedHeight());
        molen.setFotoWidth(dimensions.getCorrectedWidth());
        MolenUtils.session().setAttribute("newPhoto.dimensions",null);
        molen = molenService.addOrUpdateMolen(molen);
        MolenBS molenBS = new MolenBS();
        molenBS.cloneFromMolen(molen);
        molenBSResponse.setMolen(molenBS);
        molenBSResponse.setStatus("OK");
      } catch (SQLException | IOException e) {
        e.printStackTrace();
        molenBSResponse.setStatus("ERROR");
      }
      response = new ResponseEntity<>(molenBSResponse, HttpStatus.OK);
    }
    else{
      MolenBSResponse molenBSResponse = new MolenBSResponse();
      molen = molenService.addOrUpdateMolen(molen);
      MolenBS molenBS = new MolenBS();
      molenBS.cloneFromMolen(molen);
      molenBSResponse.setMolen(molenBS);
      molenBSResponse.setStatus("OK");
      response = new ResponseEntity<>(molenBSResponse, HttpStatus.OK);
      if (dimensions!=null)
      {
        MolenUtils.session().setAttribute("newPhoto.dimensions",null);
      }
    }
    return response;
  }

  @PostMapping("/crud/molen/deleteMolen")
  public String deleteMolen(@RequestBody Molen molen)
  {
    String response = "{\"status\":\"OK\"}";
    molenService.deleteMolen(molen);
    return response;
  }

  @PostMapping("/crud/molen/uploadFoto")
  public String uploadMolenFoto(@RequestParam("file") MultipartFile file, @RequestParam("type") String contentType,@RequestParam("requestId") String requestId,
  RedirectAttributes redirectAttributes)
  {

    try {
      Dimensions dimensions = MolenUtils.getImageDimensions(file.getInputStream(), contentType);
      MolenUtils.session().setAttribute("newPhoto.dimensions", dimensions);
      MolenUtils.session().setAttribute("newPhoto.contentType", contentType);
      MolenUtils.session().setAttribute("newPhoto.requestId", requestId);
    } catch (IOException e) {
      e.printStackTrace();
      return "{\"status\":\"ERROR\"}";
    }
    return "{\"status\":\"OK\"}";
  }

}
