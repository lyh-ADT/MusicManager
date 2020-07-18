package music.controller;

import music.dao.SongDao;
import music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 歌曲的Controller
 * @author lyhADT
 */
@Controller
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/song/{sid}/mp3")
    public String getMp3(@PathVariable("sid")String sid){
        return songService.getUrlBySid(sid);
    }

    @GetMapping("/song/{sid}/info")
    @ResponseBody
    public Object getSongInfo(@PathVariable("sid") String sid, @SessionAttribute(value = "uid", required = false) String uid){
        return songService.getSongInfo(sid, uid);
    }
}
