package music.service.impl;

import music.dao.MusicListDao;
import music.pojo.musicListInfo;
import music.pojo.music_list_songs;
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
    public List<music_list_songs> getMusicListSongsInfo(int mlid) {
        return musicListDao.getMusicListSongsInfo(mlid);
    }

    @Override
    public int[] judgeLikeOrNot(Integer mlid) {
        return musicListDao.judgeLikeOrNot(mlid);
    }

    @Override
    public List<musicListInfo> getMusicListInfo(Integer mlid) {
        return musicListDao.getMusicListInfo(mlid);
    }


    public List<Map<String , String>> addMusicList(String newMusicListname) {
        return musicListDao.addMusicList(newMusicListname);
    }

    //添加song到我喜欢歌单
    public List<Map<String , String>> addLike(Integer sid) {
        return musicListDao.addLike(sid);
    }
    //将song从我喜欢歌单中删除
    public List<Map<String , String>> cancelLike(Integer sid) {
        return musicListDao.cancelLike(sid);
    }

}
