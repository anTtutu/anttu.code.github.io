/**
 * @author hk
 * @date 2017年10月25日 下午2:37:31
 */
package com.anttu.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hk
 * @date 2017年10月25日 下午2:37:31
 */
public class TaxNoVerifyUtils
{
    /** 省市代码 */
    public static final Map<String, String> PROVINCEANDCITYS = new HashMap<>();

    /** 机构类别 */
    public static final Map<String, String> INSTITUTIONALCATEGORY = new HashMap<>();

    /** 组织机构加权因子 */
    public static final int[] WEIGHTFACTOR_REG =
    {
            3, 7, 9, 10, 5, 8, 4, 2
    };

    /** 组织机构基数 */
    public static final String BASICNUMBER_REG = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** 校验码加权因子 */
    public static final int[] WEIGHTFACTOR_CHK =
    {
            1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28
    };

    /** 校验码基数 */
    public static final String BASICNUMBER_CHK = "0123456789ABCDEFGHJKLMNPQRTUWXY";

    static
    {
        PROVINCEANDCITYS.put("11", "北京");
        PROVINCEANDCITYS.put("12", "天津");
        PROVINCEANDCITYS.put("13", "河北");
        PROVINCEANDCITYS.put("14", "山西");
        PROVINCEANDCITYS.put("15", "内蒙古");
        PROVINCEANDCITYS.put("21", "辽宁");
        PROVINCEANDCITYS.put("22", "吉林");
        PROVINCEANDCITYS.put("23", "黑龙江");
        PROVINCEANDCITYS.put("31", "上海");
        PROVINCEANDCITYS.put("32", "江苏");
        PROVINCEANDCITYS.put("33", "浙江");
        PROVINCEANDCITYS.put("34", "安徽");
        PROVINCEANDCITYS.put("35", "福建");
        PROVINCEANDCITYS.put("36", "江西");
        PROVINCEANDCITYS.put("37", "山东");
        PROVINCEANDCITYS.put("41", "河南");
        PROVINCEANDCITYS.put("42", "湖北");
        PROVINCEANDCITYS.put("43", "湖南");
        PROVINCEANDCITYS.put("44", "广东");
        PROVINCEANDCITYS.put("45", "广西");
        PROVINCEANDCITYS.put("46", "海南");
        PROVINCEANDCITYS.put("50", "重庆");
        PROVINCEANDCITYS.put("51", "四川");
        PROVINCEANDCITYS.put("52", "贵州");
        PROVINCEANDCITYS.put("53", "云南");
        PROVINCEANDCITYS.put("54", "西藏");
        PROVINCEANDCITYS.put("61", "陕西");
        PROVINCEANDCITYS.put("62", "甘肃");
        PROVINCEANDCITYS.put("63", "青海");
        PROVINCEANDCITYS.put("64", "宁夏");
        PROVINCEANDCITYS.put("65", "新疆");
        PROVINCEANDCITYS.put("71", "台湾");
        PROVINCEANDCITYS.put("81", "香港");
        PROVINCEANDCITYS.put("82", "澳门");
        PROVINCEANDCITYS.put("91", "国外");

        INSTITUTIONALCATEGORY.put("11", "机构编制机关");
        INSTITUTIONALCATEGORY.put("12", "机构编制事业单位");
        INSTITUTIONALCATEGORY.put("13", "机构编制中央编办直接管理机构编制的群众团体");
        INSTITUTIONALCATEGORY.put("19", "机构编制其他");
        INSTITUTIONALCATEGORY.put("51", "民政社会团体");
        INSTITUTIONALCATEGORY.put("52", "民政民办非企业单位");
        INSTITUTIONALCATEGORY.put("53", "民政基金会");
        INSTITUTIONALCATEGORY.put("59", "民政其他");
        INSTITUTIONALCATEGORY.put("91", "工商企业");
        INSTITUTIONALCATEGORY.put("92", "工商个体工商户");
        INSTITUTIONALCATEGORY.put("93", "工商农民专业合作社");
        INSTITUTIONALCATEGORY.put("Y1", "其他");
    }

    /**
     * 校验纳税人识别号
     *
     * @author hk
     * @date 2017年10月25日 下午2:40:37
     * @param taxPayerNo
     * @return
     */
    public static boolean validTaxPayerNo(String taxPayerNo)
    {
        if (null == taxPayerNo || "".equals(taxPayerNo))
        {
            return false;
        }
        String reg = "^[1-9]([0-9A-Z]{14}|[0-9A-Z]{17})$";
        if (!taxPayerNo.matches(reg))
        {
            return false;
        }
        if (taxPayerNo.length() == 15)
        {
            return validINTP(taxPayerNo);
        }
        else
        {
            return validUSCI(taxPayerNo);
        }
    }

