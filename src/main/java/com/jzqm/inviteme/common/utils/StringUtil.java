package com.jzqm.inviteme.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;


public class StringUtil {
	 //------------------常量定义   
    /**  
     * Email正则表达式=^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$  
     */  
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";   
       
    /**  
     * 电话号码正则表达式= (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)   
     */  
    public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)" ;   
    /**
	 * 手机号码正则表达式=^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$
	 */
	public static final String MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";

    /**  
     * IP地址正则表达式 ((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))  
     */  
    public static final String IPADDRESS = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";   
  
    /**  
     * Integer正则表达式 ^-?(([1-9]\d*$)|0)  
     */  
    public static final String  INTEGER = "^-?(([1-9]\\d*$)|0)";   
    /**  
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$  
     */  
    public static final String  INTEGER_NEGATIVE = "^[1-9]\\d*|0$";   
    /**  
     * 负整数正则表达式 <=0 ^-[1-9]\d*|0$  
     */  
    public static final String  INTEGER_POSITIVE = "^-[1-9]\\d*|0$";       
    /**  
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$  
     */  
    public static final String  DOUBLE ="^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";   
    /**  
     * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　  
     */  
    public static final String  DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";   
    /**  
     * 负Double正则表达式 <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$  
     */  
    public static final String  DOUBLE_POSITIVE ="^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";    
    /**  
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁  
     */  
    public static final String  AGE="^(?:[1-9][0-9]?|1[01][0-9]|120)$";   
    /**  
     * 邮编正则表达式  [1-9]\d{5}(?!\d) 国内6位邮编  
     */  
    public static final String  CODE="[1-9]\\d{5}(?!\\d)";     
  
    /**  
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$   
     */  
    public static final String STR_ENG_NUM="^\\w+$";   
    /**  
     * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$  
     */  
    public static final String STR_ENG="^[A-Za-z]+$";   
    /**  
     * 匹配中文字符 ^[\u0391-\uFFE5]+$  
     */  
    public static final String STR_CHINA="^[\u0391-\uFFE5]+$";     
    /**  
     * 过滤特殊字符串正则  
     * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";   
     */  
    public static final String STR_SPECIAL="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";   
    /**  
     *只能输英文 数字 中文 ^[a-zA-Z0-9\u4e00-\u9fa5]+$  
     */  
    public static final String STR_ENG_CHA_NUM="^[a-zA-Z0-9\u4e00-\u9fa5]+$";   
    /**  
     *    
     */  
    /***  
     * 日期正则 支持：  
     *  YYYY-MM-DD   
     *  YYYY/MM/DD   
     *  YYYY_MM_DD   
     *  YYYYMMDD  
     *  YYYY.MM.DD的形式  
     */  
    public static final String DATE_ALL="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)" +   
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)" +   
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +   
            "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)" +   
            "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +   
            "(0?2)([-\\/\\._]?)(29)$)" +   
            "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +   
            "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|" +   
            "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";   
  
    /**  
     * URL正则表达式  
      * 匹配 http www ftp  
     */  
    public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +   
                                    "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +   
                                    "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" +   
                                    "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";    
  
    /**  
     * 身份证正则表达式  
     */  
    public static final String IDCARD="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +   
                                        "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +   
                                        "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";   
       /**  
        * 1.匹配科学计数 e或者E必须出现有且只有一次 不含Dd  
        * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$  
        */  
       public final static String SCIENTIFIC_A ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$";   
       /**  
        * 2.匹配科学计数 e或者E必须出现有且只有一次 结尾包含Dd  
        * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$  
        */  
       public final static String SCIENTIFIC_B ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$";   
       /**  
         * 3.匹配科学计数 是否含有E或者e都通过 结尾含有Dd的也通过（针对Double类型）  
         * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$  
         */  
       public final static String SCIENTIFIC_C ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$";   
       /**  
         * 4.匹配科学计数 是否含有E或者e都通过 结尾不含Dd  
         * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$  
        */  
       public final static String SCIENTIFIC_D ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$";   
       
       
       public static final String REX_NOT_NUM ="\\D*";//非数字
       public static final String REX_NOT_PER ="[^\\d\\.]";//非百分比
   	   public static final String REX_NOT_PRICE ="[^\\d\\-\\.]*";//非金额
   	   
   	   public static final String URL_Protocol = ".*\\/\\/([^\\/\\:]*)";//url协议
   	   
   	   public static final String BRACKET = "[\\[\\]]";//中括号
   	
       
