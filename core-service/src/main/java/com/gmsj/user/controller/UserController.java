package com.gmsj.user.controller;

import com.gmsj.core.business.command.base.LoginCommand;
import com.gmsj.core.business.command.user.NewPwdCommand;
import com.gmsj.core.business.command.user.UserCommand;
import com.gmsj.core.business.group.auth.RealName;
import com.gmsj.core.business.group.user.NewPwd;
import com.gmsj.core.conf.security.annotation.RoleCheck;
import com.gmsj.user.service.UserService;
import com.gmsj.user.vo.LoginTokenDataVO;
import com.gmsj.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "用户信息模块", description = "账户管理相关功能")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录", notes = "", response = LoginTokenDataVO.class, httpMethod = "POST")
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public LoginTokenDataVO authenticate(@RequestBody LoginCommand loginCommand) {
        return userService.authenticate(loginCommand);
    }

    @ApiOperation(value = "修改密码", notes = "",  httpMethod = "PUT" , response =int.class )
    @RoleCheck
    @RequestMapping(path = "/modifyPwd", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE)
    public int modifyPwd(@RequestBody @Validated(NewPwd.class) NewPwdCommand newPwdCommand ) {
        return  userService.modifyPwd(newPwdCommand);
    }



    //TODO  下面部分均未实现
    @ApiOperation(value = "新增用户", notes = "",  httpMethod = "POST" ,response = UserVO.class)
    @RoleCheck
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public UserVO addUser(@RequestBody @Validated(RealName.class) UserCommand userCommand) {

        return null;
    }

    @ApiOperation(value = "修改用户信息", notes = "",  httpMethod = "POST" ,response = UserVO.class)
    @RoleCheck
    @RequestMapping(path = "/update", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public UserVO updateUser(@RequestBody @Validated UserCommand userCommand) {

        return null;
    }

    @ApiOperation(value = "删除用户", notes = "",  httpMethod = "DELETE" ,response = int.class)
    @RoleCheck
    @RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
    public int deleteUser(@RequestBody @Validated UserCommand userCommand) {

        return 0;
    }

    @ApiOperation(value = "查询用户信息", notes = "", response = UserVO.class, httpMethod = "GET")
    @RequestMapping(path = "/info/{userId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public UserVO getUser(@RequestBody @PathVariable Long  userId) {

        return null;
    }

    @ApiOperation(value = "查询用户信息", notes = "", response = List.class, httpMethod = "GET")
    @RequestMapping(path = "/listUsers", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<UserVO> listUsers(@RequestBody @PathVariable Long  userId) {

        return null;
    }
    @ApiOperation(value = "批量删除用户信息", notes = "", response = int.class, httpMethod = "DELETE")
    @RequestMapping(path = "/batchDelete", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
    public int batchDeleteUser(@RequestBody  @Validated List<UserCommand> users) {

        return 0;
    }
}
