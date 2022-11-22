package com.anttu.dynamic.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 描述
 *
 * @ClassName：AppEntity
 * @Description：App实体
 * @author：Anttu
 * @Date：28/10/2021 20:39
 */
@Data
public class AppEntity {
    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    /**
     * app 名称，全局唯一
     */
    private String appName;
    /**
     *  app id，全局唯一
     */
    private String appId;
    /**
     *  app 密钥
     */
    private String appSecret;
    /**
     * 是否可用 0：可用 1：不可用 默认值0
     */
    private int isFlag;
    /**
     * 鉴权令牌，生成，有效期10分钟
     */
    private String accessToken;
    /**
     * 是否删除 0：未删除  1：已删除 默认值0
     */
    private int delFlag;
    /**
     * 创建时间
     * default current_timestamp
     */
    private Date createTime;
    /**
     * 修改时间
     * on update current_timestamp
     */
    private Date updateTime;
}
