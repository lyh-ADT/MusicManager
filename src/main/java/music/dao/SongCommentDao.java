package music.dao;

import music.pojo.Comment;
import org.apache.ibatis.annotations.*;
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

    /**
     * 添加歌曲评论
     * @param comment 评论实体类
     */
    @Insert("insert into comment values(0, #{content}, #{uid}, #{sub_cid,jdbcType=INTEGER}, #{sid}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    void addComment(Comment comment);
}
