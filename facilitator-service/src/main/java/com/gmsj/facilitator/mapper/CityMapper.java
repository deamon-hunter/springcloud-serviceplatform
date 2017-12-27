package com.gmsj.facilitator.mapper;

import com.gmsj.facilitator.model.Area;
import com.gmsj.facilitator.model.City;
import com.gmsj.facilitator.model.Province;
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
