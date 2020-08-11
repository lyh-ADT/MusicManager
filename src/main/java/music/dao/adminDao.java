package music.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface adminDao {
    @Select("select * from adminInfo where account = #{account} and pwd = #{pwd}")
    Map<String,String> login(@Param("account") String account, @Param("pwd") String pwd);


    @Select("insert into adminInfo values (0,#{account},#{pwd})")
    int addAdmin(@Param("account") String account, @Param("pwd") String pwd);
}
