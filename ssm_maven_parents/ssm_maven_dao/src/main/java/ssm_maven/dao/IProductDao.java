package ssm_maven.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_maven.domain.Product;


import java.util.List;

@Repository
public interface IProductDao {

    //根据id查询product
    @Select("select * from product where id=#{id}")
    public Product findById(Integer id);

    @Select("select * from product")
    public List<Product> findAllProduct();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc ,productStatus) " +
            "  values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);
}
