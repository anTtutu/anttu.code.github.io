package com.anttu.user.controller;

import com.anttu.user.bean.UserVo;
import com.anttu.user.service.TestQuery;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述
 *
 * @ClassName：TestController
 * @Description：
 * @author：Anttu
 * @Date：23/8/2021 13:51
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestQuery testQuery;

    @GetMapping("/demo")
    public List<UserVo> test(){
        return testQuery.queryAll();
    }

    @GetMapping("/demo1")
    public UserVo test1 (@RequestParam("userId") Long userId) {
        return testQuery.selectByPrimaryKey(userId);
    }

    @GetMapping("/demo2")
    public List<UserVo> test2(@RequestParam("sex") Integer sex){
        return testQuery.selectBySex(sex);
    }

    @GetMapping("/demo3")
    public UserVo test3(@RequestParam("account") String account) {
        return testQuery.selectByAccount(account);
    }

    @GetMapping("/demo4")
    public boolean test4 (@RequestParam("name") String name, @RequestParam("id") Long id) {
        return testQuery.updateName(name, id);
    }

    @PostMapping("/demo5")
    public boolean test5(@RequestBody UserVo userVo) {
        return testQuery.insert(userVo);
    }
}
