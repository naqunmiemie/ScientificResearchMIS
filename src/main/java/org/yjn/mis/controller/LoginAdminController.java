package org.yjn.mis.controller;

import org.yjn.mis.biz.LoginBiz;
import org.yjn.mis.service.UserService;
import org.yjn.mis.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginAdminController {

    @Autowired
    private LoginBiz loginBiz;

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/admin/login"}, method = {RequestMethod.GET})
    public String login() {
        return "login/admin";
    }

    @RequestMapping(path = {"/admin/login/do"}, method = {RequestMethod.POST})
    public String doLogin(
            Model model,
            HttpServletResponse response,
            @RequestParam("account") String account,
            @RequestParam("password") String password
    ) {
        try {
            String t = loginBiz.AdminLogin(account, password);
            CookieUtils.writeCookie("t", t, response);
            return "redirect:/operation/admin";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path = {"/admin/logout/do"}, method = {RequestMethod.GET})
    public String doLogout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        CookieUtils.removeCookie("t",request,response);
        return "redirect:/index";

    }
}
