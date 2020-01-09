package org.yjn.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yjn.mis.model.User;
import org.yjn.mis.service.HostHolder;

@Controller
public class UserController {
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/operation/user"}, method = {RequestMethod.GET})
    public String OperationUser(Model model) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        return "operation/user";
    }
}