    /**
     * <p>
     * 校验纳税人识别号合法性<br>
     * <p>
     * 纳税人识别号 identification number for tax payers<br>
     * <p>
     * 注：纳税人识别号一般是15位，包括地区编码6位 + 组织机构代码9位
     * <p>
     * 其中：1—2位为省、市代码
     * <p>
     * 3—6位为地区代码
     * <p>
     * 7—8位为经济性质代码
     * <p>
     * 9—10位行业代码
     * <p>
     * 11—15位为各地自设的顺序码
     *
     * @param taxPayerNo
     * @return
     * @auther hk
     * @date 2017年7月7日
     */
    public static boolean validINTP(String intpCode)
    {
        if (null == intpCode || "".equals(intpCode))
        {
            return false;
        }

        String regex = "^[1-9][0-9A-Z]{14}$";
        if (!intpCode.matches(regex))
        {
            return false;
        }

        // 校验地区编码
        String addressCode = intpCode.substring(0, 6);
        if (!validRegionCode(addressCode))
        {
            return false;
        }

        // 校验组织机构编码
        String orgCode = intpCode.substring(6, 15);
        if (!validOrganizationCode(orgCode))
        {
            return false;
        }
        return true;
    }

    /**
     * <p>
     * 统一社会信用代码合法性校验<br>
     * <p>
     * 统一社会信用代码 unified social credit identifier<br>
     * <p>
     * 统一代码由十八位的阿拉伯数字或大写英文字母（不使用I、O、Z、S、V）组成
     * 可用作纳税人识别号<br>
     * <p>
     * 编码规则：<br>
     * 第1位：登记管理部门代码（共一位字符）<br>
     * 第2位：机构类别代码（共一位字符）<br>
     * 第3位~第8位：登记管理机关行政区划码（共六位阿拉伯数字）<br>
     * 第9位~第17位：主体标识码（组织机构代码）（共九位字符）<br>
     * 第18位：校验码​（共一位字符）<br>
     *
     * @param usciCode
     * @return
     * @auther hk
     * @date 2017年7月7日
     */
    public static boolean validUSCI(String usciCode)
    {
        if (null == usciCode || "".equals(usciCode))
        {
            return false;
        }
        String reg = "^[0-9][0-9A-Z]{17}$";
        if (!usciCode.matches(reg))
        {
            return false;
        }

        String institutionalcategory = usciCode.substring(0, 2);
        String regionCode = usciCode.substring(2, 8);
        String organizationCode = usciCode.substring(8, 17);
        String checkCode = usciCode.substring(17);

        // 判断机构类别
        if (INSTITUTIONALCATEGORY.get(institutionalcategory) == null)
        {
            return false;
        }

        // 判断地区编码
        if (!validRegionCode(regionCode))
        {
            return false;
        }

        // 判断组织机构编码
        if (!validOrganizationCode(organizationCode))
        {
            return false;
        }

        // 判断最后一位校验码
        if (!validCheckCode(usciCode, checkCode))
        {
            return false;
        }

        return true;
    }

    /**
     * 校验地区编码合法性
     *
     * @param regionCode
     * @return
     * @auther hk
     * @date 2017年7月7日
     */
    public static boolean validRegionCode(String regionCode)
    {
        if (null == regionCode || "".equals(regionCode))
        {
            return false;
        }
        String reg = "^[1-9]\\d{5}$";
        if (!regionCode.matches(reg))
        {
            return false;
        }
        String provinceCode = regionCode.substring(0, 2);
        if (PROVINCEANDCITYS.get(provinceCode) == null)
        {
            return false;
        }

        return true;
    }

    /**
     * 校验组织机构编码合法性
     *
     * @param organizationCode
     * @return
     * @auther hk
     * @date 2017年7月7日
     */
    public static boolean validOrganizationCode(String organizationCode)
    {
        if (null == organizationCode || "".equals(organizationCode))
        {
            return false;
        }
        String reg = "^([0-9A-Z]){9}$";
        if (!organizationCode.matches(reg))
        {
            return false;
        }

        // 组织机构编码主体
        String organizationStr = organizationCode.substring(0, 8);
        // 组织机构编码校验码
        String checkCode = organizationCode.substring(organizationCode.length() - 1);

        int sum = 0;
        for (int i = 0; i < 8; i++)
        {
            sum += BASICNUMBER_REG.indexOf(organizationStr.charAt(i)) * WEIGHTFACTOR_REG[i];
        }
        int remainder = 11 - (sum % 11);
        String temp = "";
        if (remainder == 11)
        {
            temp = "0";
        }
        else if (remainder == 10)
        {
            temp = "X";
        }
        else
        {
            temp = remainder + "";
        }
        return checkCode.equals(temp);
    }

    /**
     * 校验统一社会信用代码最后一位校验码的合法性
     *
     * @param usciCode
     * @param checkCode
     * @return
     * @auther hk
     * @date 2017年7月7日
     */
    public static boolean validCheckCode(String usciCode, String checkCode)
    {
        if (null == usciCode || "".equals(usciCode) || null == checkCode || "".equals(checkCode))
        {
            return false;
        }
        String reg = "^[0-9][0-9A-Z]{17}$";
        if (!usciCode.matches(reg))
        {
            return false;
        }

        String usciStr = usciCode.substring(0, 17);
        int sum = 0;
        for (int i = 0; i < 17; i++)
        {
            sum += BASICNUMBER_CHK.indexOf(usciStr.charAt(i)) * WEIGHTFACTOR_CHK[i];
        }

        int remainder = 31 - (sum % 31);
        if (31 == remainder)
        {
            return checkCode.equals("0");
        }
        String temp = BASICNUMBER_CHK.charAt(remainder) + "";
        return checkCode.equals(temp);
    }
}
