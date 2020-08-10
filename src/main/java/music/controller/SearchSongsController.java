package music.controller;

import music.pojo.Song;
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
    public List<Song> search(String searchInfo){
        List<Song> songs = searchSongsService.search(searchInfo);
        return songs;
    }

}
