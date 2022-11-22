package com.anttu.dynamic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.anttu.dynamic.dto.QueryPageBean;
import com.anttu.dynamic.entity.AppEntity;
import com.anttu.dynamic.mapper.AppMapper;
import com.anttu.dynamic.service.AppService;

/**
 * 描述
 *
 * @ClassName：AppServiceImpl
 * @Description：App 实现类
 * @author：Anttu
 * @Date：1/11/2021 20:28
 */
@Service
@Cacheable(cacheManager = "caffeineCacheManager")
@CacheConfig(cacheNames = "app")
public class AppServiceImpl implements AppService {

    @Resource
    private AppMapper appMapper;

    @Override
    public int insertApp(AppEntity appEntity) {
        return appMapper.insertApp(appEntity);
    }

    @Cacheable(value = "all", key = "'all'")
    @Override
    public List<AppEntity> findAll(QueryPageBean queryPageBean) {
        return appMapper.findAll(queryPageBean);
    }

    @Cacheable(value = "id", key = "#appEntity.getId()")
    @Override
    public AppEntity findApp(AppEntity appEntity) {
        return appMapper.findApp(appEntity);
    }

    @Cacheable(value = "appId", key = "#appId")
    @Override
    public AppEntity findAppId(String appId) {
        return appMapper.findAppId(appId);
    }

    @Cacheable(value = "appName", key = "#appName")
    @Override
    public AppEntity findAppName(String appName) {
        return appMapper.findAppName(appName);
    }

    @Override
    public int refreshAppSecret(String newAppSecret, AppEntity appEntity) {
        return appMapper.refreshAppSecret(newAppSecret, appEntity);
    }

    @Override
    public int updateAccessToken(String accessToken, String appId) {
        return appMapper.updateAccessToken(accessToken, appId);
    }

    @Override
    public int updateIsFlag(int isFlag, String appId) {
        return appMapper.updateIsFlag(isFlag, appId);
    }

    @Override
    public int updateDelFlag(int delFlag, String appId) {
        return appMapper.updateDelFlag(delFlag, appId);
    }
}
