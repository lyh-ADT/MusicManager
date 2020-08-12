package music.controller;

import music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
        return "redirect:"+urlEncodeLastPath(songService.getUrlBySid(sid));
    }

    @GetMapping("/song/{sid}/info")
    @ResponseBody
    public Object getSongInfo(@PathVariable("sid") String sid, @SessionAttribute(value = "uid", required = false) String uid){
        return songService.getSongInfo(sid, uid);
    }

    @GetMapping("/song/{sid}/lyric")
    @ResponseBody
    public String getSongLyric(@PathVariable("sid") String sid){
        return songService.getSongLyricUrl(sid);
    }

    @GetMapping("/song/randomId")
    @ResponseBody
    public int getRandomSid(){
        return songService.getRandomSid();
    }

    private String urlEncodeLastPath(String url){
        final int divide = url.lastIndexOf("/");
        if(divide == -1){
            return url;
        }
        final String address = url.substring(0, divide+1);
        String path = url.substring(divide+1);
        try {
            return address+URLEncoder.encode(path, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
