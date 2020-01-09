package org.yjn.mis.service;

import org.yjn.mis.model.User;
import org.yjn.mis.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {

  public User getUser() {
    return ConcurrentUtils.getHost();
  }

  public void setUser(User user) {
    ConcurrentUtils.setHost(user);
  }
}