////------------------验证方法        
    /**  
     * 判断字段是否为空 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static synchronized boolean StrisNull(String str) {   
        return null == str || str.trim().length() <= 0 ? true : false ;   
    }   
    /**  
     * 判断字段是非空 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean StrNotNull(String str) {   
        return !StrisNull(str) ;   
    }   
    /**  
     * 字符串null转空  
     * @param str  
     * @return boolean  
     */  
    public static  String nulltoStr(String str) {   
        return StrisNull(str)?"":str;   
    }      
    /**  
     * 字符串null赋值默认值   
     * @param str    目标字符串  
     * @param defaut 默认值  
     * @return String  
     */  
    public static  String nulltoStr(String str,String defaut) {   
        return StrisNull(str)?defaut:str;   
    }   
    /**  
     * 判断字段是否为Email 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isEmail(String str) {   
        return Regular(str,EMAIL);   
    }   
    /**  
     * 判断是否为电话号码 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isPhone(String str) {   
        return Regular(str,PHONE);   
    }   
    /**  
     * 判断是否为手机号码 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isMobile(String str) {   
        return Regular(str,MOBILE);   
    }   
    /**  
     * 判断是否为Url 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isUrl(String str) {   
        return Regular(str,URL);   
    }      
    /**  
     * 判断是否为IP地址 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isIpaddress(String str) {   
        return Regular(str,IPADDRESS);   
    }      
    /**   
     * 判断字段是否为数字 正负整数 正负浮点数 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isNumber(String str) {   
        return Regular(str,DOUBLE);   
    }   
    /**  
     * 判断字段是否为INTEGER  符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isInteger(String str) {   
        return Regular(str,INTEGER);   
    }   
    /**  
     * 判断字段是否为正整数正则表达式 >=0 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isINTEGER_NEGATIVE(String str) {   
        return Regular(str,INTEGER_NEGATIVE);   
    }   
    /**  
     * 判断字段是否为负整数正则表达式 <=0 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isINTEGER_POSITIVE(String str) {   
        return Regular(str,INTEGER_POSITIVE);   
    }      
    /**  
     * 判断字段是否为DOUBLE 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isDouble(String str) {   
        return Regular(str,DOUBLE);   
    }   
    /**   
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isDOUBLE_NEGATIVE(String str) {   
        return Regular(str,DOUBLE_NEGATIVE);   
    }   
    /**  
     * 判断字段是否为负浮点数正则表达式 <=0 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isDOUBLE_POSITIVE(String str) {   
        return Regular(str,DOUBLE_POSITIVE);   
    }      
    /**  
     * 判断字段是否为日期 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isDate(String str) {   
        return Regular(str,DATE_ALL);   
    }      
    /**  
     * 判断字段是否为年龄 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isAge(String str) {   
        return Regular(str,AGE) ;   
    }   
    /**  
     * 判断字段是否超长  
     * 字串为空返回fasle, 超过长度{leng}返回ture 反之返回false  
     * @param str  
     * @param leng  
     * @return boolean  
     */  
    public static  boolean isLengOut(String str,int leng) {        
        return StrisNull(str)?false:str.trim().length() > leng ;   
    }   
    /**  
     * 判断字段是否为身份证 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isIdCard(String str) {   
        if(StrisNull(str)) return false ;   
        if(str.trim().length() == 15 || str.trim().length() == 18) {   
                return Regular(str,IDCARD);   
        }else {   
            return false ;   
        }   
           
    }   
    /**  
     * 判断字段是否为邮编 符合返回ture  
     * @param str  
     * @return boolean  
     */  
    public static  boolean isCode(String str) {   
        return Regular(str,CODE) ;   
    }   
    /**  
     * 判断字符串是不是全部是汉字  
     * @param str  
     * @return boolean  
     */  
    public static boolean isChina(String str) {   
        return Regular(str,STR_CHINA) ;   
    }   
    /**  
     * 判断字符串是不是全部是英文字母  
     * @param str  
     * @return boolean  
     */  
    public static boolean isEnglish(String str) {   
        return Regular(str,STR_ENG) ;   
    }   
    /**  
     * 判断字符串是不是全部是英文字母+数字  
     * @param str  
     * @return boolean  
     */  
    public static boolean isENG_NUM(String str) {   
        return Regular(str,STR_ENG_NUM) ;   
    }   
   
    /**  
     * 过滤特殊字符串 返回过滤后的字符串  
     * @param str  
     * @return boolean  
     */  
    public static  String filterStr(String str) {   
    	str.replaceAll(" ", "");
        Pattern p = Pattern.compile(STR_SPECIAL);   
        Matcher m = p.matcher(str);   
        return   m.replaceAll("").trim();   
    }    
    
    /**
     * 过滤除中文、英文、数字之外其他字符
     * @param str
     * @return
     */
    public static String filterOther(String str) {
    	Pattern p = Pattern.compile("[a-zA-Z0-9\\u4e00-\\u9fa5]");
    	Matcher m =p.matcher(str);
    	StringBuilder sb =new StringBuilder();
    	while (m.find()) {
			sb.append(m.group());
		}
    	return sb.toString().trim();
    	
    }
    /**  
     * 匹配是否符合正则表达式pattern 匹配返回true  
     * @param str 匹配的字符串  
     * @param pattern 匹配模式  
     * @return boolean  
     */  
    private static  boolean Regular(String str,String pattern){   
        System.out.println("pattern="+pattern);   
        if(null == str || str.trim().length()<=0)   
            return false;            
        Pattern p = Pattern.compile(pattern);   
        Matcher m = p.matcher(str);   
        return m.matches();   
    }   
    /**  
     * 判断是不是科学计数法 如果是 返回true  
     * 匹配科学计数 e或者E必须出现有且只有一次 结尾不含D  
     * 匹配模式可参考本类定义的 SCIENTIFIC_A，SCIENTIFIC_B,SCIENTIFIC_C,SCIENTIFIC_D  
     * 若判断为其他模式可调用 Regular(String str,String pattern)方法  
     * @param str 科学计数字符串  
     * @return boolean  
     */  
    public static  boolean isScientific(String str){   
        if(StrisNull(str))   
            return false;    
        return Regular(str,SCIENTIFIC_A);   
    } 
    public static String toString(Object obj){
    	return obj==null?"":obj.toString();
    }
    public static boolean isEmpty(Object obj){
    	return obj==null || "".equals(obj);
    }
    
    /**
     * 随机生成数字与字母的组合
     * @param len
     * @return
     */
    public static String randomString(int len){
		StringBuilder random= new StringBuilder();
		while (random.length()<len) {
			String uuid = UUID.randomUUID().toString();
			random.append(uuid.replace("-",""));
		}
		return random.substring(0,len-1);
	}
    
    

