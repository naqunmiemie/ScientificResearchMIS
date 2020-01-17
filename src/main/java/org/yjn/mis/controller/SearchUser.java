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
public class SearchUser {
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private InformationService informationService;

    @RequestMapping(path = {"/user/normalSearch/do"}, method = {RequestMethod.POST})
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
        return "operation/user";
    }

    @RequestMapping(path = {"/user/advancedSearch"}, method = {RequestMethod.GET})
    public String advancedSearch() {
        return "advancedSearch/user";
    }

    @RequestMapping(path = {"/user/advancedSearch/do"}, method = {RequestMethod.POST})
    public String doAdvancedSearch(
            Model model,
            @RequestParam("university") String university,
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("title") String title,
            @RequestParam("mailbox") String mailbox,
            @RequestParam("college") String college,
            @RequestParam("researchDirection") String researchDirection
    ) {
        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadAdvancedSearchInformationView(model,university,name,phoneNumber,
                title,mailbox,college,researchDirection);
        return "operation/user";
    }

    private void loadNormalSearchInformationView(Model model,String options,String keyword) {
        model.addAttribute("information", informationService.normalSearch(options,keyword));
    }

    private void loadAdvancedSearchInformationView(Model model,String university,String name,String phoneNumber,
                                                   String title,String mailbox,String college,String researchDirection) {
        model.addAttribute("information", informationService.advancedSearch(university,name,phoneNumber,
                title,mailbox,college,researchDirection));
    }
}
