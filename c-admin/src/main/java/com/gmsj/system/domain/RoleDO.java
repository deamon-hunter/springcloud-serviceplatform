package com.gmsj.system.domain;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.List;

@Data
@Table(name = "sys_role")
public class RoleDO {
	
	private Long roleId;
	private String roleName;
    private Long deptId;
    @Transient
    private String deptName;
	private String roleSign;
	private String remark;
	private Long userIdCreate;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;
    @Transient
	private List<Long> menuIds;

}
