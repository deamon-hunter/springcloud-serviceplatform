package com.gmsj.system.mapper;

import com.gmsj.common.MyBaseMapper;
import com.gmsj.system.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeptMapper extends MyBaseMapper<DeptDO> {

    DeptDO get(Long deptId);

    List<DeptDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DeptDO dept);

    int update(DeptDO dept);

    int remove(Long deptId);

    int batchRemove(Long[] deptIds);

    Long[] listParentDept();

    int getDeptUserNumber(Long deptId);
}