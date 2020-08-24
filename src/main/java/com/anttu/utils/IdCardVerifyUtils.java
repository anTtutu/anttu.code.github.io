/**
 * 
 */
package com.anttu.utils;

import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author hk
 * @date 2017年7月23日 下午8:08:44
 */
public class IdCardVerifyUtils
{
	/** 大陆地区地域编码最大值 **/
	public static final int MAX_MAINLAND_AREACODE = 659004;
	/** 大陆地区地域编码最小值 **/
	public static final int MIN_MAINLAND_AREACODE = 110000;
	/** 香港地域编码值 **/
	public static final int HONGKONG_AREACODE = 810000;
	/** 台湾地域编码值 **/
	public static final int TAIWAN_AREACODE = 710000;
	/** 澳门地域编码值 **/
	public static final int MACAO_AREACODE = 820000;

	/** 数字正则 **/
	public static final String REGEX_NUM = "^[0-9]*$";
	/** 闰年生日正则 **/
	public static final String REGEX_BIRTHDAY_IN_LEAP_YEAR = "^((19[0-9]{2})|(200[0-9])|(201[0-5]))((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))$";
	/** 平年生日正则 **/
	public static final String REGEX_BIRTHDAY_IN_COMMON_YEAR = "^((19[0-9]{2})|(200[0-9])|(201[0-5]))((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))$";

	private static final Set<String> BLACK_SET = new HashSet<String>()
	{
		private static final long serialVersionUID = 48136604486603324L;
		{
			add("111111111111111");
		}
	};

	/**
	 * <p>
	 * 身份证格式强校验
	 * </p>
	 * <p>
	 * 身份证号码验证 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
	 * 八位数字出生日期码，三位数字顺序码和一位数字校验码。 2、地址码(前六位数）
	 * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 3、出生日期码（第七位至十四位）
	 * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
	 * 4、顺序码（第十五位至十七位） 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。 5、校验码（第十八位数）
	 * （1）十七位数字本体码加权求和公式 S =
	 * Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和 Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5
	 * 8 4 2 1 6 3 7 9 10 5
	 * 8 4 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3
	 * 2
	 * </p>
	 */
	public static final boolean strongVerifyIdNumber(String idNumber)
	{
		if (StringUtils.isBlank(idNumber))
		{
			return false;
		}
		idNumber = idNumber.trim();

		if (BLACK_SET.contains(idNumber))
		{
			return false;
		}
		if (!checkIdNumberRegex(idNumber))
		{
			return false;
		}
		if (!checkIdNumberArea(idNumber.substring(0, 6)))
		{
			return false;
		}
		idNumber = convertFifteenToEighteen(idNumber);
		if (!checkBirthday(idNumber.substring(6, 14)))
		{
			return false;
		}
		if (!checkIdNumberVerifyCode(idNumber))
		{
			return false;
		}
		return true;
	}

	/**
	 * 身份证正则校验
	 */
	private static boolean checkIdNumberRegex(String idNumber)
	{
		return Pattern.matches("^([0-9]{17}[0-9Xx])|([0-9]{15})$", idNumber);
	}

	/**
	 * 身份证地区码检查
	 */
	private static boolean checkIdNumberArea(String idNumberArea)
	{
		int areaCode = Integer.parseInt(idNumberArea);
		if (areaCode == HONGKONG_AREACODE || areaCode == MACAO_AREACODE || areaCode == TAIWAN_AREACODE)
		{
			return true;
		}
		if (areaCode <= MAX_MAINLAND_AREACODE && areaCode >= MIN_MAINLAND_AREACODE)
		{
			return true;
		}
		return false;
	}

	/**
	 * 将15位身份证转换为18位
	 */
	private static String convertFifteenToEighteen(String idNumber)
	{
		if (15 != idNumber.length())
		{
			return idNumber;
		}
		idNumber = idNumber.substring(0, 6) + "19" + idNumber.substring(6, 15);
		idNumber = idNumber + getVerifyCode(idNumber);
		return idNumber;
	}

