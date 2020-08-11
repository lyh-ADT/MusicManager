package music.service.impl;

import music.dao.MusicListDao;
import music.pojo.music_list_detail;
import music.pojo.music_list_info;
import music.service.MusicListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MusicListServiceImpl implements MusicListService {
    @Autowired
    MusicListDao musicListDao;

    /**
     * 获取用户自创建歌单
     * @param uid
     * @return
     */
    @Override
    public List<music_list_info> getUserEstablishMusicList(Integer uid) {
        return musicListDao.getUserEstablishMusicList(uid);
    }

    /**
     *
     * @param mlid  音乐列表id
     * @return  列表相关信息
     */
    @Override
    public List<music_list_detail> getMusicListInfo(int mlid) {
        return musicListDao.getMusicListInfo(mlid);
    }

    @Override
    public int[] judgeLikeOrNot(Integer mlid) {
        return musicListDao.judgeLikeOrNot(mlid);
    }
}
