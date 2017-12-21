package com.gmsj.user.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.jssdk.JSSDKAPI;
import com.foxinmy.weixin4j.jssdk.JSSDKConfigurator;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.foxinmy.weixin4j.type.TicketType;
import com.foxinmy.weixin4j.util.StringUtil;
import com.google.common.base.Strings;
import com.gmsj.core.util.JsonUtil;
import com.gmsj.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Ovrille
 * @date 2017/11/06
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @Autowired
    private WeixinUtil weixinUtil;

    @Autowired
    private HttpServletRequest request;


    @GetMapping("/ticket")
    public Map<String, Object> getWeiXinTicket(String fullUrl) throws WeixinException {

        JSSDKConfigurator jssdk = new JSSDKConfigurator(
                weixinUtil.getWeixinProxy().getTicketManager(TicketType.jsapi));
        jssdk.apis(JSSDKAPI.uploadImage, JSSDKAPI.chooseImage); // 分享功能
        //jssdk.apis(JSSDKAPI.MP_ALL_APIS); // 或者使用全部功能
        jssdk.debugMode(); //打开调试模式

        if (Strings.isNullOrEmpty(fullUrl)) {
            fullUrl = getFullLoction();
        }
        String config = jssdk.toJSONConfig(fullUrl);

        if (log.isDebugEnabled()) {
            log.debug("JS所在地址为：" + fullUrl);
        }

        return JsonUtil.toMap(config);
    }


    @GetMapping("/auth")
    public void auth(String code, HttpServletResponse response) throws WeixinException, IOException {

        OauthApi oauthApi = weixinUtil.getWeixinProxy().getOauthApi();


        log.debug("用户同意授权,登录中----code:  " + code);

        OauthToken token = oauthApi.getAuthorizationToken(code);
        if (Strings.isNullOrEmpty(token.getAccessToken())) {
            token = oauthApi.refreshAuthorizationToken(token.getRefreshToken());
        }

        com.foxinmy.weixin4j.mp.model.User weixinUser = oauthApi.getAuthorizationUser(token);

        log.debug(weixinUser.toString());

        response.sendRedirect("http://wx.wodel.com.cn?openId=" + weixinUser.getOpenId());
    }

    protected String getRootLocation() {
        int port = request.getServerPort();
        if (port == 80) {
            return String.format("%s://%s", request.getScheme(),
                    request.getServerName());
        } else {
            return String.format("%s://%s:%s", request.getScheme(),
                    request.getServerName(), port);
        }
    }

    protected String getFullLoction() {
        String root = getRootLocation();
        String queryString = request.getQueryString();
        return String.format("%s%s%s", root, request.getRequestURI(),
                StringUtil.isNotBlank(queryString) ? "?" + queryString : "");
    }
}
