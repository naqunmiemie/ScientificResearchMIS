package org.yjn.mis.controller;

import org.yjn.mis.biz.LoginBiz;
import org.yjn.mis.model.User;
import org.yjn.mis.utils.CookieUtils;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginUserController {

  @Autowired
  private LoginBiz loginBiz;

  @RequestMapping(path = {"/user/register"}, method = {RequestMethod.GET})
  public String register() {
    return "login/register";
  }

  @RequestMapping(path = {"/user/register/do"}, method = {RequestMethod.POST})
  public String doRegister(
      Model model,
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("password") String password
  ) {

    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);

    try {
      loginBiz.register(user);
      return "redirect:/index";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "404";
    }
  }

  @RequestMapping(path = {"/user/login"}, method = {RequestMethod.GET})
  public String login() {
    return "login/user";
  }

  @RequestMapping(path = {"/user/login/do"}, method = {RequestMethod.POST})
  public String doLogin(
          Model model,
          HttpServletResponse response,
          @RequestParam("email") String email,
          @RequestParam("password") String password
  ) {
    try {
      String t = loginBiz.login(email, password);
      CookieUtils.writeCookie("t", t, response);
      return "redirect:/user/operation";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "404";
    }
  }

  @RequestMapping(path = {"/user/logout/do"}, method = {RequestMethod.GET})
  public String doLogout(
      @CookieValue("t") String t
  ) {

    loginBiz.logout(t);
    return "redirect:/index";

  }
}
