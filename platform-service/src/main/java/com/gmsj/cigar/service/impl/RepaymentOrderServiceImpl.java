package com.gmsj.cigar.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gmsj.cigar.service.RepaymentOrderService;

import com.gmsj.core.business.model.DataTable;
import com.gmsj.core.business.vo.cigar.RepaymentOrderVO;
import com.gmsj.user.mapper.RepaymentOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RepaymentOrderServiceImpl implements RepaymentOrderService {

    @Autowired
    private RepaymentOrderMapper repaymentOrderMapper;


    @Override
    public DataTable<RepaymentOrderVO> getRepaymentOrder(RepaymentOrderVO repaymentOrderVO) {

        PageHelper.offsetPage(repaymentOrderVO.getStart(), repaymentOrderVO.getLength());
        List<RepaymentOrderVO> repaymentOrderList = repaymentOrderMapper.getRepaymentOrderList(repaymentOrderVO);

        DataTable<RepaymentOrderVO> tables = new DataTable<>();
        tables.setRecordsTotal(((Page<RepaymentOrderVO>) repaymentOrderList).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setData(repaymentOrderList);
        return tables;

    }


}