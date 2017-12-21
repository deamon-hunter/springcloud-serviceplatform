package com.gmsj.core.business.vo.base;

import lombok.Data;

import java.util.List;

/**
 * @author Ovrille
 * @date 2017/11/17
 */
@Data
public class CityVO {

    private List<AreaVO> areas;

    private Long id;
    private String provinceId;
    private String cityId;
    private String city;


}
