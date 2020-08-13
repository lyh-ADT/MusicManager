package music.controller;

import music.dao.adminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class admincontroller {

    @Autowired
    private adminDao admindao;

    @PostMapping("/regist")
    @ResponseBody
    public int regist(String account,String pwd){
         admindao.addAdmin(account,pwd);
         return 1;
    }

    @PostMapping("/usermessage")
    @ResponseBody
    public String usermessage(HttpSession session){
        String nickname = (String)session.getAttribute("nickname");
        return nickname;
    }




    @PostMapping("/login")
    @ResponseBody
    public int login(HttpServletRequest request){
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");

        Map<String,String> map = admindao.login(account,pwd);

        if (map == null){
            return -1;
        }

        HttpSession session = request.getSession();
        session.setAttribute("nickname",map.get("nickname"));
        session.setAttribute("pwd",map.get("pwd"));
        session.setAttribute("uid", map.get("uid"));
        return 1;
    }
}
