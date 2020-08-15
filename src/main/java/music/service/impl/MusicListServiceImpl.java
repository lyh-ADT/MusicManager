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
    public int[] judgeLikeOrNot(Integer mlid , Integer userFavoriteList) {
        return musicListDao.judgeLikeOrNot(mlid , userFavoriteList);
    }

    @Override
    public int[] JudgeLikeORNotInfoForSearch(String allResultId , Integer userFavoriteList) {
        return musicListDao.JudgeLikeORNotInfoForSearch(allResultId , userFavoriteList);
    }


    @Override
    public List<musicListInfo> getMusicListInfo(Integer mlid) {
        return musicListDao.getMusicListInfo(mlid);
    }

    @Override
    public List<music_list_info> getRemoveableMusicList(Integer mlid ,Integer uid) {
        return musicListDao.getRemoveableMusicList(mlid , uid);
    }

    @Override
    public List<Map<String, String>> addSongToMusicList(Integer mlid, Integer sid) {
        return musicListDao.addSongToMusicList(mlid , sid);
    }

    @Override
    public List<Map<String, String>> deleteSongToMusicList(Integer mlid, Integer sid) {
        return musicListDao.deleteSongToMusicList(mlid , sid);
    }

    @Override
    public List<music_list_info> getRecommedMusicList() {
        return musicListDao.getRecommedMusicList();
    }

    @Override
    public List<music_list_info> getUserFavoriteList(Integer uid) {
        return musicListDao.getUserFavoriteList(uid);
    }

    @Override
    public List<music_list_info> getUserCollectMusicList(Integer uid) {
        return musicListDao.getUserCollectMusicList(uid);
    }

    @Override
    public int judgeListBelongOrnot(Integer mlid, Integer uid) {
        return musicListDao.judgeListBelongOrnot(mlid , uid);
    }

    @Override
    public List<Map<String, String>> collectThisList(Integer uid, Integer mlid) {
        return musicListDao.collectThisList(uid , mlid);
    }

//    @Override
//    public List<music_list_info> getRemoveableMusicList(Integer mlid) {
//        return musicListDao.getRemoveableMusicList(mlid);
//    }

    @Override
    public boolean isMusicListOwner(Integer mlid, Integer uid) {
        return musicListDao.checkMusicListOwner(mlid, uid) != null;
    }

    @Override
    public String setMusicListDescription(Integer mlid, String description, Integer uid) {
        if(musicListDao.setMusicListDescription(mlid, description, uid) == 1){
            return "修改成功";
        }
        return "修改失败";
    }


    public List<Map<String , String>> addMusicList(String newMusicListname) {
        return musicListDao.addMusicList(newMusicListname);
    }

    //添加song到我喜欢歌单
    public List<Map<String , String>> addLike(Integer sid , Integer userFavoriteList) {
        return musicListDao.addLike(sid , userFavoriteList);
    }

    //将song从我喜欢歌单中删除
    public List<Map<String , String>> cancelLike(Integer sid , Integer userFavoriteList) {
        return musicListDao.cancelLike(sid , userFavoriteList);
    }


}
