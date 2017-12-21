package com.gmsj.base.controller;

import com.gmsj.base.clients.CityClient;
import com.gmsj.core.business.model.RestResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ovrille
 * @date 2017/11/17
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityClient cityClient;

    //@GetMapping("/all")
    public RestResp getCities() {
        return cityClient.getCities();
    }
}