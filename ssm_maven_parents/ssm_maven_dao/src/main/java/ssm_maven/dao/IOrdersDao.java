package ssm_maven.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ssm_maven.domain.Member;
import ssm_maven.domain.Orders;
import ssm_maven.domain.Product;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "ssm_maven.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();

    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "ssm_maven.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "ssm_maven.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm_maven.dao.ITravellerDao.findById"))
    })
    public Orders findById(int ordersId);
    //travellers是多个，所以用many
}
