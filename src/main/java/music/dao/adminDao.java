package music.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
@Mapper
public interface adminDao {
    @Select("select * from user where nickname = #{nickname} and pwd = #{pwd}")
    Map<String,String> login(@Param("nickname") String nickname, @Param("pwd") String pwd);


    @Insert("insert into user values (0,#{pwd},null,#{nickname},'884520248@qq.com')")
    int addAdmin(@Param("nickname") String nickname, @Param("pwd") String pwd);
}



