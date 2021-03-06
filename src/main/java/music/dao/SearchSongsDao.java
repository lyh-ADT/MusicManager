package music.dao;

import music.pojo.Song;
import music.pojo.music_list_info;
import music.pojo.music_list_songs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author D
 */
@Repository
@Mapper
public interface SearchSongsDao {
    /**
     * 根据搜索信息模糊查询歌曲信息
     * @param searchInfo  搜索栏信息
     * @return
     */
    @Select("SELECT  sname,sid,singer FROM song WHERE sname LIKE CONCAT('%',#{searchinfo},'%') ")
    List<music_list_songs> findSongsBysearchSongsInfo(@Param("searchinfo") String searchinfo);
}