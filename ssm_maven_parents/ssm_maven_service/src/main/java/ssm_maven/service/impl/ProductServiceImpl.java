package ssm_maven.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_maven.dao.IProductDao;
import ssm_maven.domain.Product;
import ssm_maven.service.IProductService;

import java.util.List;

@Service("productServiceImpl")
@Transactional//事务管理的注解
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public void save(Product product) {
        System.out.println("service:保存方法执行了");
        productDao.save(product);
    }

    @Override
    public List<Product> findAllProduct() {
        System.out.println("service:查询所有方法执行了");
        return productDao.findAllProduct();
    }
}
