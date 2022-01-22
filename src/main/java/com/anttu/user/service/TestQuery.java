package com.anttu.user.service;

import com.anttu.user.bean.UserVo;
import com.anttu.user.entity.User;

import java.util.List;

/**
 * 描述
 *
 * @ClassName：TestQuery
 * @Description：测试查询user
 * @author：Anttu
 * @Date：20/8/2021 10:14
 */
public interface TestQuery {
    List<UserVo> queryAll();

    UserVo selectByPrimaryKey(Long id);

    List<UserVo> selectBySex(Integer sex);

    UserVo selectByAccount(String name);

    boolean insert(UserVo userVo);

    boolean updateName(String name, Long id);

    boolean deleteByPrimaryKey(Long id);
}
