package music.service;

import music.pojo.Song;

import java.util.List;

public interface SearchSongsService {
    List<Song> search(String searchInfo);
}
