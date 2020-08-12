package music.service;

import music.pojo.Comment;

import java.util.List;

/**
 * @author lyhADT
 */
public interface SongCommentService {
    /**
     * 根据歌曲sid获取评论
     * @param sid 歌曲sid
     * @return 评论列表
     */
    List<Comment> getAllComments(String sid);

    /**
     * 添加歌曲评论
     * @param comment 歌曲评论实体类
     * @return 操作结果信息
     */
    String addComment(Comment comment);
}
