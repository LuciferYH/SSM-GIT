package ssm_maven.service;

import ssm_maven.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
