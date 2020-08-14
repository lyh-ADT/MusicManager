package music.service;

import music.pojo.Song;
import music.pojo.music_list_info;
import music.pojo.music_list_songs;

import java.util.List;

public interface SearchSongsService {
    List<music_list_songs> search(String searchInfo);
}
