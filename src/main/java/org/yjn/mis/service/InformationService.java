package org.yjn.mis.service;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yjn.mis.dao.InformationDao;
import org.yjn.mis.model.Information;

import java.util.List;

@Service
public class InformationService {

    @Autowired
    private InformationDao informationDao;

    public List<Information> getInformation() {
        return informationDao.select();
    }

    public List<Information> getIdInformation(int id) {
        return informationDao.selectId(id);
    }

    public List<Information> normalSearch(String options, String keyword) {
        if (options.equals("id")){
            return informationDao.normalSearch(options,keyword);
        }else {
            return informationDao.normalSearch(options,dispose(keyword));
        }
    }

    public List<Information> advancedSearch(String university,String name,String phoneNumber,
                                            String title,String mailbox,String college,String researchDirection){
        return informationDao.advancedSearch(dispose(university),dispose(name),dispose(phoneNumber),
                dispose(title),dispose(mailbox),dispose(college),dispose(researchDirection));
    }

    private String dispose(String word){
        String[] keywordList = word.split(" ");
        StringBuilder sb = new StringBuilder("%");
        for (String i:keywordList){
            sb.append(i).append("%");
        }
        return sb.toString();
    }

    public void addInformation(Information information) {
        informationDao.add(information);
    }

    public void changeState(int id, int state) {
        state = Math.abs(state - 1);
        informationDao.changeState(id,state);
    }

    public void changeInformation(Information information) {
        informationDao.change(information);
    }
}
