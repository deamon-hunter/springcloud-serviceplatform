package com.gmsj.user.service;

import com.gmsj.core.business.vo.user.AuthInfoVO;
import com.gmsj.core.business.vo.user.AuthRealNameVO;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import com.gmsj.core.business.vo.user.AuthXsmVO;
import com.gmsj.user.model.AuthInfo;
import com.gmsj.user.model.AuthRealName;
import com.gmsj.user.model.AuthScore;
import com.gmsj.user.model.AuthStore;

/**
 * 认证信息接口
 *
 * @author hongQiang tang
 * @version $Id: AuthService.java, v 0.1 2017年6月29日 下午2:59:02 Administrator Exp $
 */
public interface AuthService {


    /**
     * 保存实名认证信息，
     * 由客户端完成身份证信息校验，然后将认证后的信息通过请求传递过来，服务器负责将信息持久化保存
     *
     * @param realNamesVo 身份证正、反面数据
     */
    void saveRealNameInfo(AuthRealNameVO realNamesVo);


    /**
     * 修改认证信息
     *
     * @param authInfo
     */
    int updateAuthInfo(AuthInfo authInfo);

    /**
     * 修改认证授权分
     *
     * @param score
     * @return
     */
    int updateAuthScore(AuthScore score);


    /**
     * 商户认证信息
     *
     * @param authStoreVO 商户信息
     */
    void saveStoreInfo(AuthStoreVO authStoreVO);

    /**
     * 新商盟账号密码
     *
     * @param authXsmVO 账号密码
     */
    void saveXsmInfo(AuthXsmVO authXsmVO);

    /**
     * 获取认证状态
     * @param userId
     * @return
     */
    AuthInfoVO getAuthInfo(Long userId);

    AuthStore getStoreInfo(Long userId);

    AuthRealName getRealNameInfo(Long userId);
}
