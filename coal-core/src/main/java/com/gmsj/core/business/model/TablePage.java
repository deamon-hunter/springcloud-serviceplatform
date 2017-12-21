package com.gmsj.core.business.model;

import lombok.Data;

/**
 * Created by sail on 2016/12/25.
 */
@Data
public class TablePage {

    private Integer start;
    private Integer length;
    private Integer draw;

    private Integer limit;
    private Integer offset;
    private String order;
}
