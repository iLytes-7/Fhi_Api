/**
 * @FileName:SysCode.java 
 * @author shishi.qu
 * @version 2017年4月12日 上午11:18:38
 * @Description
 * @Copyright Notice: 
 * Copyright (c) 2016 Envision, Inc. All  Rights Reserved.
 * This software is published under the terms of the Envision Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
*/
package org.fh.api.dialect;

import org.apache.commons.lang3.StringUtils;

/***
  * 系统错误码以及返回信息
  * @author shishi.qu
  *
  */
public enum ErrorCode {
	DEMO_TEST(-1,"这是%s,今天%s"),
	SESSION_TIMEOUT(10001,"会话超时"),
	NOT_LOGIN(10002,"用户未登录或登录失效，请重新登录"),
	SYS_ERROR(10003,"系统繁忙"),
	DONT_HAVE_JIFEN(66666,"积分剩余不足"),
	UPDATE_ERROR(10004,"更新失败"),
	PARAMETER_NOT_NULL(10005,"参数不能为空"),
	QUERY_ERROR(10006,"查询失败"),
	DELETE_ERROR(10007,"删除失败"),
	SAVE_ERROR(10008,"保存失败"),
	NOT_EXIST(10009,"用户不存在"),
	NOT_PASSWORD_EXIST(100091,"未注册或者未设置密码"),
	LOGIN_ERROR(10010,"用户名或密码错误"),
	GET_DYNAMIC_CODE_NUM_ERROR(10011,"获取验证码次数超限"),
	INPUT_PWD_NUM_ERROR(10012,"输入密码错误次数超限"),
	MOBILE_ERROR(10013,"手机号码输入有误，请重新输入"),
	EMAIL_ERROR(10014,"邮箱输入有误，请重新输入"),
	PARAMETER_ERROR(10015,"参数校验错误"),
	NOT_BAND_PERSONAL_INFO(10016,"个人信息未绑定"),
	DYNAMIC_CODE_ERROR(10017,"动态验证码错误，请重新输入"),
	CARD_NUM_ERROR(10018,"证件号无效，请重新输入"),
	BEANCOPY_ERROR(10019,"对象属性COPY异常"),
	NO_LOGIN(10020,"未登录或会话超时"),
	NO_WX_AUTH(100201,"未授权获取用户信息"),

