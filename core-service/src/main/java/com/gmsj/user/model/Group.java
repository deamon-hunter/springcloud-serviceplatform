package com.gmsj.user.model;

import javax.persistence.*;

@Table(name = "sys_group")
public class Group {
    @Id
    private Long id;

    /**
     * 系统编码
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 组名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取系统编码
     *
     * @return group_code - 系统编码
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置系统编码
     *
     * @param groupCode 系统编码
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 获取组名
     *
     * @return group_name - 组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置组名
     *
     * @param groupName 组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}