package nl.molens.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.molens.model.Molen;
import nl.molens.service.MolenService;

@Controller
@RequestMapping(value="/fotos")
public class MolenFotoController
{

  private @Autowired MolenService molenService;
//, @PathVariable(value="refreshValue", required = false) String refreshValue

  @GetMapping("/molenfoto/{id}")
  private @ResponseBody void getMolenFoto(HttpServletResponse response, @PathVariable("id") int id)
  {
    Molen molen = molenService.getMolenById(id);
    try {

      if (molen!=null)
        {
          response.setContentType(molen.getFotoContentType());
          IOUtils.copy(molen.getFoto().getBinaryStream(), response.getOutputStream());
        }
      }
      catch (SQLException | IOException  e)
      {
        e.printStackTrace();
      }

  }
}
