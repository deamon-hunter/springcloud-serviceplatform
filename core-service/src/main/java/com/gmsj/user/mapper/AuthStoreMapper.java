package com.gmsj.user.mapper;

import com.gmsj.core.business.vo.user.AuthXsmVO;
import com.gmsj.core.lib.MyBaseMapper;
import com.gmsj.user.model.AuthStore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthStoreMapper extends MyBaseMapper<AuthStore> {

    /**
     * 更新新商盟账号密码
     * @param authXsmVO
     */
    void updateXsmAccountAndPass(AuthXsmVO authXsmVO);
}