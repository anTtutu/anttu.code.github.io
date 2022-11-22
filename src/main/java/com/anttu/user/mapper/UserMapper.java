package com.anttu.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.anttu.user.bean.UserVo;
import com.anttu.user.entity.User;

/**
 * 描述
 *
 * @ClassName：UserMapper
 * @Description：用户mapper
 * @author：Anttu
 * @Date：20/8/2021 10:08
 */
public interface UserMapper {
    List<UserVo> queryAll();

    User selectByPrimaryKey(Long id);

    List<UserVo> selectBySex(@Param("sex") Integer sex);

    User selectByAccount(@Param("account") String name);

    int insert(User user);

    int updateName(@Param("name") String name, @Param("id") Long id);

    int deleteByPrimaryKey(@Param("id") Long id);
}
