/**
 * 创建人： Ovrille
 * 包名字： com.gmsj.common.vo
 * 创建时间：2017/12/11
 * 描述：TODO
 * Copyright @ 2014-2017 bicitech.cn
 */
package com.gmsj.core.business.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *创建人： Ovrille
 *包名字： com.gmsj.common.vo
 *创建时间：2017/12/11
  *类名：LoginData
 *Copyright @ 2014-2017
 */
@Data
public class LoginTokenDataVO {
    @ApiModelProperty(value = "token串,放在请求header中")
    private String authorToken;

    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @ApiModelProperty(value = "userID")
    private Long userId;

}
