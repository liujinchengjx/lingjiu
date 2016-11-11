package com.liu.qinziyou.entity.systemmanager;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.business.Company;

@Entity
@Table(name = "um_user")
public class User extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="USER_NAME",nullable=false)	
	private String userName;//登录名称（）//会员编号
	
	@Column(name="PASSWORD",nullable=true)
	private String passWord;//登录密码
	
	@Column(name="LOWERED_NAME",nullable=false)
	private String lowerdName;//小写登录名
	
	@Column(name="SYSTEM_ID",nullable=true)
	private Integer systemId;//系统ID
	
	@Column(name="ENTERPRISE_ID",nullable=true)
	private Integer enterpriseId;//单位ID 属于哪家经营公司的员工
	
	@Transient
	private Company company ;//对应的公司
	
	
	@Column(name="TEL",nullable=true)
	private String tel;//电话	

	@Column(name="EMAIL",nullable=true)
	private String email;//邮箱	
	
	@Column(name="STATUS",nullable=true)
	private Integer status;//状态  0无效 1有效
	
	@Column(name="SEX",nullable=true)
	private Integer sex;//性别 	
	
	@Column(name="REAL_NAME",nullable=true)
	private String realName;//真实姓名	
	
	@Column(name="ENAME",nullable=true)
	private String ename;//英文名字
	
	@Column(name="IDCARD",nullable=true)
	private String idCard;//身份证	
	
	@Column(name="HEAD_PIC",nullable=true)
	private String headPic;//头像	
	
	@Column(name="USER_GRADE",nullable=true)
	private String userGrade;//用户级别。0-普通用户；1系统管理员
	
	//父级用户，谁推荐的
	@Column(name="PARENT_USER_NAME",nullable=true)
	private String parentUserName;
	
	/**
	 *1：运营公司的员工用户 

	 *2:会员
	 */
	@Column(name="USER_TYPE",nullable=true)
	private Integer userType;
	
	@Column(name="IS_ADMIN",nullable=true)
	private Integer isAdmin;//是否管理员 0 否 1 是		
	
	@Column(name="LOGINED",nullable=true)
	private Integer logined;//登录次数

	@Column(name="REMARK",nullable=true)
	private String remark;//备注	
	
	@Column(name="LAST_LOGIN_DATE",nullable=true)
	private Timestamp lastLoginDate;//最后登录时间
	
	@Column(name="LOGIN_DATE",nullable=true)
	private Timestamp loginDate;//本次登录时间
	
	@Column(name="LAST_LOCKED_OUT_DATE",nullable=true)
	private Timestamp lastLockedOutDate;//最后冻结时间	
	
	@Column(name="LAST_ACTIVITY_DATE",nullable=true)
	private Timestamp LastActivityDate;//最后激活时间	
	
	@Column(name="FAILED_PASSWORE_LOGIN",nullable=true)
	private Integer failedPassworeLogin;//尝试登录失败次数	
	
	@Column(name="IS_ONLINE",nullable=true)
	private Integer isOnline;//是否在线。0-否；1-是
	
	@Column(name="REMIND_INTERVAL",nullable=true)
	private Integer remindInterval;//提醒间隔秒数
	
	@Column(name="RECENT_ITEMS",nullable=true)
	private String recentItems;//最近使用菜单
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;////0 有效  1 无效
	

	

	
	@Transient
	private String enterpriseName; //企业名称
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getLowerdName() {
		return lowerdName;
	}

	public void setLowerdName(String lowerdName) {
		this.lowerdName = lowerdName;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Timestamp getLastLockedOutDate() {
		return lastLockedOutDate;
	}

	public void setLastLockedOutDate(Timestamp lastLockedOutDate) {
		this.lastLockedOutDate = lastLockedOutDate;
	}

	public Timestamp getLastActivityDate() {
		return LastActivityDate;
	}

	public void setLastActivityDate(Timestamp lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Integer getFailedPassworeLogin() {
		return failedPassworeLogin;
	}

	public void setFailedPassworeLogin(Integer failedPassworeLogin) {
		this.failedPassworeLogin = failedPassworeLogin;
	}
	
	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getRemindInterval() {
		return remindInterval;
	}

	public void setRemindInterval(Integer remindInterval) {
		this.remindInterval = remindInterval;
	}

	public String getRecentItems() {
		return recentItems;
	}

	public void setRecentItems(String recentItems) {
		this.recentItems = recentItems;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}	

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getLogined() {
		return logined;
	}

	public void setLogined(Integer logined) {
		this.logined = logined;
	}


	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}



	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}


	
}
