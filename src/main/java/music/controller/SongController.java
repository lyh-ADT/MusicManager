package music.controller;

import music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

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

    @GetMapping("/song/{sid}/download")
    public String download(@PathVariable("sid") String sid, HttpServletResponse response) throws IOException {
        Map<String, Object> song = songService.getSongInfo(sid, null);
        System.out.println(song.get("name"));
        String name = URLEncoder.encode((String) song.get("name"), "utf-8").replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment;fileName="+name+".mp3");

        HttpURLConnection connection = (HttpURLConnection) new URL(urlEncodeLastPath(songService.getUrlBySid(sid))).openConnection();
        connection.setRequestMethod("GET");
        connection.getResponseCode();
        InputStream inputStream = connection.getInputStream();
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read=inputStream.read(buffer,0,buffer.length)) != -1){
            outputStream.write(buffer, 0, read);
        }

        inputStream.close();
        return null;
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
