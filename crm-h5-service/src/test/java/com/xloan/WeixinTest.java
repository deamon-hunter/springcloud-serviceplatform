package com.gmsj;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.model.media.MediaUploadResult;
import com.foxinmy.weixin4j.mp.WeixinProxy;

import com.gmsj.util.WeixinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Ovrille
 * @date 2017/10/16
 */

@SpringBootTest(classes = H5Application.class)
@RunWith(SpringRunner.class)
public class WeixinTest {

    @Autowired
    private WeixinUtil weixinUtil;

    @Test
    public void test () throws WeixinException {
        WeixinProxy weixinProxy = weixinUtil.getWeixinProxy();
        System.out.println(weixinProxy.getWeixinAccount().getId());
        System.out.println(weixinProxy.getWeixinAccount().getSecret());
        weixinProxy.getAllFollowingOpenIds().forEach(System.out::println);
    }

    @Test
    public void testUpload() throws FileNotFoundException, WeixinException {
        FileInputStream is = new FileInputStream("/Users/xiaomai/Desktop/money.png");

        MediaUploadResult mediaUploadResult = weixinUtil.getWeixinProxy().uploadMedia(false, is, "test.png");
        System.out.println(mediaUploadResult.getMediaId());
        System.out.println(mediaUploadResult.getUrl());
    }

}
