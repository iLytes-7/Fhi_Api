/**
 * @FileName:DataEnum.java 
 * @author shishi.qu
 * @version 2017年4月26日 下午2:31:06
 * @Description
 * @Copyright Notice: 
 * Copyright (c) 2016 Envision, Inc. All  Rights Reserved.
 * This software is published under the terms of the Envision Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
*/
package org.fh.api.dialect;

import java.util.Arrays;
import java.util.List;

public class DataEnum {

	/** 通过，未通过，面试中，缺席，评价填写中， 简历推荐中*/
	public enum interviewStatus {
		//pass, nopass, interview, absent, evaluation,recommend
		pass, nopass, interview, absent,recommend
	}

	/** 数据库数据状态 yes:有效;no:无效 **/
	public enum DataStatus {
		yes("有效"), no("无效");
		private DataStatus(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}

	}

	/** 是否字段状态 */
	public enum TrueFalseStatus {
		yes("是"), no("否");
		private TrueFalseStatus(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 性别 m:男;w:女 **/
	public enum DataSex {
		m("男"), w("女");
		private DataSex(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}

	}

	/** 消息类型 wechat:微信;email:邮件;msg:短信 */
	public enum MsgType {
		wechat("微信"), email("邮件"), msg("短信");

		private MsgType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}

	}

	/** 配置、菜单类型 student:学生;interviewer:面试官 **/
	public enum ConfigType {
		student, interviewer
	}

