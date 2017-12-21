package com.gmsj.user.service.impl;

import com.gmsj.core.util.WebToolsUtils;
import com.gmsj.core.util.id.IdUtil;
import com.gmsj.user.mapper.LoginLogMapper;
import com.gmsj.user.model.LoginLog;
import com.gmsj.user.service.LoginLogService;
import com.gmsj.core.business.vo.user.UserVO;
import com.gmsj.core.util.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void saveLoginLog(UserVO userVO) {
        CompletableFuture.runAsync(new LogThread(loginLogMapper, userVO, WebToolsUtils.getClientIp()));
    }

    static class LogThread extends Thread {

        String ip;
        UserVO userVO;
        LoginLogMapper loginLogMapper;

        public LogThread(LoginLogMapper loginLogMapper, UserVO userVO, String ip) {
            this.ip = ip;
            this.userVO = userVO;
            this.loginLogMapper = loginLogMapper;
        }

        @Override
        public void run() {

            LoginLog log = new LoginLog();
            log.setId(IdUtil.generateId());
            log.setUserId(userVO.getId());
            log.setIp(ip);
            log.setLoginAddress(Ip.getIpLocation(ip));
            loginLogMapper.insertSelective(log);
        }
    }
}
