package com.gmsj.core.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 实体Bean工具类
 *
 * @author hongQiang tang
 * @version $Id: BeanUtil.java, v 0.1 2017年6月20日 上午11:48:12 Administrator Exp $
 */
@Slf4j
public class BeanUtil {

    public static final Example createExample(Class<?> type, Object... fields) {

        Example example = new Example(type);
        Criteria criteria = example.createCriteria();

        int size = fields.length % 2 == 0 ? fields.length / 2 : fields.length - 1;
        for (int i = 0; i <= size; i += 2) {
            criteria.andEqualTo(fields[i].toString(), fields[i + 1]);
        }

        return example;
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }


    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 拷贝不为空的值
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }


    /**
     * 转换Map为BeanT
     */
    @SneakyThrows
    public static <T> T mapToBean(Map<String, Object> m, Class<T> clazz) {
        T target = clazz.newInstance();
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(target);
        beanWrapper.setAutoGrowNestedPaths(true);
        beanWrapper.setPropertyValues(m);

        return target;
    }

    /**
     * 转换Bean为Map
     */
    public static <T> Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
        PropertyDescriptor[] descriptor = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor aDescriptor : descriptor) {
            String name = aDescriptor.getName();
            if (!name.equals("class")) {
                map.put(name, beanWrapper.getPropertyValue(name));
            }
        }

        return map;
    }
}
