package music.dao;

import music.pojo.music_list_detail;
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

    @Select("select ml.imgUrl , mlname , date , description , sname , mlt.sid , singer from" +
            " music_list ml,music_list_detail mlt, song s where ml.mlid = mlt.mlid and mlt.sid = s.sid " +
            " and ml.uid = 1 and ml.mlid=#{mlid} order by mlt.sid desc")
    List<music_list_detail> getMusicListInfo(@Param("mlid") Integer mlid);

    @Select("select mld.sid from (select ml.imgUrl , mlname , date , description , sname , mlt.sid , singer from" +
            " music_list ml,music_list_detail mlt, song s where ml.mlid = mlt.mlid" +
            " and mlt.sid = s.sid and ml.uid = 1 and ml.mlid=#{mlid} order by mlt.sid desc) t1, music_list_detail mld" +
            " where t1.sid = mld.sid and mld.mlid=1")
    int[] judgeLikeOrNot(@Param("mlid") Integer mlid);


    @Select("select * from music_list where uid=#{uid}")
    List<music_list_info> getUserEstablishMusicList(@Param("uid") Integer uid);
}
