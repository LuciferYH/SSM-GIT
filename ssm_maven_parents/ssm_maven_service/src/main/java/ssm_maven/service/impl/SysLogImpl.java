package ssm_maven.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_maven.dao.ISysLogDao;
import ssm_maven.domain.SysLog;
import ssm_maven.service.ISysLogService;

import java.util.List;

@Service
@Transactional
public class SysLogImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
