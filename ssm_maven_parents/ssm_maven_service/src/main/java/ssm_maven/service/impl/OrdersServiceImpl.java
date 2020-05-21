package ssm_maven.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_maven.dao.IOrdersDao;
import ssm_maven.domain.Orders;
import ssm_maven.service.IOrdersService;

import java.util.List;

@Service("ordersServiceImpl")
@Transactional//事务管理的注解
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll() {

        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findByPages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//这一句一定要在ordersDao.findAll()上面
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(int ordersId) {
        return ordersDao.findById(ordersId);
    }
}
