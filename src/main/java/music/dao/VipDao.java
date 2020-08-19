package music.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface VipDao {

    @Update("update price set price = #{price} where class = #{_class}")
    int updatePrice(@Param("_class") String _class ,@Param("price") String price);

    @Insert("insert into voucher values (0,#{_class},'1',#{code})")
    int addcode(@Param("_class") String _class, @Param("code") String code);

    @Select("select * from price")
    List<Map<String, Object>> findPrice();

    @Update("update voucher set status = #{status} where concode = #{code}")
    int updatevourcher(@Param("status") String nickname ,@Param("code") String code);

    @Select("select class from voucher where status = #{nickname}")
    List<Map<String, Object>> findVoucher(@Param("nickname") String nickname);
}


