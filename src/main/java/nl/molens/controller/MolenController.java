package nl.molens.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MolenController {

  @RequestMapping(value = { "/addMolen", "/login" })
  public  String addMolen(Model model) throws IOException {
    model.addAttribute("loggedIn", LoginController.isLoggedIn());
    return "index";
  }

  @RequestMapping("/")
  public String viewMolens(Model model) {
    model.addAttribute("loggedIn", LoginController.isLoggedIn());
    return "index";
  }


}