/**格式化字段**/
    public static String convertString(String str, Boolean beginUp){
        char[] ch = str.toCharArray();
		StringBuffer sbf = new StringBuffer();
        for(int i=0; i< ch.length; i++){
            if(i == 0 && beginUp){//如果首字母需大写
                sbf.append(charToUpperCase(ch[i]));
            }else{
                sbf.append(charToLowerCase(ch[i]));
            }
        }
        return sbf.toString();
    }
    /**转大写**/
    public static char charToUpperCase(char ch){
        if(ch <= 122 && ch >= 97){
            ch -= 32;
        }
        return ch;
    }
    /***转小写**/
    public static char charToLowerCase(char ch){
        if(ch <= 90 && ch >= 65){
            ch += 32;
        }
        return ch;
    }

    /**
     * 生成A~Z的大写字母
     * 函数功能说明 
     * miracle  2014年5月28日
     * 修改者名字 
     * 修改日期 
     * 修改内容
     * @return
     */
    public static List<String> getUppercaseLetters(){
		return StringUtil.getLetters('A', 'Z');
    }
    /**
     * 生成a~z的小写字母
     * 函数功能说明 
     * miracle  2014年5月28日
     * 修改者名字 
     * 修改日期 
     * 修改内容
     * @return
     */
    public static List<String> getLowercaseLetters(){
		return StringUtil.getLetters('a', 'z');
    }
    /**
     * 生成连续的字母（A~Z  a~z 任意区间）
     * 函数功能说明 
     * miracle  2014年5月28日
     * 修改者名字 
     * 修改日期 
     * 修改内容
     * @param from
     * @param to
     * @return
     */
    public static List<String> getLetters(char from,char to){
    	List<String> list = new ArrayList<String>();
    	int f = (int)from;
		int t = (int)to;
		for (int i = f; i <= t; i++) {
			char value = (char)i;
//			System.out.println(value);
			list.add(value+"");
		}
		return list;
    }
    /**
    * 返回首字母
    * 
    * 用pinyin4j
    * @param strChinese
    * @param bUpCase
    * @return
    */
    public static String getPYIndexStr(String strChinese, boolean bUpCase){
    	return PinyinUtils.getFirstSpell(strChinese, bUpCase);
    	
    }

    /**
     * 方法功能说明 需要单独处理的特殊字符
     * 修改者  修改日期
     * 修改说明
     * @param keyword
     * @return
     */
    public static String replaceSpecialStr(String keyword) {   
    	keyword = keyword.replaceAll("\\\\", "\\\\\\\\");
		keyword = keyword.replaceAll("%", "\\\\%");
		keyword = keyword.replaceAll("_", "\\\\_");
    	return keyword;
    }
    
    /**
     * 方法功能说明 还原需要单独处理的特殊字符
     * 修改者  修改日期
     * 修改说明
     * @param keyword
     * @return
     */
    public static String reconstructSpecialStr(String keyword) {   
		keyword = keyword.replaceAll("\\\\%" ,"%");
		keyword = keyword.replaceAll("\\\\_", "_");
		keyword = keyword.replaceAll("\\\\\\\\", "\\\\");
    	return keyword;
    }
    
    /**
     * pageindex参数验证
     * 修改者  修改日期
     * 修改说明
     * @return
     */
    public static Map testPageIndex(String pageIndex) {   
    	//返回参数的map
    	Map<String , String> map =new HashMap<String , String>();
    	//如果参数里前3个字母是-v2
    	if(pageIndex.indexOf("-v2")==0){
    		map.put("version", "v2");
        	pageIndex=pageIndex.substring(3);
    	}else{
    		map.put("version", "v1");
    	}
    	//参数验证
  		if(!StringUtil.isINTEGER_POSITIVE(pageIndex)){
  			pageIndex = "1";
  		}else{
  			if(pageIndex != null && pageIndex.length()> 1 && pageIndex.length() <= 10 ){
  				pageIndex = pageIndex.substring(1);
  			}else{
  				pageIndex = "1";
  			}
  		}
		map.put("pageIndex", pageIndex);
    	return map;
    }
    
    public static String formartList(List list,String append){
    	
    	StringBuilder builder = new StringBuilder();
    	if(list!=null && (!list.isEmpty())){
    		for (Object object : list) {
        		if(!isEmpty(object)){
        			builder.append(String.valueOf(object)).append(',');
        		}
    		}
    		builder.deleteCharAt(builder.length()-1);
    	}
    	return builder.toString();
    }
    
    /**  
     * 判断是否是一个中文汉字  
     *   
     * @param c  
     *            字符  
     * @return true表示是中文汉字，false表示是英文字母  
     * @throws UnsupportedEncodingException  
     *             使用了JAVA不支持的编码格式  
     */  
    public static boolean isChineseChar(char c)
            throws UnsupportedEncodingException {
        // 如果字节数大于1，是汉字   
        // 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了   
        return String.valueOf(c).getBytes("utf-8").length > 1;   
    }
    
    /**
     * 计算当前String字符串所占的总Byte长度
     * @param args 
     * 				要截取的字符串
     * @return 
     * 				返回值int型，字符串所占的字节长度，如果args为空或者“”则返回0
     * @throws UnsupportedEncodingException
     */
    public static int getStringByteLenths(String args) throws UnsupportedEncodingException{
    	return args!=null&&args!=""? args.getBytes("utf-8").length:0;
    }
    
    /**
     * 获取与字符串每一个char对应的字节长度数组
     * @param  args  
     * 				要计算的目标字符串
     * @return int[]
     * 				数组类型，返回与字符串每一个char对应的字节长度数组
     * @throws UnsupportedEncodingException
     */
    public static int[] getByteLenArrays(String args) throws UnsupportedEncodingException{
    	char[] strlen=args.toCharArray();
    	int[] charlen=new int[strlen.length];
    	for (int i = 0; i < strlen.length; i++) {
    		charlen[i]=String.valueOf(strlen[i]).getBytes("utf-8").length;
		}
    	return charlen;
    }
    
    /**  
     * 按字节截取字符串 ，指定截取起始字节位置与截取字节长度
     *   
     * @param orignal  
     *            	要截取的字符串  
     *            	截取Byte长度；
     * @return 
     * 				截取后的字符串  
     * @throws UnsupportedEncodingException  
     *              使用了JAVA不支持的编码格式  
     */  
    public static String substringByte(String orignal,int start, int count){
    	
    	//如果目标字符串为空，则直接返回，不进入截取逻辑；
    	if(orignal==null || "".equals(orignal))return orignal;
    	//截取Byte长度必须>0
    	if (count <= 0) return orignal;
    	//截取的起始字节数必须比
	    if(start<0) start=0;
    	//目标char Pull buff缓存区间；
    	StringBuffer buff = new StringBuffer();
    	 
    	 try {
    		 
    		//截取字节起始字节位置大于目标String的Byte的length则返回空值
    		if (start >= getStringByteLenths(orignal)) return null;
    		// int[] arrlen=getByteLenArrays(orignal);
    		int len=0;
    		char c;
    		//遍历String的每一个Char字符，计算当前总长度
    		//如果到当前Char的的字节长度大于要截取的字符总长度，则跳出循环返回截取的字符串。
    		 for (int i = 0; i < orignal.toCharArray().length; i++) {
    			 c=orignal.charAt(i);
    			 //当起始位置为0时候
    			 if(start==0){
    				 len+=String.valueOf(c).getBytes("utf-8").length;
        			 if(len<=count) buff.append(c);
        			 else break;
        			 
    			 }else{
    				 
    				 //截取字符串从非0位置开始
    				 len+=String.valueOf(c).getBytes("utf-8").length;
    				 if(len>=start&&len<=start+count){
    					 buff.append(c);
    				 }
    				 if(len>start+count) break;
    			 }
			 }
	    	 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	 //返回最终截取的字符结果;
    	 //创建String对象，传入目标char Buff对象
    	 return new String(buff);
    }
  
    /**
     * 截取指定长度字符串
     * @param orignal
     * 				要截取的目标字符串
     * @param count 
     * 				指定截取长度
     * @return
     * 				返回截取后的字符串
     */
    public static String substringByte(String orignal, int count){
    	return substringByte(orignal,0,count);
    }
    
	public static String createId()
	{
		//生成唯一id
        String id= UUID.randomUUID().toString();
        
        //替换uuid中的"-"
        id=id.replace("-", "");
        return id;

	}
	
	
	public static String createCode()
	{
		int code = (int)((Math.random()*9+1)*100000);
		String  codeStr = ""+code;
		return codeStr;
	}
   
  
    public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		
        
    } 
  
}
