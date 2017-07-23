/**
 * 
 */
package com.anttu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hk
 * @date 2017年7月23日 下午8:28:51
 */
public class DateVerifyUtils
{
	public static boolean isDate(String date)    
    {    
        /**  
         * 判断日期格式和范围  
         */    
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";    
            
        Pattern pat = Pattern.compile(rexp);      
            
        Matcher mat = pat.matcher(date);      
            
        boolean dateType = mat.matches();    

        return dateType;    
    }
	
	/** jquery 日期强校验 
	 String.prototype.IsDate=function(){
		    var regexp = /^((([0-9]{2}([02468][048])|([13579][26])))(-)(2|02)(-)(([0][1-9])|([1-2][0-9])))|((([0-9]{2}([02468][123579])|([13579][01345789])))(-)(2|02)(-)(([0][1-9])|([1][0-9])([2][0-8])))|(([0-9]{4})(-)((([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([0][1-9])|([1-2][0-9])|30|31))|(([0-9]{4})(-)((([0]{0,1}(4|6))|11))(-)(([0][1-9])|([1-2][0-9])|30))$/g;
		    return regexp.test(this);
		}
	    从现在开始我们就是这样来判断一个字符串是否为一个合法的日期了：
	     var strDate ="2005-2-29";
	     if(strDate.IsDate())
	     {
	       alert("这是一个正确的日期格式");
	     }
	     else
	     {	 
	       alert("日期格式错误");
	     }
	*/
	
	public static void main(String[] args)     
    {    
        /**  
         * 日期格式正确  
         */    
        String date1 = "2014-01-03";    
        /**  
         * 日期范围不正确---平年二月没有29号  
         */    
        String date2 = "2014-02-29";    
        /**  
         * 日期月份范围不正确---月份没有13月  
         */    
        String date3 = "2014-13-03";    
        /**  
         * 日期范围不正确---六月没有31号  
         */    
        String date4 = "2014-06-31";    
        /**  
         * 日期范围不正确 ----1月超过31天  
         */    
        String date5 = "2014-01-32";    
        /**  
         * 这个测试年份  
         */    
        String date6 = "0014-01-03";    
            
        /**  
         * 输出结果 
         */    
        System.out.println(isDate(date1));    
        System.out.println(isDate(date2));    
        System.out.println(isDate(date3));    
        System.out.println(isDate(date4));    
        System.out.println(isDate(date5));    
        System.out.println(isDate(date6));    
    } 
}
