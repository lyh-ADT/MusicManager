package music.controller;

import music.dao.VipDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;


import java.util.List;
import java.util.Map;

@Controller
public class vipBack {

    @Autowired
    private VipDao vipDao;

    @PostMapping("/findPrice")
    @ResponseBody
    public  List<Map<String, Object>> findPrice(){
        return vipDao.findPrice();
    }

    @PostMapping("/back/updatedata1")
    @ResponseBody
    public int updatedata1(String select1, String input1){
        vipDao.updatePrice(select1,input1);
        return  1;
    }

    @PostMapping("/back/updatedata2")
    @ResponseBody
    public int updatedata2(String select2, String input2){
        vipDao.updatePrice(select2,input2);
        return  1;
    }

    @PostMapping("/back/updatedata3")
    @ResponseBody
    public int updatedata3(String select3, String input3){
        vipDao.addcode(select3,input3);
        return  1;
    }

    @PostMapping("/updatevoucher")
    @ResponseBody

    public int updatevoucher(String nickname,String code){
        return vipDao.updatevourcher(nickname,code);
    }
    @PostMapping("/findVoucher")
    @ResponseBody
    public  List<Map<String, Object>> findVoucher(String nickname){
        return vipDao.findVoucher(nickname);
    }

}
