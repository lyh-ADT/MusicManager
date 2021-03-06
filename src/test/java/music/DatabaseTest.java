package music;

import music.dao.SongDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import  static org.junit.Assert.*;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SongDao songDao;

    @Test
    public void testConnection(){
        jdbcTemplate.execute("show tables");
        List<String> tables = jdbcTemplate.queryForList("show tables", String.class);

        final String[] expected = {"music_list", "music_list_detail", "song", "user"};
        assertArrayEquals(expected, tables.toArray(new String[0]));
    }

    @Test
    public void testMybatis(){
        Object obj = songDao.getSongBySid(1);
        System.out.println(obj);
        assertNotEquals(null, obj);
    }
}
