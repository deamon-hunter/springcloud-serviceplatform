/**
 * 创建人： Hehaiyang
 * 包名字： com.gmsj.colliery.controller
 * 创建时间：2017/12/27
 * 描述：TODO
 * Copyright @ 2014-2017 bicitech.cn
 */
package com.gmsj.colliery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 *创建人： Hehaiyang 
 *包名字： com.gmsj.colliery.controller
 *创建时间：2017/12/27
  *类名：BaseController
 *描述：TODO
 *Copyright @ 2014-2017 
 */
@RestController
public class BaseController {

    @Value("${sys.group.code}")
    public String GROUP_CODE;
}
