package com.anttu.user.service.impl;

import com.anttu.user.bean.UserVo;
import com.anttu.user.entity.User;
import com.anttu.user.mapper.UserMapper;
import com.anttu.user.service.TestQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @ClassName：TestQueryImpl
 * @Description：测试查询user impl
 * @author：Anttu
 * @Date：20/8/2021 10:15
 */
@Service
@CacheConfig(cacheManager = "redisCacheManager")
public class TestQueryImpl implements TestQuery {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(key = "'queryAll'", sync = true)
    @Override
    public List<UserVo> queryAll() {
        return userMapper.queryAll();
    }

    @Cacheable(key = "#id", sync = true)
    @Override
    public UserVo selectByPrimaryKey(Long id) {
        User user = userMapper.selectByPrimaryKey(id);

        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setAccount(user.getAccount());
        userVo.setPassword(user.getPassword());
        userVo.setName(user.getName());
        userVo.setSex(user.getSex());
        userVo.setCompany(user.getCompany());

        return userVo;
    }

    @Cacheable(key = "#sex", sync = true)
    @Override
    public List<UserVo> selectBySex(Integer sex) {
        return userMapper.selectBySex(sex);
    }

    @Cacheable(key = "#name", sync = true)
    @Override
    public UserVo selectByAccount(String name) {
        return userMapper.selectByAccount(name);
    }

    @CachePut(key = "#userVo.getId()")
    @Override
    public boolean insert(UserVo userVo) {
        int result = userMapper.insert(userVo);
        return result > 0;
    }

    @CachePut(key = "#id")
    @Override
    public boolean updateName(String name, Long id) {
        int result = userMapper.updateName(name, id);
        return result > 0;
    }

    @CacheEvict(key = "#id")
    @Override
    public boolean deleteByPrimaryKey(Long id) {
        int result = userMapper.deleteByPrimaryKey(id);
        return result > 0;
    }
}
