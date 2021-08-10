package nl.molens.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

  //@RequestMapping(value="login", method = RequestMethod.GET)
  public String loginGet(@RequestParam(required = false) String error) {
     return "index";
  }

  @RequestMapping(value="login") // , method = RequestMethod.POST
  public String loginPost(@RequestBody(required = false) String string, @RequestParam(required = false) String error) {
    if (error != null)
      return "{\"return\" : \"ERROR\"}";
    else
      return "{\"return\" : \"OK\"}";
  }

  @RequestMapping("loginError")
  public String loginError(@RequestBody(required = false) String string) {
    return "{\"return\" : \"ERROR\"}";
  }

  @RequestMapping("loginStatus")
  public String loginStatus(@RequestBody(required = false) String string) {
    if (isLoggedIn()) {
      return "{\"loggedIn\" : \"true\"}";
    } else {
      return "{\"loggedIn\" : \"false\"}";
    }
  }

  @RequestMapping(value="csrf", method = RequestMethod.POST)
  public String getCsrfToken(HttpServletRequest request){
    CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
    return "{\"token\" : \""+token.getToken()+"\" ,\n\"headerName\" : \""+token.getHeaderName()+"\"}";
  }

  public static boolean isLoggedIn() {
    if(SecurityContextHolder.getContext().getAuthentication() != null)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return (name!=null && !name.equals("anonymousUser"));
    }
    return false;
  }
}