	/***系统配置异常10100-10300  */
	QUERY_CONFIG_ERROR(10100,"查询系统配置信息失败"),
	QUERY_REGION_ERROR(10101,"查询城市失败"),
	DYNAMIC_CONFI_NULL(10102,"ID[%s]的信息不存在"),
	UPDATE_SORT(10103,"该配置信息已是该节点%s"),
	PI_REPORT_NULL(10104,"报告信息不存在"),
	PI_CREATE_ERROR(10105,"创建时保存失败"),
	PI_EMAIL_NONE(10106,"评估邮箱为空"),
	PI_FIRST_NAME_NONE(10107,"评估firstName为空"),
	PI_LAST_NAME_NONE(10108,"评估lastName为空"),
	PI_USERID_NONE(10109,"评估用户ID为空"),
	PI_CREATE_REQUEST_ERROR(10110,"创建时请求PI异常"),
	DYNAMIC_CONFI_REQUEST_NULL(10111,"请求参数为空"),
	DYNAMIC_CODE_EXIST(10112,"key[%s]已存在"),
	DYNAMIC_CONFIG_ERROR(10113,"获取配置[%s]失败"),
	BEISEN_CREATE_ISNULL(10114,"参数[%s]不合法"),
	/***职位异常20000-20100  */
	CHECK_PARAMETER_ERROR(20000,"前端参数异常"),
	CHECK_PARAMETERS_ERROR(20000,"前端参数异常:%s"),
	QUERY_PROJECT_ERROR(20001,"查询招聘项目失败"),
	QUERY_PROJECTPLACE_ERROR(20002,"查询项目面试地点失败"),
	QUERY_PLACEADDRESS_ERROR(20003,"查询面试详细地址失败"),
	QUERY_JOBBASICINFO_ERROR(20004,"查询职位失败"),
	QUERY_JOBINTERVIEWINFO_ERROR(20005,"此招聘方向尚未配置面试信息"),
	COPY_JOBBASICINFO_ERROR(20006,"保存职位基本信息发生异常"),
	BIND_RECRUITCITY_ERROR(20007,"绑定招聘城市异常：非限定招聘城市的职位不需要维护招聘城市"),
	QUERY_WORKERPROJECT_ERROR(20008,"查询人员、项目、招聘方向关联关系失败"),
	SAVE_HRJOB_ERROR(20009,"关联HR招聘方向前请先添加HR到招聘项目"),
	QUERY_JOBINT_ERROR(20010,"查询招聘方向面试官配置失败"),
	QUERY_JOBTYPEWORKER_ERROR(20011,"查询职位类型与人员关联关系失败"),
	QUERY_JOBTYPEMODE_ERROR(20012,"查询职位类型与评价模板关联关系失败"),
	QUERY_JOBCHECK_ERROR(20013,"选择方向的面试官不一致，不能一起安排"),
	QUERY_JOBINFOCHECK_ERROR(20014,"选择方向的面试方式不一致，不能一起安排"),
	QUERY_GROUPCHECK_ERROR(20015,"查询招聘方向失败"),
	QUERY_WORKERCHECK_ERROR(20016,"匹配人员角色类型失败：interviewer:面试官;hr;picker:筛选官"),
	SAVE_CATEGORY_INFO_ERROR(20017,"招聘方向%s,已经配置过面试方式不能新增"),
	QUERY_CATEGORY_INFO_ERROR(20018,"招聘方向%s,查询失败，请联系系统管理员"),
	QUERY_PLAN_ERROR(20019,"查询面试安排失败，ID：%s"),
	QUERY_PLANCANDI_ERROR(20020,"暂无要选择时间的面试安排"),
	QUERY_PLANDETAIL_ERROR(20021,"查询面试安排场次明细失败"),
	CHECK_QTY_ERROR(20022,"选择的场次人数已满，请重新选择其他场次"),
	CHECK_PLANQTY_ERROR(20023,"面试安排的应聘者数量与安排场次的总人数不一致，不能提交"),
	CHECK_SENDMSG_ERROR(20024,"选择的安排计划已经发送通知，不能重复发送"),
	CHECK_CATEGORY_ERROR(20025,"%s"),
	CHECK_BEGINDATE_ERROR(20027,"%s"),
	CHECK_SAVEPLANSTATE_ERROR(20026,"面试安排状态为“已通知”，不能修改"),
	CHECK_SAVEPLANTYPE_ERROR(20027,"面试安排已自动分配学生面试计划，不能修改"),
	CHECK_PLACE_ERROR(20028,"尚未维护期望面试地点，请完善个人其他信息"),
	CHECK_DELETEPLAN_ERROR(20029,"面试安排状态为“已通知”，不能删除"),
	CHECK_DELETEPLANTYPE_ERROR(20030,"面试安排已自动分配学生面试计划，不能删除"),
	CHECK_PLANTURNS_ERROR(20031,"当天最大计划面试轮次超过方向可面轮次数，无法安排"),
	/***OFFER异常20200-20300  */
	QUERY_OFFER_ERROR(20200,"查询offer失败，ID：%s"),
	SEND_OFFER_ERROR(20201,"应聘者没有通过面试，不能发送Offer"),
	
	/***简历异常30000-30100  */
	FILE_TOO_LONGER(30000,"上传文件大小超过%sM"),
	RESUME_NOT_FINISH(30001,"简历未完善，请到个人中心完善简历"),
	RESUME_STATUS_ERROR(30002,"简历已在筛选中，无法更改"),
	RESUME_JOB_TOO_MUCH(30003,"超过简历可投递职位数"),
	RESUME_TAG_EXISTS(30004,"此简历已有此标签"),
	RESUME_JOB_EXISTS(30005,"已投递过此职位"),
	RESUME_HAS_SIGN(30006,"该简历已被筛选"),
	QUERY_RESUME_ERROR(30007,"暂无要选择时间的面试安排"),
	DELIVERY_NOT_POWER(30008,"招聘尚未开始,敬请期待"),
	OTHER_ATT_TOO_MUCH(30009,"简历其他附件最多只可上传6张图片"),
	FILE_NOT_EXISTS(30010,"文件不存在"),
	DELIVERY_PROJ_TOO_MUCH(30011,"只允许投递一个项目"),
	DATA_CAN_NOT_NONE(30012,"删除失败,至少保留一条数据"),
	LATERAL_HAS_TWO(30013,"推荐失败,此职位本已属于此方向"),
	STU_IS_NOT_ACCADJUST(30014,"该学生不接受调剂"),
	RESUME_NOT_CHANGE_ST(30015,"该简历已为%s状态"),
	RESUME_NOT_BELONG_CATEGORY(30016,"该简历不属于此方向"),
	RESUME_ST_ERR(30017,"当前简历状态为%s,不可%s"),
	RESUME_JOB_NOT_EXISTS(30018,"简历投递记录不存在"),
	CATEGORY_IS_SATURATED(30019,"此方向通过的人数已满,无法继续通过"),
	WORK_JOB_CITY_NOT_CORRESPOND(30020,"期望工作地与职位工作地不符"),
	RESUME_DATA_MISS(30021,"简历信息缺失,请完善简历"),
	JOB_HAS_NOT_CATEGORY(30022,"此职位无对应方向,无法投递"),
	RESUME_IS_DELIVERED(30023,"您已投递过职位,再次投递将覆盖之前的职位"),
	RESUME_IS_IN_PUBLIC_POND(20024,"该简历已在简历公共池中,无法%s"),
	TYPE_DETAIL_IS_NOT_EXISTS(20025,"类型明细不存在"),
	RESUME_CANNOT_TO_PUBLIC_POND(20026,"无法向简历公共池中推荐简历"),
	CANNOT_PASS_TO_PUBLIC_POND(20027,"无法向简历公共池中通过简历"),
	RESUME_BASIC_ERROR(20028,"查询简历个人基本信息失败"),
	STU_NOT_IN_CATEGORY(20029,"此简历不存在于此方向"),
	ACCEPT_OFFER_ERROR(20030,"简历状态不是“OFFER”"+"，不能接收offer"),
	REFUSE_OFFER_ERROR(20030,"简历状态不是“OFFER”"+"，不能拒绝offer"),
	STATE_NOT_FOURTHTPASEE(20031,"简历状态不为4面通过,无法发送offer"),
	CANNOT_TO_OLD_CATEGORY(20032,"无法向原方向推荐简历"),
	RESUME_CAN_NOT_AGREE(20033,"简历状态不为'已发送offer','接收Offer','已签三方',无法变更"),
	RESUME_CATEGORY_NOT_EXIST(20034,"简历方向信息不存在,无法变更方向"),
	RESUME_NOT_PASS(20035,"筛选未通过的简历无法变更方向"),
	
