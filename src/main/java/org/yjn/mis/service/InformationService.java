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
