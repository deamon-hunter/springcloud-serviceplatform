package com.gmsj.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 机构归属的系统组（平台煤企/服务商/crm等）
 * 
 * @author niechen
 * @date 2017-12-27 17:05:41
 */
public class GroupDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//系统编码
	private String groupCode;
	//组名
	private String groupName;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：系统编码
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * 获取：系统编码
	 */
	public String getGroupCode() {
		return groupCode;
	}
	/**
	 * 设置：组名
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 获取：组名
	 */
	public String getGroupName() {
		return groupName;
	}
}
