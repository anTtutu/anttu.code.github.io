package com.anttu.dynamic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.anttu.dynamic.dto.QueryPageBean;
import com.anttu.dynamic.entity.AppEntity;

/**
 * 描述
 *
 * @ClassName：AppMapper
 * @Description：App的 mapper
 * @author：Anttu
 * @Date：28/10/2021 20:42
 */
public interface AppMapper {
    /**
     * 新增
     * @param appEntity
     * @return
     */
    @Insert("insert into m_app (app_name, app_id, app_secret, access_token) values (#{appName}, #{appId}, #{appSecret}, #{accessToken})")
    int insertApp(AppEntity appEntity);

    /**
     * 分页查询列表
     * @param queryPageBean
     * @return
     */
    @Select("SELECT ID AS Id, APP_NAME AS appName, app_id as appId, app_secret as appSecret, is_flag as isFlag, access_token as accessToken, del_flag as delFlag, create_time as createTime, update_time as updateTime from m_app limit #{num}, #{size}")
    List<AppEntity> findAll(QueryPageBean queryPageBean);

    /**
     * 查找单个 app 应用
     * @param appEntity
     * @return
     */
    @Select("SELECT ID AS Id, APP_NAME AS appName, app_id as appId, app_secret as appSecret, is_flag as isFlag, access_token as accessToken, del_flag as delFlag, create_time as createTime, update_time as updateTime from m_app "
            + "where app_id=#{appId} and app_secret=#{appSecret}")
    AppEntity findApp(AppEntity appEntity);

    /**
     * 根据 appId 查询单个 app 应用
     * @param appId
     * @return
     */
    @Select("SELECT ID AS Id, APP_NAME AS appName, app_id as appId, app_secret as appSecret, is_flag as isFlag, access_token as accessToken, del_flag as delFlag, create_time as createTime, update_time as updateTime from m_app "
            + "where app_id=#{appId}")
    AppEntity findAppId(@Param("appId") String appId);

    /**
     * 根据 appName 查询单个 app 应用
     * @param appName
     * @return
     */
    @Select("SELECT ID AS Id, APP_NAME AS appName, app_id as appId, app_secret as appSecret, is_flag as isFlag, access_token as accessToken, del_flag as delFlag, create_time as createTime, update_time as updateTime from m_app "
            + "where app_name=#{appName}")
    AppEntity findAppName(@Param("appName") String appName);

    /**
     * 修改 app 密钥
     * @param newAppSecret
     * @param appEntity
     * @return
     */
    @Update("update m_app set app_secret =#{newAppSecret} where app_id=#{appId} and app_secret=#{appSecret}")
    int refreshAppSecret(@Param("newAppSecret") String newAppSecret, AppEntity appEntity);

    /**
     * 修改 accessToken
     * @param accessToken
     * @param appId
     * @return
     */
    @Update("update m_app set access_token =#{accessToken} where app_id=#{appId}")
    int updateAccessToken(@Param("accessToken") String accessToken, @Param("appId") String appId);

    /**
     * 修改是否可用标识
     * @param isFlag
     * @param appId
     * @return
     */
    @Update("update m_app set is_flag =#{isFlag} where app_id=#{appId}")
    int updateIsFlag(@Param("isFlag") int isFlag, @Param("appId") String appId);

    /**
     * 修改是否删除标识
     * @param delFlag
     * @param appId
     * @return
     */
    @Update("update m_app set del_flag =#{delFlag} where app_id=#{appId}")
    int updateDelFlag(@Param("delFlag") int delFlag, @Param("appId") String appId);
}
