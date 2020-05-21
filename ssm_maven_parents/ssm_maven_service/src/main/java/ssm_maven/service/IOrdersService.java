package ssm_maven.service;

import org.apache.ibatis.annotations.Select;
import ssm_maven.domain.Orders;

import java.util.List;

public interface IOrdersService {


    List<Orders> findAll();

    List<Orders> findByPages(int pageNum,int pageSize);

    Orders findById(int ordersId);
}
