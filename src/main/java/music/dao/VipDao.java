package music.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface VipDao {

    @Select("update price set price = #{price} where class = #{_class}")
    int updatePrice(@Param("price") String price, @Param("_class") String _class);

    @Select("insert into voucher values (0,#{_class},1,_#{code})")
    int addcode(@Param("_class") String _class, @Param("code") String code);

    @Select("select * from price")
    List<Map<String, String>> findPrice();
}


