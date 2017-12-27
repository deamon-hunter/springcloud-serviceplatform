package com.gmsj.user.model;

import javax.persistence.*;

@Table(name = "sys_dept")
public class Dept {
    @Id
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 上级部门ID，一级部门为0
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 所属系统组
     */
    @Column(name = "sys_group_id")
    private Long sysGroupId;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @Column(name = "del_flag")
    private Byte delFlag;

    /**
     * @return dept_id
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取上级部门ID，一级部门为0
     *
     * @return parent_id - 上级部门ID，一级部门为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置上级部门ID，一级部门为0
     *
     * @param parentId 上级部门ID，一级部门为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取部门名称
     *
     * @return name - 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     *
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取所属系统组
     *
     * @return sys_group_id - 所属系统组
     */
    public Long getSysGroupId() {
        return sysGroupId;
    }

    /**
     * 设置所属系统组
     *
     * @param sysGroupId 所属系统组
     */
    public void setSysGroupId(Long sysGroupId) {
        this.sysGroupId = sysGroupId;
    }

    /**
     * 获取排序
     *
     * @return order_num - 排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取是否删除  -1：已删除  0：正常
     *
     * @return del_flag - 是否删除  -1：已删除  0：正常
     */
    public Byte getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否删除  -1：已删除  0：正常
     *
     * @param delFlag 是否删除  -1：已删除  0：正常
     */
    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }
}