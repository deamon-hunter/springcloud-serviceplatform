package com.gmsj.risk.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.gmsj.CoreApplication;
import com.gmsj.risk.fraud.mapper.RiskBlacklistMapper;
import com.gmsj.risk.fraud.model.RiskBlacklist;

/**
 * 
 * @author jintao
 * @date 2017年11月13日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class RiskBlacklistMapperTest {

    @Autowired
    private RiskBlacklistMapper blacklistMapper;

    @Test
    public void testSelect() {
        PageHelper.offsetPage(0, 1, false);
        List<RiskBlacklist> list = blacklistMapper.selectAll();
        Assert.assertTrue(list.size() == 0);

    }
}