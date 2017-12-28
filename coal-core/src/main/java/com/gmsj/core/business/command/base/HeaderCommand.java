package com.gmsj.core.business.command.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;

/**
 *创建人： Ovrille
 *包名字： com.gmsj.core.business.command.base
 *创建时间：2017/12/26
  *类名：HeaderCommand
 *描述：通用请求参数,所有入参都需要传入该值
 *Copyright @ 2014-2017 
 */
@Data
public class HeaderCommand {

    @NotNull
    @ApiModelProperty(value = "请求发起所属系统组")
    private String groupCode;

}
