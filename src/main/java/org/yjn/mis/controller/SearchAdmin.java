package org.yjn.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yjn.mis.model.User;
import org.yjn.mis.service.HostHolder;
import org.yjn.mis.service.InformationService;

@Controller
public class SearchAdmin {
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private InformationService informationService;

    @RequestMapping(path = {"/admin/normalSearch/do"}, method = {RequestMethod.POST})
    public String normalSearch(
            Model model,
            @RequestParam("options") String options,
            @RequestParam("keyword") String keyword
    ) {
        if (options.equals("0")){
            model.addAttribute("error", "请选择搜索关键词");
            return "404";
        }
        if (keyword.length() == 0){
            model.addAttribute("error", "请填写搜索关键词");
            return "404";
        }

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadNormalSearchInformationView(model,options,keyword);
        return "operation/admin";
    }

    private void loadNormalSearchInformationView(Model model,String options,String keyword) {
        model.addAttribute("information", informationService.normalSearch(options,keyword));
    }
}