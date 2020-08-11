package music.controller;

import music.dao.VipDao;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Controller
public class vipBack {

    @Autowired
    VipDao vipDao;

    @PostMapping("/findPrice")
    @ResponseBody
    public List<Map<String, String>> findPrice(){
        return vipDao.findPrice();
    }

    @PostMapping("/updatedata1")
    @ResponseBody
    public int updatedata1(String select1, String input1){
        vipDao.updatePrice(select1,input1);
        return  1;
    }

    @PostMapping("/updatedata2")
    @ResponseBody
    public int updatedata2(String select1, String input1){
        vipDao.updatePrice(select1,input1);
        return  1;
    }

    @PostMapping("/updatedata3")
    @ResponseBody
    public int updatedata3(String select1, String input1){
        vipDao.updatePrice(select1,input1);
        return  1;
    }
}