	/**
	 * 根据身份证前17位计算身份证校验码
	 */
	private static String getVerifyCode(String idNumber)
	{
		if (!Pattern.matches(REGEX_NUM, idNumber.substring(0, 17)))
		{
			return null;
		}

		String[] valCodeArr =
		{
				"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"
		};

		String[] wi =
		{
				"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"
		};

		int sum = 0;
		for (int i = 0; i < 17; i++)
		{
			sum = sum + Integer.parseInt(String.valueOf(idNumber.charAt(i))) * Integer.parseInt(wi[i]);
		}
		return valCodeArr[sum % 11];
	}

	/**
	 * 身份证出生日期嘛检查
	 */
	private static boolean checkBirthday(String idNumberBirthdayStr)
	{
		Integer year = null;
		try
		{
			year = Integer.valueOf(idNumberBirthdayStr.substring(0, 4));
		}
		catch (Exception e)
		{
			System.out.println("checkBirthday failed,error:" + e);
		}
		if (null == year)
		{
			return false;
		}
		if (isLeapYear(year))
		{
			return Pattern.matches(REGEX_BIRTHDAY_IN_LEAP_YEAR, idNumberBirthdayStr);
		}
		else
		{
			return Pattern.matches(REGEX_BIRTHDAY_IN_COMMON_YEAR, idNumberBirthdayStr);
		}
	}

	/**
	 * 判断是否为闰年
	 */
	private static boolean isLeapYear(int year)
	{
		return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
	}

	/**
	 * 身份证校验码检查
	 */
	private static boolean checkIdNumberVerifyCode(String idNumber)
	{
		return getVerifyCode(idNumber).equalsIgnoreCase(idNumber.substring(17));
	}
	
	/** jquery 版本的身份证号强校验
	<script type="text/javascript" src="./jquery-1.6.2.js"></script>
    <script type="text/javascript">
          function checkCertificateNo(){
                var certificateNo = $("#certificateNo").val();//身份证号码
                if(certificateNo.length != 18){
                      alert("身份证号码无效，请使用第二代身份证！！！"); 
                }else{
                      var address = certificateNo.substring(0,6);//6位，地区代码
                      var birthday = certificateNo.substring(6,14);//8位，出生日期
                      var sequenceCode =  certificateNo.substring(14,17);//3位，顺序码：奇为男，偶为女
                      var checkCode =  certificateNo.substring(17);//1位，校验码：检验位
                      console.log("身份证号码:"+certificateNo+"、地区代码:"+address+"、出生日期:"+birthday+"、顺序码:"+sequenceCode+"、校验码:"+checkCode);

                      var province={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};

                      var year =  birthday.substring(0,4);   
                      var month = birthday.substring(4,6);   
                      var day = birthday.substring(6);
                      var tempDate = new Date(year,parseFloat(month)-1,parseFloat(day)); 
                      if(province[parseInt(address.substr(0,2))] == null || (tempDate.getFullYear()!=parseFloat(year) || tempDate.getMonth()!=parseFloat(month)-1 || tempDate.getDate()!=parseFloat(day))){//这里用getFullYear()获取年份，避免千年虫问题
                            alert("身份证号码无效，请重新输入！！！");
                      }else{
                            var weightedFactors = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];//加权因子   
                            var valideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，其中10代表X
                            var certificateNoArray =certificateNo.split("");// 得到身份证数组
                            var sum = 0;// 声明加权求和变量
                            if (certificateNoArray[17].toLowerCase() == 'x') {
                                  certificateNoArray[17] = 10;// 将最后位为x的验证码替换为10  
                            }
                           for ( var i = 0; i < 17; i++) {
                                 sum += weightedFactors[i] * certificateNoArray[i];// 加权求和   
                          }
                          valCodePosition = sum % 11;// 得到验证码所在位置
                         if (certificateNoArray[17] == valideCode[valCodePosition]) {
                                var sex = "男";
                               if(sequenceCode%2==0){
                                      sex = "女";
                                }
                               alert("身份证号码有效，性别为："+sex+"！");
                          } else {
                               alert("身份证号码无效，请重新输入！！！");
                          }
                     }
               }
          }
     </script>
     */

	public static void main(String[] args)
	{
		System.out.println(strongVerifyIdNumber("111111111111111"));
	}
}