	/** 内推提示信息35000-35100 */
	REFERRAL_SUCCESS(35000,"内推成功"),
	REFERRAL_FAIL(35001,"内推失败:此手机号的学生已经填写简历或已经被内部推荐，无法重复推荐。"),
	
	/**
	 * jimmy  
	 * 面试现场：签到sign异常   40000-40300 
	 */
	SIGN_CANDIDATEID_NOTFOUND(40001,"签到失败-候选人未绑定微信"),
	SIGN_EARLY_CHECKIN_TIME(40002,"签到失败 _亲爱的%s：_您面试的职位为:%s_您预约的面试时间为:%s_此职位的最早签到时间为:%s_请在规定的时间区间签到，谢谢。"),
	SIGN_LATER_CHECKIN_TIME(40003,"签到失败 _亲爱的%s：_您面试的职位为:%s_您预约的面试时间为:%s_此职位的最晚签到时间为:%s_很遗憾您错过了签到时间。"),
	SIGN_CANDIDATE_ELIMINATED(40004,"签到失败_亲爱的:%s_你已被淘汰"),
	SIGN_CHECK_RESUME(40005,"签到失败,%s_你的简历不符合条件"),
	SIGN_REPEAT(40006,"签到失败_亲爱的:%s_你已签到，请勿重复签到"),
	SIGN_OTHER_EXCEPTION(40007,"签到失败_亲爱的:%s_请联系现场负责人"),
	SIGN_NOT_PASS(40008,"签到失败_亲爱的:%s_你的上一面未通过"),
	SIGN_NOT_WECHAT(40009,"签到失败_亲爱的:%s_请使用微信扫码签到"),
	SIGN_END_TODAY(40010,"签到失败_亲爱的:%s_今日你的面试已结束，等待终面通知"),
	SIGN_END_ALL(40011,"签到失败_面试已结束，请耐心等待通知"),
	SIGN_CALLNUMBER_TOOMUCH(40012,"叫号失败-呼叫人数超过已签到人数，请重新叫号"),
	SIGN_NUll_DATA(40013,"传入数据[%s]不能为空"),
	
	
	SIGN_NULL_JOBDIRECTION(40014,"根据微信id查询职位方向失败"),
	SIGN_NOT_ORDERED(40015,"签到失败_亲爱的:%s_你未预约面试"),
	SIGN_NO_USER(40016,"暂时已没有需要面试的学生"),
	SIGN_NOT_GETUSER(40017,"您还在面试中，无法呼叫下一位"),
	SIGN_NOT_RESUMEJOBPASS(40018,"签到失败,你的简历未通过筛选"),
	RAMADHIN_GETNULL(40019,"操作失败_面试官无对应桌号数据,请在面试任务页面输入桌号等数据，保存后在进行操作！"),
	INTERVIEW_RESULTERROR(40020,"评价填写失败_此学生本轮面试状态不在面试中,不能填写评价"),
	RECOMMEND_ERROR(40021,"横向推荐失败_此学生已不在面试中"),
	SELECTTABLE_ERROR(40022,"操作失败，查询%s表数据为空"),
	RECOMMEND_ERROR_INTERVIEWER(40023,"操作失败，此方向没有面试中的面试官"),
	SELECT_NULL_INTERVIEWER(40024,"查询面试官负责方向为空"),
	SELECTDATA_TOOMUCH(40025,"操作失败，查询[%s]表数据异常（数据过多）"),
	SELECTNULL_TODAYMAXROUND(40026,"查询当天面试轮数为空"),
	RAMADHIN_NOT_EXISTENCE(40027,"面试官桌号不存在"),
	SIGN_CALL_ERROR(40028,"无签到学生,无法叫号"),
	STU_IS_CALLED(40029,"多人同时叫号,叫到同一人,请重新叫号"),
	QUERY_SIGN_ERROR(40030,"查询签到记录失败"),
	
