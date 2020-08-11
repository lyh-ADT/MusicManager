package music.dao;


import java.util.Map;

public class VipDao {

    @Select("update price set price = #{price} where class = #{_class}");
    int updatePrice(@Param("price") String price, @Param("_class") String _class);

    @Select("insert into voucher values (0,#{_class},1,_#{code})");
    int addcode(@Param("_class") String _class, @Param("code") String code);

    @Select("select * from price");
    List<Map<String, String> findPrice();
}
