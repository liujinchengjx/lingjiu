package com.liu.qinziyou.dao.systemmanager;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IRoleDao  extends IBaseDAO{	
    /**
     * 删除角色
     * @return
     * @throws Exception
     */
    public boolean delete(int roleId,int flag) throws Exception;
    	
    /**
     * 冻结角色
     * @return
     * @throws Exception
     */
    public boolean froze(int roleId,int flag) throws Exception;
    /**
     * 复核角色
     * @return
     * @throws Exception
     */
    public boolean approved(int roleId,int flag) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchRole(String searchValue,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
