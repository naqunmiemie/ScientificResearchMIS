package org.yjn.mis.interceptor;

import org.yjn.mis.model.Ticket;
import org.yjn.mis.model.User;
import org.yjn.mis.service.TicketService;
import org.yjn.mis.service.UserService;
import org.yjn.mis.utils.ConcurrentUtils;
import org.yjn.mis.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {

  @Autowired
  private TicketService ticketService;

  @Autowired
  private UserService userService;

  /**
   * 为注入host信息
   *
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String t = CookieUtils.getCookie("t", request);
    if (!StringUtils.isEmpty(t)) {
      if (t.equals("admin")){
        User admin = new User();
        admin.setId(0);
        admin.setName("管理员");
        admin.setEmail("admin");
        admin.setPassword("admin");
        ConcurrentUtils.setHost(admin);
      }else {
        Ticket ticket = ticketService.getTicket(t);
        if (ticket != null && ticket.getExpiredAt().after(new Date())) {
          User host = userService.getUser(ticket.getUserId());
          ConcurrentUtils.setHost(host);
        }

      }

    }
    return true;
  }

}
