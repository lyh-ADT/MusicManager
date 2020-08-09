package music.dao;

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
            " music_list ml,music_list_detail mlt, song s where ml.mlid = mlt.mlid and mlt.sid = s.sid and ml.uid = 1 and ml.mlid=#{mlid} order by mlt.sid desc")
    List<Map<String , Object >> getMusicListInfo(@Param("mlid") Integer mlid);

}
