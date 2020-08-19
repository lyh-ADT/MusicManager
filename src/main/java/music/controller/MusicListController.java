package music.controller;

import music.pojo.musicListInfo;
import music.pojo.music_list_songs;
import music.pojo.music_list_info;
import music.service.impl.MusicListServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public int[] judgeLikeOrNot(Integer mlid , Integer userFavoriteList){
        return musicListServiceImpl.judgeLikeOrNot(mlid , userFavoriteList);
    }

    @PostMapping("/JudgeLikeORNotInfoForSearch")
    @ResponseBody
    public int[] JudgeLikeORNotInfoForSearch(String allResultId , Integer userFavoriteList){

        return musicListServiceImpl.JudgeLikeORNotInfoForSearch(allResultId , userFavoriteList);
    }
//
//    @PostMapping("/JudgeLikeORNotInfoForSearch")
//    @ResponseBody
//    public int[] JudgeLikeORNotInfoForSearch(String allResultId){
//
//        return musicListServiceImpl.JudgeLikeORNotInfoForSearch(allResultId);
//    }

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
    public List<Map<String , String>> addLike(Integer sid , Integer userFavoriteList){
        return musicListServiceImpl.addLike(sid , userFavoriteList);
    }

    @PostMapping("/cancelLike")
    @ResponseBody
    public List<Map<String , String>> cancelLike(Integer sid , Integer userFavoriteList){
        return musicListServiceImpl.cancelLike(sid , userFavoriteList);
    }

    @PostMapping("/getRemoveableMusicList")
    @ResponseBody
    public List<music_list_info> getRemoveableMusicList(Integer mlid , Integer uid){
        return musicListServiceImpl.getRemoveableMusicList(mlid , uid);
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

    @PostMapping("/getRecommedMusicList")
    @ResponseBody
    public List<music_list_info> deleteSongToMusicList(){
        return musicListServiceImpl.getRecommedMusicList();
    }

    @PostMapping("/getUserFavoriteList")
    @ResponseBody
    List<music_list_info> getUserFavoriteList(HttpSession session){
        Integer uid = (Integer) session.getAttribute("uid") ;
        return musicListServiceImpl.getUserFavoriteList(uid);
    }

    @PostMapping("/getUserCollectMusicList")
    @ResponseBody
    public List<music_list_info> getUserCollectMusicList(Integer uid){
        return musicListServiceImpl.getUserCollectMusicList(uid);
    }

    @PostMapping("/judgeListBelongOrnot")
    @ResponseBody
    public int judgeListBelongOrnot(Integer mlid , Integer uid){
        return musicListServiceImpl.judgeListBelongOrnot(mlid , uid);
    }

    @PostMapping("/collectThisList")
    @ResponseBody
    public List<Map<String , String >> collectThisList(Integer uid , Integer mlid){
        return musicListServiceImpl.collectThisList(uid , mlid);
    }

//    @PostMapping("/getRemoveableMusicList")
//    @ResponseBody
//    public List<music_list_info> getRemoveableMusicList(Integer mlid){
//        return musicListServiceImpl.getRemoveableMusicList(mlid);
//    }

    @GetMapping("/MusicListOwner")
    @ResponseBody
    public boolean isMusicListOwner(@RequestParam Integer mlid, @SessionAttribute Integer uid){
        return musicListServiceImpl.isMusicListOwner(mlid, uid);
    }

    @PostMapping("/MusicList/{mlid}/description")
    @ResponseBody
    public String setMusicListDescription(@PathVariable("mlid") Integer mlid, @RequestParam("description") String description, @SessionAttribute Integer uid){
        return musicListServiceImpl.setMusicListDescription(mlid, description, uid);
    }

}
