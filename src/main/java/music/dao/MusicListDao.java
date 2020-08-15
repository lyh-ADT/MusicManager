package music.dao;

import music.pojo.musicListInfo;
import music.pojo.music_list_songs;
import music.pojo.music_list_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author D
 */
@Repository
@Mapper
public interface MusicListDao {


    @Select("select  sname , mlt.sid , singer from" +
            " music_list ml,music_list_detail mlt, song s where ml.mlid = mlt.mlid and mlt.sid = s.sid " +
            "and ml.mlid=#{mlid} order by mlt.sid desc")
    List<music_list_songs> getMusicListSongsInfo(@Param("mlid") Integer mlid);

    @Select("select mld.sid from (select mlt.sid from music_list ml,music_list_detail mlt, song s where ml.mlid = mlt.mlid" +
            " and mlt.sid = s.sid and ml.mlid=#{mlid} order by mlt.sid desc)  t1 , music_list_detail mld " +
            "where t1.sid = mld.sid and mld.mlid=#{userFavoriteList}")
    int[] judgeLikeOrNot(@Param("mlid") Integer mlid,@Param("userFavoriteList") Integer userFavoriteList);

    @Select("select sid from music_list_detail where mlid=#{userFavoriteList} and find_in_set(sid,#{allResultId})")
    int [] JudgeLikeORNotInfoForSearch(@Param("allResultId") String allResultId , @Param("userFavoriteList") Integer mlid );

    @Select("select * from music_list where uid=#{uid}")
    List<music_list_info> getUserEstablishMusicList(@Param("uid") Integer uid);

    @Select("insert into music_list values(0,1,#{newMusicListName},'imges/share_icon.png',null,now())")
    List<Map<String , String>> addMusicList(@Param("newMusicListName") String newMusicListName);

    @Select("select * from music_list where mlid=#{mlid}")
    List<musicListInfo> getMusicListInfo(@Param("mlid") Integer mlid);


    @Select("insert into music_list_detail values(#{sid} , #{userFavoriteList} , 0)")
    List<Map<String , String>> addLike(@Param("sid") Integer sid , @Param("userFavoriteList") Integer userFavoriteList);


    @Select("delete from music_list_detail where sid=#{sid} and mlid = #{userFavoriteList}")
    List<Map<String , String>> cancelLike(@Param("sid") Integer sid , @Param("userFavoriteList") Integer userFavoriteList);


    @Select("insert into music_list_detail values(#{sid} , #{mlid} , 0)")
    List<Map<String, String>> addSongToMusicList(@Param("mlid") Integer mlid, @Param("sid") Integer sid);

    @Select("delete from music_list_detail where sid=#{sid} and mlid=#{mlid}")
    List<Map<String , String>> deleteSongToMusicList(@Param("mlid") Integer mlid, @Param("sid") Integer sid);

    @Select("select * from music_list where mlname!='我喜欢的音乐' order by rand() limit 10")
    List<music_list_info> getRecommedMusicList();

    @Select("select * from music_list where uid=#{uid} order by mlid limit 1")
    List<music_list_info> getUserFavoriteList(@Param("uid") Integer uid);

    @Select("select ml.mlname , ml.imgurl , ml.mlid , ml.uid from collect_detail cd , music_list ml  where cd.mlid = ml.mlid and cd.uid=#{uid} ")
    List<music_list_info> getUserCollectMusicList(@Param("uid") Integer uid);

    @Select("select (select count(mlid) from music_list where uid=#{uid} and mlid=#{mlid}) + (select count(mlid) from collect_detail where mlid=#{mlid} and uid=#{uid})")
    int judgeListBelongOrnot(@Param("mlid") Integer mlid, @Param("uid") Integer uid);


    @Select("insert into collect_detail values(0,#{mlid} , #{uid})")
    List<Map<String, String>> collectThisList(@Param("uid") Integer uid , @Param("mlid") Integer mlid);


    @Select("select * from music_list where uid = #{uid} and mlid not in (#{mlid})")
    List<music_list_info> getRemoveableMusicList(@Param("mlid") Integer mlid , @Param("uid") Integer uid);


    /**
     * 检查歌单是否属于用户
     * @param mlid 歌单编号
     * @param uid 用户编号
     * @return 如果属于该用户，返回歌单的实体类，否则返回null
     */
    @Select("select * from music_list where mlid=#{mlid} and uid=#{uid}")
    musicListInfo checkMusicListOwner(@Param("mlid") Integer mlid, @Param("uid") Integer uid);

    /**
     * 修改歌单简介
     * @param mlid 歌单编号
     * @param description 歌单简介
     * @param uid 用户编号，用于验证身份
     * @return 修改的行数
     */
    @Update("update music_list set description=#{desc} where mlid=#{mlid} and uid=#{uid}")
    int setMusicListDescription(@Param("mlid") Integer mlid, @Param("desc") String description, @Param("uid") Integer uid);
}
