package com.gmsj.core.business.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 */
@Data
public class BootstrapTable<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    // 总记录数
    private long total;
    // 列表数据
    private List<T> rows;

    /**
     * 分页
     *
     * @param list       列表数据
     */
    public BootstrapTable(List<T> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public BootstrapTable() {

    }


}
