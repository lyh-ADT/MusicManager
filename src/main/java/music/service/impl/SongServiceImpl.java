package music.service.impl;

import music.dao.SongDao;
import music.pojo.Song;
import music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Object> getSongInfo(String sid, String uid) {
        int songId = Integer.parseInt(sid);
        Map<String, Object> result = new HashMap<>();
        Song song = songDao.getSongBySid(songId);
        result.put("sid", songId);
        result.put("name", song.getSname());
        result.put("singer", song.getSinger());
        result.put("imgUrl", song.getImgUrl());

        // TODO: 用户是否添加到我最喜欢
        result.put("liked", false);

        return result;
    }
}
