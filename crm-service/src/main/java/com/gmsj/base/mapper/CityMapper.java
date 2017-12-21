package com.gmsj.base.mapper;

import com.gmsj.base.model.Area;
import com.gmsj.base.model.City;
import com.gmsj.base.model.Province;
import com.gmsj.core.lib.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Ovrille
 * @date 2017/11/17
 */
@Mapper
public interface CityMapper extends MyBaseMapper<City>{
    List<City> getCities();

    List<Province> getProvinces();

    List<Area> getAreas();

}
