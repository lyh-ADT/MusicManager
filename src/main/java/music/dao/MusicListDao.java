package music.dao;

import music.pojo.musicListInfo;
import music.pojo.music_list_songs;
import music.pojo.music_list_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
            " and ml.uid = 1 and ml.mlid=#{mlid} order by mlt.sid desc")
    List<music_list_songs> getMusicListSongsInfo(@Param("mlid") Integer mlid);

    @Select("select mld.sid from (select  sname , mlt.sid , singer from" +
            " music_list ml,music_list_detail mlt, song s where ml.mlid = mlt.mlid" +
            " and mlt.sid = s.sid and ml.uid = 1 and ml.mlid=#{mlid} order by mlt.sid desc) t1, music_list_detail mld" +
            " where t1.sid = mld.sid and mld.mlid=1")
    int[] judgeLikeOrNot(@Param("mlid") Integer mlid);


    @Select("select * from music_list where uid=#{uid}")
    List<music_list_info> getUserEstablishMusicList(@Param("uid") Integer uid);

    @Select("insert into music_list values(0,1,#{newMusicListName},'imges/share_icon.png',null,now())")
    List<Map<String , String>> addMusicList(@Param("newMusicListName") String newMusicListName);

    @Select("select * from music_list where mlid=#{mlid}")
    List<musicListInfo> getMusicListInfo(@Param("mlid") Integer mlid);
}