	/** 项目人员角色类型 interviewer:面试官;hr;picker:简历筛选官 **/
	public enum ActerType {
		interviewer("面试官"), hr("hr"), picker("筛选官");
		private ActerType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 面试方式 single:单面;multi:多面 **/
	public enum InterviewType {
		single("单面"), multi("群面");
		private InterviewType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 项目状态 recruiting进行中;recruited已结束 **/
	public enum ProjectStates {
		recruiting("进行中"), recruited("已完成");
		private ProjectStates(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/**
	 * 简历附件类型
	 * headPhoto:头像照片;video:自我展示视频;resumeAttachment:简历附件;otherAttachment:其他附件
	 **/
	public enum AttachmentType {
		headPhoto, video, resumeAttachment, otherAttachment
	}

	/** 简历操作日志操作员类型 student:求职学生;interviewer:面试官或管理员 **/
	public enum OperatorType {
		student, interviewer
	}

	/** 消息是否发送的标志 */
	public enum MsgSendSign {
		send("已发送"), unsend("未发送");

		private MsgSendSign(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}

	}

	/** 性别 */
	public enum Sex {
		male, female
	};

	/** 简历状态 */
	public enum ResumeStatus {
		unfilled("未填写"), init("填写中"), delivered("已投递"), finish("已完善"), pass("通过"), pending("待定"), noPass("淘汰"), expired("已过期"), 
		planed("已安排"), noticed("已通知"), ordered("已预约"), giveUp("已放弃"), sign("已签到"), fristTpasee("一面结束"), secondTpasee("二面结束"), 
		ThirdTpasee("三面结束"), FourthTpasee("四面结束"), success("面试结束"), out("面试结束"), offer("已发送offer"),absent("缺席"),refused("拒绝Offer"),
		accepted("接受Offer"),signContract("已签三方"),DEFAULT("已违约"); 

		private ResumeStatus(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}

		public static List<ResumeStatus> getResumeIntegritySt() {
			return Arrays.asList(ResumeStatus.unfilled, ResumeStatus.init, ResumeStatus.finish);
		}

		public static List<ResumeStatus> getDeliveryJobSt() {
			return Arrays.asList(ResumeStatus.delivered);
		}

		public static List<ResumeStatus> getScreenSt() {
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.pending,ResumeStatus.noPass);
		}

		public static List<ResumeStatus> getInterviewTimeSt() {
			return Arrays.asList(ResumeStatus.planed, ResumeStatus.noticed, ResumeStatus.ordered);
		}
		
		public static List<ResumeStatus> getStForBatchSign() {
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.planed, ResumeStatus.noticed, ResumeStatus.ordered);
		}
		public static List<ResumeStatus> getStForBatchSign_round() {
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.planed, ResumeStatus.noticed, 
					ResumeStatus.ordered,ResumeStatus.fristTpasee,ResumeStatus.secondTpasee,
					ResumeStatus.ThirdTpasee,ResumeStatus.FourthTpasee,ResumeStatus.absent);
		}
		public static List<ResumeStatus> get4NoCall() {
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.sign,ResumeStatus.planed, ResumeStatus.noticed, 
					ResumeStatus.ordered,ResumeStatus.fristTpasee,ResumeStatus.secondTpasee,
					ResumeStatus.ThirdTpasee,ResumeStatus.FourthTpasee,ResumeStatus.absent);
		}
		//可面试状态
		public static List<ResumeStatus> getInterviewState() {
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.planed,ResumeStatus.noticed, ResumeStatus.ordered,ResumeStatus.sign, ResumeStatus.fristTpasee, ResumeStatus.secondTpasee, 
					ResumeStatus.ThirdTpasee, ResumeStatus.FourthTpasee, ResumeStatus.absent);
		}
		public static List<ResumeStatus> getInterviewSurveySt() {
			//return Arrays.asList(ResumeStatus.planed, ResumeStatus.noticed, ResumeStatus.ordered);
			//unfilled("未填写"), init("填写中"), delivered("已投递"), finish("已完善"), pass("通过"), pending("待定"), noPass("淘汰"), expired("已过期"), 
//			planed("已安排"), noticed("已通知"), ordered("已预约"), giveUp("已放弃"), sign("已签到"), fristTpasee("一面通过"), secondTpasee("二面通过"), 
//			ThirdTpasee("三面通过"), FourthTpasee("四面通过"), success("面试成功"), out("面试失败"), offer("offer");
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.planed,ResumeStatus.noticed, ResumeStatus.ordered,ResumeStatus.sign, ResumeStatus.fristTpasee, ResumeStatus.secondTpasee, 
					ResumeStatus.ThirdTpasee, ResumeStatus.FourthTpasee, ResumeStatus.success, ResumeStatus.out,ResumeStatus.absent);
		}
		public static List<ResumeStatus> getInterviewDataState() {
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.planed,ResumeStatus.noticed, ResumeStatus.ordered,ResumeStatus.sign, ResumeStatus.fristTpasee, ResumeStatus.secondTpasee, 
					ResumeStatus.ThirdTpasee, ResumeStatus.FourthTpasee,ResumeStatus.absent);
		}
		public static List<ResumeStatus> canSendOfferStateList() {
			return Arrays.asList(ResumeStatus.FourthTpasee,ResumeStatus.offer,ResumeStatus.success);
		}
		/**
		 * 面试状态列表
		 * @return
		 */
		public static List<ResumeStatus> getInterviewSt() {
			return Arrays.asList(ResumeStatus.sign, ResumeStatus.fristTpasee, ResumeStatus.secondTpasee, 
					ResumeStatus.ThirdTpasee, ResumeStatus.FourthTpasee, ResumeStatus.success, ResumeStatus.out,ResumeStatus.absent);
		}
		/**
		 * 未签到学生状态--计划面试学生
		 * @return
		 */
		public static List<ResumeStatus> getNoSignSt() {
			//planed("已安排")-, noticed("已通知"), ordered("已预约")
			return Arrays.asList(ResumeStatus.noticed, ResumeStatus.ordered);
		}
		public static List<ResumeStatus> getOfferSt() {
			return Arrays.asList(ResumeStatus.offer,ResumeStatus.refused,ResumeStatus.accepted);
		}

		/**
		 * 获取可修改简历的状态
		 * 
		 * @return 状态列表
		 */
		public static List<ResumeStatus> getEditResumeStatus() {
			return Arrays.asList(ResumeStatus.unfilled, ResumeStatus.init, ResumeStatus.delivered, ResumeStatus.finish);
		}

		/**
		 * 获取不可修改简历的状态
		 * 
		 * @return 状态列表
		 */
		public static List<ResumeStatus> getFinalResumeStatus() {
			return Arrays.asList(ResumeStatus.pass, ResumeStatus.pending, ResumeStatus.noPass, ResumeStatus.expired, 
					ResumeStatus.planed, ResumeStatus.noticed, ResumeStatus.ordered,ResumeStatus.absent, ResumeStatus.giveUp,sign,fristTpasee,
					secondTpasee,ThirdTpasee,FourthTpasee,success,out,offer,refused,accepted);
		}

		/**
		 * 可签到状态
		 * 
		 * @return
		 */
		public static List<ResumeStatus> getInterviewResumeStatus() {
			return Arrays.asList(ResumeStatus.pass,ResumeStatus.planed,ResumeStatus.noticed, ResumeStatus.ordered, ResumeStatus.sign, ResumeStatus.fristTpasee,
					ResumeStatus.secondTpasee, ResumeStatus.ThirdTpasee, ResumeStatus.FourthTpasee, ResumeStatus.success,ResumeStatus.out,ResumeStatus.offer,ResumeStatus.absent);
		
		}
	};

	/** 状态变化操作说明 */
	public enum HistoryType {
		delivery("简历投递"), screen("简历筛选"), planed("安排面试"), ordered("预约面试"), noticed("通知学生"), sign("现场签到"), tpasee("面试");
		private HistoryType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 角色类型 add:正角色;delete:负角色 */
	public enum RoleType {
		add, delete
	}

	/** 模板名称 */
	public enum TemplateName {
		test, test1, test2
	};

	/** 面试安排方式 auto:自动分配;manual:手动选择 **/
	public enum PlanType {
		auto("系统分配"), manual("学生自选");
		private PlanType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 模板类型 wechat:微信;email:邮件;msg:短信 */
	public enum TemplateType {
		email("邮件"), msg("短信");
		private TemplateType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 模板用途interview("面试模板"), offer("录用通知"),resume("简历通知"),smsCode("短信验证码") */
	public enum TemplateUse {
		interview("面试模板"), offer("录用通知"),resume("简历通知"), smsCode("短信验证码"),deliveryJob("简历投递短信通知"),
		beisenTest("北森评测邮件通知"),interviewResult("面试结果通知"),signContract("签约阶段通知");
		private TemplateUse(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 简历完整度模板名称 */
	public enum ResumeModelName {
		personBasicInfo, otherPersonInfo, awardSituation, certificate, educationBackground, rePostSchool, internshipExperience, projectExperience, resumeAttachment, otherAttachmentList, reInterestHobby, rePaperPatent;
		public static List<ResumeModelName> getResumeModelName() {
			return Arrays.asList(personBasicInfo, otherPersonInfo, awardSituation, certificate, educationBackground, rePostSchool,
					internshipExperience, projectExperience, resumeAttachment, otherAttachmentList, reInterestHobby, rePaperPatent);
		}

		public static List<ResumeModelName> getNoCheckResumeModelName() {
			return Arrays.asList(otherAttachmentList, reInterestHobby, rePostSchool, awardSituation, rePaperPatent, certificate);
		}
	}

	/** 简历附表描述值 */
	public enum ReModelFinishStatus {
		yes("已完善"), no("未完善"), free("无此类信息");
		private ReModelFinishStatus(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 面试安排计划状态 saved:已保存;confirmed:已审核；noticed:已通知； **/
	public enum PlanState {
		saved("已保存"), confirmed("已确认"), noticed("已通知");
		private PlanState(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** PI评估报告类型 **/
	public enum AssessmentReportType {
		PersonDevelopmentChart("个人发展图表"), PersonDetailReport("个人详情报告");
		private AssessmentReportType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** PI评估性别 */
	public enum AssessmentGender {
		Unknown("0"), Male("1"), Female("2");
		private AssessmentGender(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** PI评估语言 */
	public enum AssessmentlanguageLocale {
		CN("zh-CN"), EN("en-US");
		private AssessmentlanguageLocale(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 是否通知管理员 */
	public enum AssessmentNotify {
		Notify("1"), UnNotify("0");
		private AssessmentNotify(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 学校所在国家 国内，国外 */
	public enum UniversityCountry {
		CN, FN;
	}

	/** 国内学校类型 */
	public enum CNType {
		T211, T985, OT, ALL;
	}

	/**国内学校类型*/
	public enum FNType{
		T100,ALL;
	}
	
	/**应聘进展模块代码 */
	public enum InterviewProgressName {
		resumeIntegrity, deliveryJob, screen, interviewTime, interviewSurvey, offer;
		public static List<InterviewProgressName> getInterviewProgressName() {
			return Arrays.asList(resumeIntegrity, deliveryJob, screen, interviewTime, interviewSurvey, offer);
		}
	}

	/** 专著专利类型 */
	public enum PaperPatentType {
		paper("论文"), monograph("专著"), patent("专利");
		private PaperPatentType(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** 作者顺序 */
	public enum AuthorOrder {
		first("第一作者"), second("第二作者"), other("其他");
		private AuthorOrder(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/** offer状态 sent:已发送;accepted:已接受；refused:已拒绝； **/
	public enum OfferState {
		saved("已保存"),sent("已发送"), accepted("已接受"), refused("已拒绝");
		private OfferState(String text) {
			this.text = text;
		}

		private String text;

		public String getText() {
			return text;
		}
	}

	/**翻译字段名称*/
	public enum ExportColumName{
		type("类型:"),turn("作者顺序:");
		private ExportColumName(String text) {
			this.text = text;
		}
		private String text;

		public String getText() {
			return text;
		}
	}
	
	/**验证码获取方式*/
	public enum VerifyType{
		video("语音播报"),msg("短信接收");
		private VerifyType(String text) {
			this.text = text;
		}
		private String text;

		public String getText() {
			return text;
		}
	}
	
	/**简历方向绑定状态*/
	public enum StuCategoryStatus{
		init("初始化"),pass("筛选通过"),noPass("筛选不通过"),pending("待定"),fristTpasee("一面结束"), secondTpasee("二面结束"), 
		ThirdTpasee("三面结束"), FourthTpasee("四面结束"), success("面试结束"), out("面试结束"), offer("offer"),
		expired("已过期"), planed("已安排"), noticed("已通知"), ordered("已预约"), giveUp("已放弃"), sign("已签到"),absent("缺席"),refused("拒绝Offer"),
		accepted("接受Offer"),signContract("已签三方"),DEFAULT("已违约");
		private StuCategoryStatus(String text) {
			this.text = text;
		}
		private String text;

		public String getText() {
			return text;
		}
	}
	
	/** 类型明细表类型 */
	public enum TypeDetail{
		publicCategory("公共简历池"),targetSchool("目标学校");
		private TypeDetail(String text) {
			this.text = text;
		}
		private String text;

		public String getText() {
			return text;
		}
	}
	
	/** 三方状态 */
	public enum SanfangStatus{
		stuSend("学生已寄出"),compSend("公司已回寄"),stuSecSend("学生二次寄出"),finish("已完成");
		private SanfangStatus(String text) {
			this.text = text;
		}
		private String text;

		public String getText() {
			return text;
		}
	}
}
