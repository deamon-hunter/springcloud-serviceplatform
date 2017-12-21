package com.gmsj.user.controller;

import com.aliyun.oss.OSSClient;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.media.MediaDownloadResult;
import com.gmsj.core.business.group.auth.RealName;
import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.business.vo.user.AuthRealNameVO;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import com.gmsj.core.business.vo.user.AuthXsmVO;
import com.gmsj.core.lib.AliyunProperties;
import com.gmsj.core.util.AliyunUtil;
import com.gmsj.core.util.DigestUtils;
import com.gmsj.user.clients.AuthClient;
import com.gmsj.util.SessionUtil;
import com.gmsj.util.WeixinUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 信息认证接口
 */
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthClient authClient;

    @Autowired
    private WeixinUtil weixinUtil;

    @Autowired
    private AliyunProperties aliyunProperties;

    /**
     * 实名认证
     *
     * @param authRealNameVO
     */
    @PostMapping("/realName")
    public RestResp saveRealNameInfo(@RequestBody @Validated(RealName.class) AuthRealNameVO authRealNameVO) throws WeixinException, ExecutionException, InterruptedException {
        authRealNameVO.setUserId(SessionUtil.getCurrUid());


        String back = downloadFromWeixinUploadToOSS(authRealNameVO.getIdCardBack());
        String front = downloadFromWeixinUploadToOSS(authRealNameVO.getIdCardFront());


        authRealNameVO.setIdCardFront(aliyunProperties.getOss().getImgDomain() + front);
        authRealNameVO.setIdCardBack(aliyunProperties.getOss().getImgDomain() + back);
        return authClient.saveRealNameInfo(authRealNameVO);
    }


    @PostMapping("/store")
    public RestResp saveStoreInfo(@RequestBody @Validated AuthStoreVO authStoreVO) throws ExecutionException, InterruptedException {
        authStoreVO.setUserId(SessionUtil.getCurrUid());

        String cigarNumberImg = downloadFromWeixinUploadToOSS(authStoreVO.getCigarNumberImg());
        authStoreVO.setCigarNumberImg(aliyunProperties.getOss().getImgDomain() + cigarNumberImg);
        return authClient.saveStoreInfo(authStoreVO);
    }


    @PostMapping("/xsm")
    public RestResp saveXsmInfo(@RequestBody @Validated AuthXsmVO authXsmVO) {
        authXsmVO.setUserId(SessionUtil.getCurrUid());
        return authClient.saveXsmInfo(authXsmVO);
    }



    @GetMapping("/info")
    public RestResp getAuthInfo() {
        return authClient.getAuthInfo(SessionUtil.getCurrUid());
    }


    @SneakyThrows
    private String downloadFromWeixinUploadToOSS(String weixinMediaId) {
        MediaDownloadResult back = weixinUtil.getWeixinProxy().downloadMedia(weixinMediaId, false);
        MediaDownloadResult front = weixinUtil.getWeixinProxy().downloadMedia(weixinMediaId, false);

        String suffix = "." + back.getContentType().getMimeType().getSubType();


        String key = "user/auth/" + SessionUtil.getCurrUid() + "/" + DigestUtils.md5(weixinMediaId) + suffix;

        CompletableFuture.runAsync(() -> {
            OSSClient ossClient = new OSSClient(aliyunProperties.getOss().getEndpoint(), aliyunProperties.getAccessKeyId(), aliyunProperties.getAccessKeySecret());
            AliyunUtil.uploadToOSS(back.getContent(), key, ossClient, aliyunProperties.getOss().getImgBucketName());
        });

        return key;
    }
}
