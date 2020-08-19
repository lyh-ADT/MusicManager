package music.service.impl;

import music.dao.SearchSongsDao;
import music.pojo.Song;
import music.pojo.music_list_info;
import music.pojo.music_list_songs;
import music.service.SearchSongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchSongsServiceImpl implements SearchSongsService {
    @Autowired SearchSongsDao searchSongsDao;

    /**
     * 根据输入信息查找songs
     * @param searchInfo 搜索框信息
     * @return
     */
    @Override
    public List<music_list_songs> search(String searchInfo) {
        return searchSongsDao.findSongsBysearchSongsInfo(searchInfo);
    }

}
