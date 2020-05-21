package ssm_maven.service;

import ssm_maven.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAllProduct();

    void save(Product product);
}
