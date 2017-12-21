package com.gmsj.base.service.impl;

import com.gmsj.base.mapper.CityMapper;
import com.gmsj.base.model.Area;
import com.gmsj.base.model.City;
import com.gmsj.base.model.Province;
import com.gmsj.base.service.CityService;
import com.gmsj.core.business.vo.base.AreaVO;
import com.gmsj.core.business.vo.base.CityVO;
import com.gmsj.core.business.vo.base.ProvinceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ovrille
 * @date 2017/11/17
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    @Deprecated
    public List<ProvinceVO> getCities() {


        // 省份
        List<Province> provinces = cityMapper.getProvinces();

        // 城市
        List<City> cities = cityMapper.getCities();

        // 区域
        List<Area> areas = cityMapper.getAreas();


        Map<String, List<AreaVO>> areaMap = areas.stream().map(area -> {
            AreaVO areaVO = new AreaVO();
            BeanUtils.copyProperties(area, areaVO);
            return areaVO;
        }).collect(Collectors.groupingBy(AreaVO::getCityId));


        List<CityVO> cityVOList = cities.stream().map(city -> {
            CityVO cityVO = new CityVO();
            BeanUtils.copyProperties(city, cityVO);
            cityVO.setAreas(areaMap.get(city.getProvinceId()));
            return cityVO;
        }).collect(Collectors.toList());

        Map<String, List<CityVO>> cityMap = cityVOList.stream().collect(Collectors.groupingBy(CityVO::getProvinceId));

        List<ProvinceVO> provinceVOList = provinces.stream().map(province -> {
            ProvinceVO provinceVO = new ProvinceVO();
            BeanUtils.copyProperties(province, provinceVO);
            List<CityVO> pCity = cityMap.get(province.getProvinceId());

            provinceVO.setCities(cityVOList);
            return provinceVO;
        }).collect(Collectors.toList());


        return provinceVOList;
    }
}
