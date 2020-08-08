package music.service.impl;

import music.dao.SongCommentDao;
import music.pojo.Comment;
import music.service.SongCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyhADT
 */
@Service
public class SongCommentServiceImpl implements SongCommentService {

    @Autowired
    private SongCommentDao songCommentDao;

    @Override
    public List<Comment> getAllComments(String sid) {
        return songCommentDao.getAllComments(sid);
    }
}
