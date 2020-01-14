package org.yjn.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yjn.mis.model.Information;
import org.yjn.mis.model.User;
import org.yjn.mis.service.HostHolder;
import org.yjn.mis.service.InformationService;

@Controller
public class AdminController {
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private InformationService informationService;

    @RequestMapping(path = {"/admin/operation"}, method = {RequestMethod.GET})
    public String OperationAdmin(Model model) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadInformationView(model);
        return "operation/admin";
    }

    @RequestMapping(path = {"/admin/details/{informationId}"}, method = {RequestMethod.GET})
    public String DetailsUAdmin(Model model,@PathVariable("informationId") int informationId) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadIdInformationView(model,informationId);
        return "details/admin";
    }

    @RequestMapping(path = {"/admin/add"}, method = {RequestMethod.GET})
    public String add() {
        return "add";
    }

    @RequestMapping(path = {"/admin/add/do"}, method = {RequestMethod.POST})
    public String doAdd(
            Model model,
            @RequestParam("university") String university,
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("title") String title,
            @RequestParam("mailbox") String mailbox,
            @RequestParam("college") String college,
            @RequestParam("lab") String lab,
            @RequestParam("resume") String resume,
            @RequestParam("educationExperience") String educationExperience,
            @RequestParam("workExperience") String workExperience,
            @RequestParam("researchDirection") String researchDirection,
            @RequestParam("courses") String courses,
            @RequestParam("scientificResearchItem") String scientificResearchItem,
            @RequestParam("monograph") String monograph,
            @RequestParam("patent") String patent,
            @RequestParam("awards") String awards
    ) {
        if (university.length() == 0){
            model.addAttribute("error", "未完成基本信息-学校");
            return "404";
        }
        if (name.length() == 0){
            model.addAttribute("error", "未完成基本信息-姓名");
            return "404";
        }
        Information information = new Information();
        information.setUniversity(university);
        information.setName(name);
        information.setPhoneNumber(phoneNumber);
        information.setTitle(title);
        information.setMailbox(mailbox);
        information.setCollege(college);
        information.setLab(lab);
        information.setResume(resume);
        information.setEducationExperience(educationExperience);
        information.setWorkExperience(workExperience);
        information.setResearchDirection(researchDirection);
        information.setCourses(courses);
        information.setScientificResearchItem(scientificResearchItem);
        information.setMonograph(monograph);
        information.setPatent(patent);
        information.setAwards(awards);
        informationService.addInformation(information);

        return "redirect:/admin/operation";
    }

    @RequestMapping(path = {"/admin/state/{informationId}/{informationState}"}, method = {RequestMethod.GET})
    public String changeState(
            @PathVariable("informationId") int informationId,
            @PathVariable("informationState") int informationState
    ) {
        informationService.changeState(informationId,informationState);
        return "redirect:/admin/details/{informationId}";
    }

    @RequestMapping(path = {"/admin/change/{informationId}"}, method = {RequestMethod.GET})
    public String change(Model model,@PathVariable("informationId") int informationId) {
        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadIdInformationView(model,informationId);
        return "change";
    }

    @RequestMapping(path = {"/admin/change/do/{informationId}"}, method = {RequestMethod.POST})
    public String doChange(
            Model model,
            @PathVariable("informationId") int id,
            @RequestParam("university") String university,
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("title") String title,
            @RequestParam("mailbox") String mailbox,
            @RequestParam("college") String college,
            @RequestParam("lab") String lab,
            @RequestParam("resume") String resume,
            @RequestParam("educationExperience") String educationExperience,
            @RequestParam("workExperience") String workExperience,
            @RequestParam("researchDirection") String researchDirection,
            @RequestParam("courses") String courses,
            @RequestParam("scientificResearchItem") String scientificResearchItem,
            @RequestParam("monograph") String monograph,
            @RequestParam("patent") String patent,
            @RequestParam("awards") String awards
    ) {
        if (university.length() == 0){
            model.addAttribute("error", "未完成基本信息-学校");
            return "404";
        }
        if (name.length() == 0){
            model.addAttribute("error", "未完成基本信息-姓名");
            return "404";
        }
        Information information = new Information();
        information.setId(id);
        information.setUniversity(university);
        information.setName(name);
        information.setPhoneNumber(phoneNumber);
        information.setTitle(title);
        information.setMailbox(mailbox);
        information.setCollege(college);
        information.setLab(lab);
        information.setResume(resume);
        information.setEducationExperience(educationExperience);
        information.setWorkExperience(workExperience);
        information.setResearchDirection(researchDirection);
        information.setCourses(courses);
        information.setScientificResearchItem(scientificResearchItem);
        information.setMonograph(monograph);
        information.setPatent(patent);
        information.setAwards(awards);
        informationService.changeInformation(information);

        return "redirect:/admin/details/{informationId}";
    }

    private void loadInformationView(Model model) {
        model.addAttribute("information", informationService.getInformation());
    }

    private void loadIdInformationView(Model model,int id) {
        model.addAttribute("information", informationService.getIdInformation(id));
    }
}
