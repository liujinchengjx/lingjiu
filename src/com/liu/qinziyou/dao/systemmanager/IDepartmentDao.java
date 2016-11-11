package com.liu.qinziyou.dao.systemmanager;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IDepartmentDao extends IBaseDAO{
    /**
     * 获取部门名称
     * @return
     * @throws Exception
     */
    public  String getDepartmentName(Integer departmentId) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchDepartment(String searchValue,Integer opComanyId,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
