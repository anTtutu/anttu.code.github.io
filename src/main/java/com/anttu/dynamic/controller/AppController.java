package com.anttu.dynamic.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.anttu.dynamic.dto.QueryPageBean;
import com.anttu.dynamic.entity.AppEntity;
import com.anttu.dynamic.request.AppRequest;
import com.anttu.dynamic.response.AppResponse;
import com.anttu.dynamic.service.AppService;
import com.anttu.dynamic.util.AppIDUtils;
import com.anttu.dynamic.util.AppSecretUtils;
import com.anttu.dynamic.util.TokenUtils;

/**
 * 描述
 *
 * @ClassName：AppController
 * @Description：App 应用
 * @author：Anttu
 * @Date：1/11/2021 20:15
 */
@RestController
@RequestMapping("app/auth")
public class AppController {

    @Resource
    private AppService appService;

    /**
     * 输入appName创建app应用，返回appId，appSecret，accessToken
     * @param appRequest
     * @return
     */
    @RequestMapping(value = "test/createApp", method = RequestMethod.POST)
    public AppResponse createApp (@RequestBody AppRequest appRequest) {
        AppEntity appResult = appService.findAppName(appRequest.getAppName());
        if (!ObjectUtils.isEmpty(appResult)) {
            return AppResponse.failed(403, "appName is exists", appResult);
        }
        String appId = AppIDUtils.generateAppId();
        String appSecret = AppSecretUtils.generateAppSecret(appId);
        AppEntity appEntity = new AppEntity();
        appEntity.setAppId(appId);
        appEntity.setAppSecret(appSecret);
        appEntity.setAppName(appRequest.getAppName());
        appEntity.setAccessToken(TokenUtils.createToken());
        appService.insertApp(appEntity);
        return AppResponse.success(appEntity);
    }

    /**
     * 查询单个 app 应用
     * @param appRequest
     * @return
     */
    @RequestMapping(value = "getAccessToken", method = RequestMethod.POST)
    public AppResponse getAccessToken(@RequestBody AppRequest appRequest) {
        AppEntity appEntity = new AppEntity();
        appEntity.setAppId(appRequest.getAppId());
        appEntity.setAppSecret(appRequest.getAppSecret());
        AppEntity appResult = appService.findApp(appEntity);
        if (ObjectUtils.isEmpty(appResult)) {
            return AppResponse.failed(404, "appId not found", appEntity);
        }

        return AppResponse.success(appResult);
    }

    /**
     * 刷新 accessToken
     * @param appRequest
     * @return
     */
    @RequestMapping(value = "refreshAccessToken", method = RequestMethod.POST)
    public AppResponse refreshAccessToken(@RequestBody AppRequest appRequest) {
        AppEntity appEntity = new AppEntity();
        appEntity.setAppId(appRequest.getAppId());
        appEntity.setAppSecret(appRequest.getAppSecret());

        String accessToken = TokenUtils.createToken();
        int result = appService.updateAccessToken(accessToken, appRequest.getAppId());
        if (result >= 1) {
            AppEntity newAppEntity = appService.findAppId(appEntity.getAppId());
            return AppResponse.success(newAppEntity);
        } else {
            return AppResponse.failed(404, "appId not found", appEntity);
        }
    }

    /**
     * 分页查询 app 应用列表
     * @param queryPageBean
     * @return
     */
    @RequestMapping(value = "queryAllAccessToken", method = RequestMethod.POST)
    public AppResponse queryAllAccessToken(@RequestBody QueryPageBean queryPageBean) {
        List<AppEntity> appEntityList = appService.findAll(queryPageBean);
        PageInfo pageInfo = new PageInfo(appEntityList);
        return AppResponse.success(pageInfo);
    }

    /**
     * 刷新app应用密钥
     * @param appRequest
     * @return
     */
    @RequestMapping(value = "refreshAppSecret", method = RequestMethod.POST)
    public AppResponse refreshAppSecret(@RequestBody AppRequest appRequest){
        AppEntity appResult = appService.findAppId(appRequest.getAppId());
        if (ObjectUtils.isEmpty(appResult)) {
            return AppResponse.failed(404, "appId not found", appRequest);
        }
        int result = appService.refreshAppSecret(AppSecretUtils.generateAppSecret(appRequest.getAppId()), appResult);
        if (result >= 1) {
            AppEntity appEntity = appService.findAppId(appResult.getAppId());
            return AppResponse.success(appEntity);
        } else {
            return AppResponse.failed(404, "appId not found", appRequest);
        }
    }

    /**
     * 修改 app 应用是否可用标记
     * @param appRequest
     * @return
     */
    @RequestMapping(value = "updateIsFlag", method = RequestMethod.POST)
    public AppResponse updateIsFlag(@RequestBody AppRequest appRequest) {
        AppEntity appResult = appService.findAppId(appRequest.getAppId());
        if (ObjectUtils.isEmpty(appResult)) {
            return AppResponse.failed(404, "appId not found", appRequest);
        }
        int result = appService.updateIsFlag(1, appResult.getAppId());
        if (result >= 1) {
            AppEntity appEntity = appService.findAppId(appResult.getAppId());
            return AppResponse.success(appEntity);
        } else {
            return AppResponse.failed(404, "appId not found", appRequest);
        }
    }

    /**
     * 修改 app 应用是否删除标记
     * @param appRequest
     * @return
     */
    @RequestMapping(value = "updateDelFlag", method = RequestMethod.POST)
    public AppResponse updateDelFlag(@RequestBody AppRequest appRequest) {
        AppEntity appResult = appService.findAppId(appRequest.getAppId());
        if (ObjectUtils.isEmpty(appResult)) {
            return AppResponse.failed(404, "appId not found", appRequest);
        }
        int result = appService.updateDelFlag(1, appResult.getAppId());
        if (result >= 1) {
            AppEntity appEntity = appService.findAppId(appResult.getAppId());
            return AppResponse.success(appEntity);
        } else {
            return AppResponse.failed(404, "appId not found", appRequest);
        }
    }
}
