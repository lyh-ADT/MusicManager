package music.service;

import music.pojo.musicListInfo;
import music.pojo.music_list_songs;
import music.pojo.music_list_info;

import java.util.List;
import java.util.Map;

public interface MusicListService {
    //首页获取用户创建歌单
    List<music_list_info> getUserEstablishMusicList(Integer uid);
    //获取歌单中歌曲方法
    List<music_list_songs> getMusicListSongsInfo(int mlid);
    //判断歌单中的歌曲是否在“我喜欢”歌单中
    int[] judgeLikeOrNot(Integer mlid);

    //判断搜索出来的歌曲是否在“我喜欢”歌单中
    int[] JudgeLikeORNotInfoForSearch(String allResultId);

    //获取歌单中歌曲方法
    public List<musicListInfo> getMusicListInfo(Integer mlid);

    //查询可用于移除歌曲的歌单
    List<music_list_info> getRemoveableMusicList(Integer mlid);

    //将指定歌曲添加到指定歌单中操作
    List<Map<String, String>> addSongToMusicList(Integer mlid, Integer sid);

    //将指定歌曲从指定歌单中删除操作
    List<Map<String, String>> deleteSongToMusicList(Integer mlid, Integer sid);


}

