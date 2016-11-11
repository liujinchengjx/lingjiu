package com.liu.qinziyou.dao.baseinfo;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.IBean;

public interface ISystemDictDao extends IBaseDAO{
    /**
     * 更改字典的状态
     * @param systemDictId
     * @param flag
     */
    public Integer updateFlag(Integer systemDictId,Integer flag) throws Exception;
    /**
     * 根据Type读取字典列表
     * @param type
     * @return
     * @throws Exception
     */
    public List selectDictByType(String type,Integer flag) throws Exception;
    /**
     * 根据字典类型和字典子项值，获得子项的名称
     * @param dictType 字典大类型
     * @param dictCode 字典子项代码
     * @param isCnName 是否输出中文名称，否则输出因为名称
     * @return
     * @throws Exception
     */
    public  String getDictName(String dictType,String dictCode,boolean isCnName) throws Exception;
    
    public  String getDictCnName(String dictType,String enName) throws Exception;
    /**
     *  根据名称找到代码
     * @param dictName
     * @return
     * @throws Exception
     */
    public  String getDictCode(String dictName,String dicType) throws Exception;
    /**
     * 检测字典是否存在
     * @param type
     * @param dictName
     * @return
     * @throws Exception
     */
    public boolean isExist(String type,String dictName) throws Exception;
    /**
     * 获取外键引用值
     * @param type
     * @param dictCode
     * @return
     */
    public String getRefKeyValueBy(String type,String dictCode) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSystemDict(String searchValue,String type,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;

}
