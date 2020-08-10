package music.service;

import music.pojo.music_list_detail;
import music.pojo.music_list_info;

import java.util.List;
import java.util.Map;

public interface MusicListService {
    //首页获取用户创建歌单
    List<music_list_info> getUserEstablishMusicList(Integer uid);
    //获取歌单信息方法
    List<music_list_detail> getMusicListInfo(int mlid);
    //判断歌单中的歌曲是否在“我喜欢”歌单中
    public int[] judgeLikeOrNot(Integer mlid);
}

