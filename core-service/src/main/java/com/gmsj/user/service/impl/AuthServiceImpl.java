package com.gmsj.user.service.impl;

import com.gmsj.core.business.exception.BizAuthenticationErrorCode;
import com.gmsj.core.business.exception.BusinessException;
import com.gmsj.core.business.vo.user.AuthInfoVO;
import com.gmsj.core.business.vo.user.AuthRealNameVO;
import com.gmsj.core.business.vo.user.AuthStoreVO;
import com.gmsj.core.business.vo.user.AuthXsmVO;
import com.gmsj.core.util.BeanUtil;
import com.gmsj.crawl.utils.AuthUtil;
import com.gmsj.risk.service.RiskService;
import com.gmsj.user.mapper.AuthInfoMapper;
import com.gmsj.user.mapper.AuthRealNameMapper;
import com.gmsj.user.mapper.AuthScoreMapper;
import com.gmsj.user.mapper.AuthStoreMapper;
import com.gmsj.user.model.AuthInfo;
import com.gmsj.user.model.AuthRealName;
import com.gmsj.user.model.AuthScore;
import com.gmsj.user.model.AuthStore;
import com.gmsj.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AuthRealNameMapper realNameMapper;

    @Autowired
    private AuthInfoMapper authMapper;

    @Autowired
    private AuthScoreMapper scoreMapper;

    @Autowired
    private AuthStoreMapper authStoreMapper;

    @Autowired
    private RiskService riskService;

    @Override
    public void saveRealNameInfo(AuthRealNameVO authRealNameVO) {

        try {
            realNameMapper.insertSelective(new AuthRealName(authRealNameVO));
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(BizAuthenticationErrorCode.ALREAD_AUTH_REALNAME);
        }


        // 修改实名认证情况
        AuthInfo authInfo = new AuthInfo();
        authInfo.setIsAutoaym(true);
        authInfo.setUserId(authRealNameVO.getUserId());
        updateAuthInfo(authInfo);


    }


    @Override
    public void saveStoreInfo(AuthStoreVO authStoreVO) {
        try {
            authStoreMapper.insertSelective(new AuthStore(authStoreVO));
        } catch (DuplicateKeyException e) {

            throw new BusinessException(BizAuthenticationErrorCode.ALREAD_AUTH_STORE);

        }

        // 修改商户认证情况
        AuthInfo authInfo = new AuthInfo();
        authInfo.setIsStore(true);
        authInfo.setUserId(authStoreVO.getUserId());
        updateAuthInfo(authInfo);
        AuthScore authScore = new AuthScore();
        authScore.setAutoaymScore(500);
        updateAuthScore(authScore);



    }

    @Override
    public void saveXsmInfo(AuthXsmVO authXsmVO) {
        String jmcmm = AuthUtil.jmcmm(authXsmVO.getXsmPassword());

        authXsmVO.setXsmPassword(jmcmm);
        authStoreMapper.updateXsmAccountAndPass(authXsmVO);

        // 修改商户认证情况
        AuthInfo authInfo = new AuthInfo();
        authInfo.setIsXsm(true);
        authInfo.setUserId(authXsmVO.getUserId());
        updateAuthInfo(authInfo);


        // TODO: 2017/11/21 启动风控
        // 先直接通过，设置额度为2万

        riskService.antiFraud(authXsmVO.getUserId());
        riskService.getPrice(authXsmVO.getUserId());


    }

    @Override
    public AuthInfoVO getAuthInfo(Long userId) {

        return authMapper.selectAuthInfo(userId);
    }

    @Override
    public AuthStore getStoreInfo(Long userId) {
        AuthStore authStore = new AuthStore();
        authStore.setUserId(userId);
        return authStoreMapper.selectOne(authStore);
    }

    @Override
    public AuthRealName getRealNameInfo(Long userId) {
        AuthRealName authRealName = new AuthRealName();
        authRealName.setUserId(userId);
        return realNameMapper.selectOne(authRealName);
    }

    @Override
    public int updateAuthInfo(AuthInfo authInfo) {
        return authMapper.updateByExampleSelective(authInfo, BeanUtil.createExample(AuthInfo.class, "userId", authInfo.getUserId()));
    }

    @Override
    public int updateAuthScore(AuthScore score) {
        return scoreMapper.updateByExampleSelective(score, BeanUtil.createExample(AuthScore.class, "userId", score.getUserId()));
    }

}