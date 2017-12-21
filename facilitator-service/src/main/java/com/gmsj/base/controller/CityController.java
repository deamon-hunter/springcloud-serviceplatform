package com.gmsj.base.controller;

import com.gmsj.base.service.CityService;
import com.gmsj.core.business.vo.base.ProvinceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ovrille
 * @date 2017/11/17
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/all")
    public List<ProvinceVO> getCities() {
        return cityService.getCities();
    }
}
