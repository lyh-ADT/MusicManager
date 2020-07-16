package music.service.impl;

import music.dao.SongDao;
import music.pojo.Song;
import music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyhADT
 */
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongDao songDao;

    @Override
    public String getUrlBySid(String sid) {
        Song song = songDao.getSongBySid(Integer.parseInt(sid));
        return song.getUrl();
    }
}
