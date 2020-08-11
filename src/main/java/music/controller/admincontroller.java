package music.controller;

import music.dao.adminDao;


import java.util.Map;

@Controller
public class admincontroller {

    @Autowired
    adminDao admindao;

    @PostMapping("/regist")
    @ResponseBody
    public int regist(String account,String pwd){
         admindao.addAdmin(account,pwd);
         return 1;
    }

    @PostMapping("/login")
    @ResponseBody
    public int login(String account, String pwd){
        Map<String,String> map = admindao.login(account,pwd);
        return 1;
    }
}
