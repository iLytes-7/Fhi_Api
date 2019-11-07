package org.fh.api.util;

import com.mashape.unirest.http.Unirest;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：常用工具
 * 作者：yyh-Oracles
 * csyyh
 */
public class Tools {
	
	/**
	 * 随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * 随机生成四位数验证码 
	 * @return
	 */
	public static int getRandomNum4(){
		 Random r = new Random();
		 return r.nextInt(9000)+1000;
	}

	/**
	 * 根据时间生成订单编号
	 * @return 订单编号
	 */
	public static String getOrderNo(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate=sdf.format(new Date());
		String result="";
		Random random=new Random();
		for(int i=0;i<3;i++){
			result+=random.nextInt(10);
		}
		return newDate+result;
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	


	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobileNumber
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }


	/**
	 * 发送短信
	 * @param phoneNumbers 手机号码
	 * @param code  验证码
	 * @throws Exception
	 */
	@Async
	public static void sendSMS(String phoneNumbers,String code) throws Exception {
		String accessKeyId = "LTAIi53rr43M3m3h";
		String accessSecret = "SvMYVrx1MXZ24ekLoOqVCsXFfKjJ0u";
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区
		java.util.Map<String, String> paras = new java.util.HashMap<String, String>();
		// 1. 系统参数
		paras.put("SignatureMethod", "HMAC-SHA1");
		paras.put("SignatureNonce", java.util.UUID.randomUUID().toString());
		paras.put("AccessKeyId", accessKeyId);
		paras.put("SignatureVersion", "1.0");
		paras.put("Timestamp", df.format(new java.util.Date()));
		paras.put("Format", "XML");
		// 2. 业务API参数
		paras.put("Action", "SendSms");
		paras.put("Version", "2017-05-25");
		paras.put("RegionId", "cn-hangzhou");
		paras.put("PhoneNumbers", phoneNumbers);
		paras.put("SignName", "菲华国际");
		paras.put("TemplateParam", "{\"code\":\""+code+"\"}");
		paras.put("TemplateCode", "SMS_166670399");
		paras.put("OutId", "123");
		// 3. 去除签名关键字Key
		if (paras.containsKey("Signature"))
			paras.remove("Signature");
		// 4. 参数KEY排序
		java.util.TreeMap<String, String> sortParas = new java.util.TreeMap<String, String>();
		sortParas.putAll(paras);
		// 5. 构造待签名的字符串
		java.util.Iterator<String> it = sortParas.keySet().iterator();
		StringBuilder sortQueryStringTmp = new StringBuilder();
		while (it.hasNext()) {
			String key = it.next();
			sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
		}
		String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append("GET").append("&");
		stringToSign.append(specialUrlEncode("/")).append("&");
		stringToSign.append(specialUrlEncode(sortedQueryString));
		String sign = sign(accessSecret + "&", stringToSign.toString());
		// 6. 签名最后也要做特殊URL编码
		String signature = specialUrlEncode(sign);
		System.out.println(paras.get("SignatureNonce"));
		System.out.println("\r\n=========\r\n");
		System.out.println(paras.get("Timestamp"));
		System.out.println("\r\n=========\r\n");
		System.out.println(sortedQueryString);
		System.out.println("\r\n=========\r\n");
		System.out.println(stringToSign.toString());
		System.out.println("\r\n=========\r\n");
		System.out.println(sign);
		System.out.println("\r\n=========\r\n");
		System.out.println(signature);
		System.out.println("\r\n=========\r\n");
		// 最终打印出合法GET请求的URL
		System.out.println("http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp);
		String result = Unirest.get("http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp).asString().getBody();
		System.out.println(result);
	}




	public static String specialUrlEncode(String value) throws Exception {
		return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
	}
	public static String sign(String accessSecret, String stringToSign) throws Exception {
		javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
		mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));
		byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
		return new sun.misc.BASE64Encoder().encode(signData);
	}
	 



	public static void main(String[] args) {
		System.out.println(getRandomNum());
	}

}
