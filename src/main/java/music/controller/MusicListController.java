package music.controller;

import music.pojo.musicListInfo;
import music.pojo.music_list_songs;
import music.pojo.music_list_info;
import music.service.impl.MusicListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MusicListController {
    @Autowired
    MusicListServiceImpl musicListServiceImpl;


    @PostMapping("/showMusicListInfo")
    @ResponseBody
    public List<music_list_songs> search(Integer mlid){

        return musicListServiceImpl.getMusicListSongsInfo(mlid);
    }

    @PostMapping("/judgeLikeOrNot")
    @ResponseBody
    public int[] judgeLikeOrNot(Integer mlid){

        return musicListServiceImpl.judgeLikeOrNot(mlid);
    }

    @PostMapping("/JudgeLikeORNotInfoForSearch")
    @ResponseBody
    public int[] JudgeLikeORNotInfoForSearch(String allResultId){

        return musicListServiceImpl.JudgeLikeORNotInfoForSearch(allResultId);
    }

    @PostMapping("/getUserEstablishMusicList")
    @ResponseBody
    public List<music_list_info> getUserEstablishMusicList(Integer uid){
        return musicListServiceImpl.getUserEstablishMusicList(uid);
    }

    @PostMapping("/addMusicList")
    @ResponseBody
    public List<Map<String , String>> addMusicList(String newMusicListName){
        System.out.println(newMusicListName);
        return musicListServiceImpl.addMusicList(newMusicListName);
    }

    @PostMapping("/getMusicListIfo")
    @ResponseBody
    public List<musicListInfo> getMusicListInfo(Integer mlid){

        return musicListServiceImpl.getMusicListInfo(mlid);
    }

    @PostMapping("/addLike")
    @ResponseBody
    public List<Map<String , String>> addLike(Integer sid){
        return musicListServiceImpl.addLike(sid);
    }

    @PostMapping("/cancelLike")
    @ResponseBody
    public List<Map<String , String>> cancelLike(Integer sid){
        return musicListServiceImpl.cancelLike(sid);
    }

    @PostMapping("/getRemoveableMusicList")
    @ResponseBody
    public List<music_list_info> getRemoveableMusicList(Integer mlid){
        return musicListServiceImpl.getRemoveableMusicList(mlid);
    }

    @PostMapping("/addSongToMusicList")
    @ResponseBody
    public List<Map<String , String>> addSongToMusicList(Integer mlid , Integer sid){

        return musicListServiceImpl.addSongToMusicList(mlid , sid);
    }

    @PostMapping("/deleteSongToMusicList")
    @ResponseBody
    public List<Map<String , String>> deleteSongToMusicList(Integer mlid , Integer sid){
        return musicListServiceImpl.deleteSongToMusicList(mlid , sid);
    }

}