	/** 三方协议提示信息 45000-45100*/
	SANFANG_ST_ERROR(45000,"三方协议发送状态异常,无法修改"),
	SANFANG_NOT_EXIST(45001,"三方协议信息不存在,无法操作"),
	SIGN_GRANT_ST_ERROR(45002,"签约奖已发放,无法重复发放"),
	REFERRAL_IS_GRANT(45002,"内推奖已发放,无法重复发放"),
	REFERRAL_GRANT_ST_ERROR(45003,"无内推人,无法发放"),
	
	/***调用发送消息异常50001-50100  */
	MSG_SEND_TO_NULL(50001,"消息接收者为空"),
	MSG_CONTENT_NULL(50002,"消息内容为空"),
	MSG_TMP_NULL(50003,"模板不存在"),
	MSG_TMP_PROPERTY_NULL(50004,"模板参数为空"),
	MSG_TMP_PROPERTY_NOT(50005,"模板与模板参数不匹配"),
	MSG_NOT_ALLOW(50006,"接收者信息不符"),
	MSG_SUCCESS(50007,"SUCCESS"),
	MSG_SEND_ERROR(50008,"消息发送失败"),
	SEQ_PREFIX_ERROR(50009,"序列号前缀不合法"),
	
	/** 校验验证码提示信息50101-50199 */
	VERIFY_NOT_PASS(50101,"校验码不正确"),
	VERIFY_TIME_OUT(50102,"校验码不存在或已过期"),
	CLICK_SO_MUCH(50103,"一分钟内只允许获取一次校验码"),
	VERIFY_NOT_EXIST(50104,"未获取校验码,请先点击获取校验码"),
	
	/**
	 * 权限异常	60001-60100 
	 */
	RUTURN_NULL_ERROR(60001,"查询返回空"),
	REQUEST_NULL_ERROR(60002,"请求参数空"),
	
	/**
	 * 微信异常70001-70100
	 */
	WEIXIN_NOT_FOLLOW_MP(70001,"该用户未关注公众号"),
	
	/**
	 * 微信首次登陆绑定证手机号
	 */
	WEIXIN_NOT_VALIDATE_TEL(70002,"该用户未绑定手机号"),
	
	/**
	 * 微信手机号绑定失败
	 */	
	WEIXIN_BANDING_TEL_FAIL(70003,"绑定手机失败"),
	
	/**
	 * 微信手机号绑定失败,手机号已被绑定
	 */
	BANGDING_TEL_EXISTS(70004,"绑定的手机号已被使用"),
	
	/**
	 * 请求微信接口失败
	 */
	WEIXIN_REQUEST_FAIL(70005,"请求微信接口失败"),
	
	/**
	 * 修改的手机号不能与原先的手机号一致
	 */
	BANGDING_TEL_SAME_TO_ORIGINAL(70005,"修改的手机号不能与原先的一致"),
	
	
    /**动态错误码*/
	GEN_ERROR_CODE(0,""),
	
	FUNCTION_IS_DEVELOP(1,"功能开发中,敬请期待"),
	PI_EXCEPTION_RE(70006,"PI获取超时"),
	PI_EXCEPTION_IO(70007,"PI获取IOException"),
	PI_EXCEPTION_NULL(70008,"PI用户不存在")
	;
	
	
	
	private int code;
	private String msg;
	private ErrorCode(int code,String msg) {
		this.code = code;
		this.msg=msg;
	}
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	/**
	 * 根据code获取信息值
	 * 不存在返回null
	 * @param code
	 * @return
	 */
	public static String getMsg(int code){
	    for(ErrorCode errorCode : ErrorCode.values()){
	      if(code==errorCode.getCode()){
	        return errorCode.getMsg();
	      }
	    }
	    return null;
	  }
}
