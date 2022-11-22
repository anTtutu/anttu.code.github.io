package com.anttu.user.test;

import com.anttu.BaseTest;
import com.anttu.tools.RandomChineseCompanyName;
import com.anttu.tools.RandomChinesePeopleName;
import com.anttu.tools.RandomPwd;
import com.anttu.user.bean.UserVo;
import com.anttu.user.service.TestQuery;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 描述
 *
 * @ClassName：TestUser
 * @Description：
 * @author：Anttu
 * @Date：23/8/2021 14:25
 */
public class TestUser extends BaseTest {

    @Resource
    private TestQuery testQuery;

    @Test
    public void testInsertUser (){
        String account = "test";

        for (int i = 0; i < 50; i++) {
            // 随机性别
            int sex = new Random().nextInt(2);

            UserVo userVo = new UserVo();
            userVo.setAccount(account + i);
            userVo.setPassword(RandomPwd.randomPasswordV3());
            userVo.setName(RandomChinesePeopleName.getRandomHumanName(sex));
            userVo.setSex(sex);
            userVo.setCompany(RandomChineseCompanyName.getRandomCompanyName());

            testQuery.insert(userVo);
        }
    }
}
