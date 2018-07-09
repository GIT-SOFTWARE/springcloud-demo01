package com.biostime.entity.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.biostime.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 类功能描述：微信成员基础类
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:12624HL@biostime.com">12624HL</a>
 * @version DEALER 5.0
 * @since 2017年1月12日
 */
public class Member implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10877542410883621L;
	
	
	private String userid; //成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
	private String name; //成员名称。长度为1~64个字节
	private List<Integer> department; //成员所属部门id列表,不超过20个
	private String position; //职位信息。长度为0~64个字节
	private String mobile; //手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	private Integer gender; //性别。1表示男性，2表示女性
	private String email; //邮箱。长度为0~64个字节。企业内必须唯一
	private String weixinid; //微信号。企业内必须唯一。（注意：是微信号，不是微信的名字）
	private String enable; //启用/禁用成员。1表示启用成员，0表示禁用成员
	private String avatar_mediaid; //成员头像的mediaid，通过多媒体接口上传图片获得的mediaid
	private Map<String,List<Map<String,String>>> extattr;  //扩展属性。扩展属性需要在WEB管理端创建后才生效，否则忽略未知属性的赋值
	private String avatar; //头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
	private Integer status; //status	关注状态: 1=已关注，2=已禁用，4=未关注
	private List<Integer> order; //成员所属部门id列表,不超过20个
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getDepartment() {
		return department;
	}
	public void setDepartment(List<Integer> department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}
	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}
	public Map<String, List<Map<String, String>>> getExtattr() {
		return extattr;
	}
	public void setExtattr(Map<String, List<Map<String, String>>> extattr) {
		this.extattr = extattr;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<Integer> getOrder() {
		return order;
	}
	public void setOrder(List<Integer> order) {
		this.order = order;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}	
	
}
