package music.dao;

import music.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
class SongCommentDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SongCommentDao songCommentDao;

    @Test
    public void addComment(){
        final String expected = "test content";
        Comment comment = new Comment();
        comment.setSid(1);
        comment.setContent(expected);
        comment.setSub_cid(null);
        comment.setUid(1);
        songCommentDao.addComment(comment);
        String actual = jdbcTemplate.queryForObject("select content from comment where cid=?", String.class, comment.getCid());
        assertEquals(expected, actual);
    }

}