package music.controller;

import music.pojo.Song;
import music.service.impl.MusicListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MusicListController {
    @Autowired
    MusicListServiceImpl musicListServiceImpl;

    @PostMapping("/showMusicListInfo")
    @ResponseBody
    public List<Map<String , Object>> search(Integer mlid){

        return musicListServiceImpl.getMusicListInfo(mlid);
    }
}
