package org.example.service.impl;

import org.example.dao.ActivityDao;
import org.example.entity.Activity;
import org.example.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;

    @Override
    public List<Activity> queryAll() {
        return activityDao.queryAll();
    }
}
