package music.dao;

import music.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lyhADT
 */
@Repository
@Mapper
public interface SongCommentDao {
    /**
     * 根据歌曲sid获取评论
     * @param sid 歌曲sid
     * @return 评论列表
     */
    @Select("select c.*, u.nickname from comment c, user u where c.uid=u.uid and c.sid=#{sid}")
    List<Comment> getAllComments(@Param("sid") String sid);
}
