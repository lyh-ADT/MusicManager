package music.controller;

import music.pojo.Song;
import music.pojo.music_list_info;
import music.pojo.music_list_songs;
import music.service.SearchSongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchSongsController {
    @Autowired
    SearchSongsService searchSongsService;

    @PostMapping("/searchSongs")
    @ResponseBody
    public List<music_list_songs> search(String searchInfo){
        List<music_list_songs> songs = searchSongsService.search(searchInfo);
        return songs;
    }

}
