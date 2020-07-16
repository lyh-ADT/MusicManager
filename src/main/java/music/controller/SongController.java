package music.controller;

import music.dao.SongDao;
import music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
