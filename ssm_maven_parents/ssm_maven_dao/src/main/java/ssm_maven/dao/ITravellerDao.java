package ssm_maven.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_maven.domain.Traveller;

import java.util.List;

@Repository
public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    List<Traveller> findById(int ordersId);
    //通过ordersId查询Traveller，需要通过中间表order_traveller
}
