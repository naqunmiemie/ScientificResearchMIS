package org.yjn.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yjn.mis.model.User;
import org.yjn.mis.service.HostHolder;
import org.yjn.mis.service.InformationService;

@Controller
public class UserController {
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private InformationService informationService;

    @RequestMapping(path = {"/operation/user"}, method = {RequestMethod.GET})
    public String OperationUser(Model model) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }else {
            return "/index";
        }
        loadInformationView(model);
        return "operation/user";
    }

    @RequestMapping(path = {"/details/{informationId}/user"}, method = {RequestMethod.GET})
    public String DetailsUser(Model model,@PathVariable("informationId") int informationId) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }else {
            return "/index";
        }
        loadIdInformationView(model,informationId);
        return "details/user";
    }

    private void loadInformationView(Model model) {
        model.addAttribute("information", informationService.getInformation());
    }

    private void loadIdInformationView(Model model,int id) {
        model.addAttribute("information", informationService.getIdInformation(id));
    }

}
