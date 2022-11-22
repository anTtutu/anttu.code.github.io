package com.anttu.demo;

import com.apifan.common.random.constant.CompetitionType;
import com.apifan.common.random.constant.CreditCardType;
import com.apifan.common.random.constant.FootballConfederation;
import com.apifan.common.random.entity.Poem;
import com.apifan.common.random.source.*;
import org.junit.Test;

/**
 * 描述
 *
 * @ClassName：TestRandomHumanInfo
 * @Description：测试随机人员信息
 * @author：Anttu
 * @Date：22/11/2022 18:23
 */
public class TestRandomHumanInfo {
    @Test
    public void testInfo() {
        //随机获取省份
        String province = AreaSource.getInstance().randomProvince();
        System.out.println(province);
        //随机获取城市(省份+城市，以逗号为分隔符)
        String city = AreaSource.getInstance().randomCity(",");
        System.out.println(city);
        //随机获取邮编
        String zipCode = AreaSource.getInstance().randomZipCode();
        System.out.println(zipCode);
        //生成1个随机中国大陆详细地址
        String address = AreaSource.getInstance().randomAddress();
        System.out.println(address);
        //生成1个随机公网IPv4地址
        String publicIpv4 = InternetSource.getInstance().randomPublicIpv4();
        //生成1个随机私有(内网)IPv4地址
        String privateIpv4 = InternetSource.getInstance().randomPrivateIpv4();
        //生成1个随机ipv6地址
        String ipv6 = InternetSource.getInstance().randomIpV6();
        //生成1个随机邮箱地址，后缀随机，邮箱用户名最大长度为10
        String n1 = InternetSource.getInstance().randomEmail(6);
        System.out.println(n1);
        //生成1个随机虚拟VISA信用卡号码
        String cc1 = PersonInfoSource.getInstance().randomCreditCardNo(CreditCardType.Visa);
        //生成1个随机虚拟MasterCard信用卡号码
        String cc2 = FinancialSource.getInstance().randomCreditCardNo(CreditCardType.MasterCard);
        //生成1个随机虚拟American Express信用卡号码
        String cc3 = FinancialSource.getInstance().randomCreditCardNo(CreditCardType.Amex);
        //生成1个随机虚拟银联信用卡号码
        String cc4 = FinancialSource.getInstance().randomCreditCardNo(CreditCardType.UnionPay);
        //生成1个随机虚拟JCB信用卡号码
        String cc5 = FinancialSource.getInstance().randomCreditCardNo(CreditCardType.JCB);
        //生成1个随机虚拟借记卡(储蓄卡)号码
        String dbc = FinancialSource.getInstance().randomDebitCardNo();
        System.out.println(dbc);
        //生成1个随机中文人名(性别随机)
        String k = PersonInfoSource.getInstance().randomChineseName();
        //生成1个随机男性中文人名
        String k2 = PersonInfoSource.getInstance().randomMaleChineseName();
        System.out.println(k2);
        //生成1个随机女性中文人名
        String k3 = PersonInfoSource.getInstance().randomFemaleChineseName();
        System.out.println(k3);
        //生成1个随机英文人名
        String l = PersonInfoSource.getInstance().randomEnglishName();
        //随机固话区号(省级行政区名称不需要包含后缀)
        String phoneCode = AreaSource.getInstance().randomPhoneCode("湖南");
        System.out.println(phoneCode);
        //随机固话号码(使用-作为分隔符，默认的分隔符是空格)
        String phoneNumber = AreaSource.getInstance().randomPhoneNumber("广东", "-");
        System.out.println(phoneNumber);
        //生成1个随机中国大陆车牌号(新能源车型)
        String carNumber = OtherSource.getInstance().randomPlateNumber(true);
        System.out.println(carNumber);
        //生成1个随机中国大陆车牌号(非新能源车型)
        String carNumber2 = OtherSource.getInstance().randomPlateNumber();
        System.out.println(carNumber2);
        //生成1个随机英文网络昵称，最大长度为8个字符
        String nickName = PersonInfoSource.getInstance().randomNickName(8);
        System.out.println(nickName);
        //生成1个随机汉字网络昵称，最大长度为8个汉字
        String nickName2 = PersonInfoSource.getInstance().randomChineseNickName(8);
        System.out.println(nickName2);
        //基于随机汉字网络昵称生成1个拼音网络昵称，最大长度为4个汉字
        String nickName3 = PersonInfoSource.getInstance().randomPinyinNickName(4);
        System.out.println(nickName3);
        //随机获取学历
        String degree = EducationSource.getInstance().randomDegree();
        //随机获取本科高校名称
        String college = EducationSource.getInstance().randomCollege();
        //随机高校专业名称
        String majorName = EducationSource.getInstance().randomMajorName();
        //随机获取小学名称
        String primarySchoolName = EducationSource.getInstance().randomPrimarySchoolName();
        //随机获取小学年级
        String primarySchoolGrade = EducationSource.getInstance().randomPrimarySchoolGrade();
        //随机获取中学名称
        String highSchoolName = EducationSource.getInstance().randomHighSchoolName();
        //随机获取中学年级
        String highSchoolGrade = EducationSource.getInstance().randomHighSchoolGrade();
        //随机班级名称
        String className = EducationSource.getInstance().randomClassName();
        //随机生成1个公司名称，地区前缀为北京
        String companyName = OtherSource.getInstance().randomCompanyName("北京");
        //随机生成1个公司部门名称
        String department = OtherSource.getInstance().randomCompanyDepartment();
        System.out.println(department);
        //随机ISBN，返回结果需要分隔符-，格式例如：978-7-XXXX-XXXX-X
        String isbn1 = OtherSource.getInstance().randomISBN(true);
        //随机ISBN，返回结果不需要分隔符，格式例如：9787XXXXXXXXX
        String isbn2 = OtherSource.getInstance().randomISBN(false);
        //随机国际商品编码，格式例如：691XXXXXXXXXX
        String ean = OtherSource.getInstance().randomEAN();
        //随机生成1个网卡MAC地址，使用:作为分隔符
        String mac = InternetSource.getInstance().randomMacAddress(":");
        //随机股票信息(沪A+深A+创业板+科创版)
        String[] stock = FinancialSource.getInstance().randomStock();
        String stockName = stock[0];
        String stockCode = stock[1];
        //随机股票信息(港股)
        String[] hkStock = FinancialSource.getInstance().randomHKStock();
        String hkStockName = hkStock[0];
        String hkStockCode = hkStock[1];
        //随机股票信息(新三板)
        String[] xsbStock = FinancialSource.getInstance().randomXsbStock();
        String xsbStockName = xsbStock[0];
        String xsbStockCode = xsbStock[1];
        //随机股票信息(北交所)
        String[] bseStock = FinancialSource.getInstance().randomBseStock();
        String bseStockName = bseStock[0];
        String bseStockCode = bseStock[1];

        //英超
        SportSource.getInstance().randomFootballTeam(CompetitionType.PREMIER_LEAGUE);
        //西甲
        SportSource.getInstance().randomFootballTeam(CompetitionType.LA_LIGA);
        //德甲
        SportSource.getInstance().randomFootballTeam(CompetitionType.BUNDESLIGA);
        //意甲
        SportSource.getInstance().randomFootballTeam(CompetitionType.SERIE_A);
        //法甲
        SportSource.getInstance().randomFootballTeam(CompetitionType.LIGUE_1);
        //荷甲
        SportSource.getInstance().randomFootballTeam(CompetitionType.EREDIVISIE);
        //CBA
        SportSource.getInstance().randomBasketballTeam(CompetitionType.CBA);
        //NBA
        SportSource.getInstance().randomBasketballTeam(CompetitionType.NBA);

        //(亚洲足联范围内)随机足球队名称
        SportSource.getInstance().randomFootballTeam(FootballConfederation.AFC);
        //(欧洲足联范围内)随机足球队名称
        SportSource.getInstance().randomFootballTeam(FootballConfederation.UEFA);
        //随机足球队名称(不限足球联合会)
        SportSource.getInstance().randomFootballTeam();
        // 热门手机号
        OtherSource.getInstance().randomMobileModel();
        //生成1个随机中国大陆手机号
        String m = PersonInfoSource.getInstance().randomChineseMobile();
        System.out.println(m);
        //生成1个随机域名，域名最大长度为16
        String dm = InternetSource.getInstance().randomDomain(16);
        System.out.println(dm);

        //生成1个随机的虚拟身份证号码，地区为广西壮族自治区，男性，年龄为18岁
        String id3 = PersonInfoSource.getInstance().randomMaleIdCard("广西壮族自治区", 18);
        System.out.println(id3);
        //生成1个随机的虚拟身份证号码，地区为河北省，女性，年龄为19岁
        String id4 = PersonInfoSource.getInstance().randomFemaleIdCard("河北省", 19);
        System.out.println(id4);

        //随机唐诗
        Poem p = OtherSource.getInstance().randomTangPoem();
        System.out.println(p.toString());

        //随机成语
        String idiom = OtherSource.getInstance().randomChineseIdiom();
        System.out.println(idiom);
    }
}
