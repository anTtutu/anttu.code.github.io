package com.anttu.dynamic.service;

import java.util.List;

import com.anttu.dynamic.dto.QueryPageBean;
import com.anttu.dynamic.entity.AppEntity;

/**
 * 描述
 *
 * @ClassName：AppServce
 * @Description：App接口
 * @author：Anttu
 * @Date：1/11/2021 20:26
 */
public interface AppService {
    /**
     * 新增app应用
     * @param appEntity
     * @return
     */
    int insertApp(AppEntity appEntity);

    /**
     * 查询app应用列表，分页查询
     * @param queryPageBean
     * @return
     */
    List<AppEntity> findAll(QueryPageBean queryPageBean);

    /**
     * 查询单个app应用
     * @param appEntity
     * @return
     */
    AppEntity findApp(AppEntity appEntity);

    /**
     * 根据appId查询单个app应用
     * @param appId
     * @return
     */
    AppEntity findAppId(String appId);

    /**
     * 根据appName查询单个app应用
     * @param appName
     * @return
     */
    AppEntity findAppName(String appName);

    /**
     * 重新生成app应用密钥
     * @param newAppSecret
     * @param appEntity
     * @return
     */
    int refreshAppSecret(String newAppSecret, AppEntity appEntity);

    /**
     *  修改app应用accessToken
     * @param accessToken
     * @param appId
     * @return
     */
    int updateAccessToken(String accessToken, String appId);

    /**
     * 修改app应用是否可用标记
     * @param isFlag
     * @param appId
     * @return
     */
    int updateIsFlag(int isFlag, String appId);

    /**
     * 修改app应用是否删除标记
     * @param delFlag
     * @param appId
     * @return
     */
    int updateDelFlag(int delFlag, String appId);
}
