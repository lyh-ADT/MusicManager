package music.service;

import music.pojo.musicListInfo;
import music.pojo.music_list_songs;
import music.pojo.music_list_info;

import java.util.List;

public interface MusicListService {
    //首页获取用户创建歌单
    List<music_list_info> getUserEstablishMusicList(Integer uid);
    //获取歌单中歌曲方法
    List<music_list_songs> getMusicListSongsInfo(int mlid);
    //判断歌单中的歌曲是否在“我喜欢”歌单中
    int[] judgeLikeOrNot(Integer mlid);

    //获取歌单中歌曲方法
    public List<musicListInfo> getMusicListInfo(Integer mlid);
}

