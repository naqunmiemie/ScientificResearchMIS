package org.yjn.mis.service;


import org.yjn.mis.dao.UserDAO;
import org.yjn.mis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  @Autowired
  private UserDAO userDAO;

  public int addUser(User user){
    return userDAO.addUser(user);
  }

  public User getUser(String email) {
    return userDAO.selectByEmail(email);
  }

  public User getUser(int uid) {
    return userDAO.selectById(uid);
  }
}
