package org.crm.service.impl;

import org.crm.dao.ChartsDao;
import org.crm.entity.DicValue;
import org.crm.service.ChartsService;
import org.crm.service.DicValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("chartsService")
public class ChartsServiceImpl implements ChartsService {

    @Resource
    private ChartsDao chartsDao;



    @Override
    public List<Map<String, Object>> queryTranCount() {
        return chartsDao.queryTranCount();
    }
}
