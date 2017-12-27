package com.gmsj.facilitator.service.impl;

import com.gmsj.CoreApplication;
import com.gmsj.facilitator.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ovrille
 * @date 2017/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class CityServiceImplTest {

    @Autowired
    private CityService cityService;

    @Test
    public void getCities() throws Exception {
        System.out.println(cityService.getCities());
    }

}