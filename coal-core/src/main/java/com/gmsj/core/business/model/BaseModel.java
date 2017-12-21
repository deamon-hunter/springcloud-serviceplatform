package com.gmsj.core.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmsj.core.util.id.IdUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -5855158282006445350L;
    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    public void generateId() {
        this.id = IdUtil.generateId();
    }
}
