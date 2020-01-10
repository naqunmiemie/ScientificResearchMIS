package org.yjn.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yjn.mis.model.User;
import org.yjn.mis.service.HostHolder;

@Controller
public class AdminController {
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/operation/admin"}, method = {RequestMethod.GET})
    public String OperationAdmin(Model model) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }else {
            return "/index";
        }
        return "operation/admin";
    }
}
