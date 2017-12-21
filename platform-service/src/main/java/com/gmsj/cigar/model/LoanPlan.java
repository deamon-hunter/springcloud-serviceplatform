package com.gmsj.cigar.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tbl_finance_solutions")
public class LoanPlan implements java.io.Serializable {

    @Id
    private Long id;
    /**
     * 金融方案名称
     */
    private String financeSolutionName;
    /**
     * 金融方案编码
     */
    private String financeSolutionCode;
    /**
     * 实际年化
     */
    private double actualAnnual;
    /**
     * 前期
     */
    private Integer earlyStage;
    /**
     * 后期
     */
    private String laterStage;
    /**
     * 前期费率
     */
    private double earlyRate;
    /**
     * 后期费率
     */
    private double laterRate;
    /**
     * 最高加层后名义利率
     */
    private double addedRate;
    /**
     * 是否删除 0：正常 1：删除
     */
    private Integer isDelete;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 最后更新人
     */
    private Long updateBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;

}
