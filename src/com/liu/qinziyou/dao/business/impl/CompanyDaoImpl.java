package com.liu.qinziyou.dao.business.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.Constants;
import com.liu.qinziyou.common.LoginUserUtil;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.Constants.CompanyType;
import com.liu.qinziyou.common.util.DateUtil;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.business.ICompanyDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.Company;
import com.liu.qinziyou.entity.business.SuplerBankInfo;

public class CompanyDaoImpl extends BaseDAOImpl implements ICompanyDao{	    
	
	public int updateCompanyStatus(Integer companyId,Integer status)throws Exception{
		String sql="UPDATE company A SET A.STATUS=:status WHERE A.ID=:companyId "; 
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("companyId",companyId);    
    	filterMap.put("status",status);    
    	searchBean.setFilterMap(filterMap);
        int row=this.executeSQL(sql, searchBean);
    	return row;		
	}
	public int deleteCompany(Integer companyId)throws Exception{
		String sql="DELETE FROM company WHERE ID=:companyId "; 
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("companyId",companyId);
    	searchBean.setFilterMap(filterMap);
        int row=this.executeSQL(sql, searchBean);
    	return row;		
	}
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Company com = (Company)baseBean;
    	String sql="SELECT COUNT(*) FROM company A WHERE A.COMPANY_TYPE=:companyType AND ((A.CCODE LIKE :ccode) OR (A.CNAME LIKE :cname) ) ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("companyType",  com.getCompanyType());
    	m.put("ccode",  com.getCcode());
    	m.put("cname", com.getCname());
    	if(baseBean.getId()!=null) {
    		sql = sql + " AND A.ID!= :id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }   
    /**
     * 查询供方
     * @param searchValue
     * @param companyType
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public  PageHelper searchSeller(String cname,String ename,String pinyin,String ccode,Integer buyerCompanyId,
    		Integer companyType,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT DISTINCT A.* FROM company A INNER JOIN company_relation B ON A.ID=B.SELLER_ID WHERE 1=1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if((cname!=null) && (!"".equals(cname))){
    		sql=sql+" AND A.CNAME LIKE :cname ";
    		m.put("cname", "%"+cname+"%");
    	}
    	if((ename!=null) && (!"".equals(ename))){
    		sql=sql+" AND A.ENAME LIKE :ename ";
    		m.put("ename", "%"+ename+"%");
    	}
    	if((pinyin!=null) && (!"".equals(pinyin))){
    		sql=sql+" AND A.PINYIN LIKE :pinyin ";
    		m.put("pinyin", "%"+pinyin+"%");
    	}
    	if((pinyin!=null) && (!"".equals(pinyin))){
    		sql=sql+" AND A.CCODE LIKE :ccode ";
    		m.put("ccode", "%"+ccode+"%");
    	}  	
    	if((buyerCompanyId!=null) && (buyerCompanyId.intValue()>0)){
    		sql = sql + " AND (B.BUYER_ID=:buyerCompanyId) ";
    		m.put("buyerCompanyId", buyerCompanyId);
    	}
    	if((companyType!=null) && (companyType.intValue()>0)){
    		sql = sql + "AND A.COMPANY_TYPE=:companyType";
    		m.put("companyType", companyType);
    	}
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m); 
    	PageHelper ph=this.getPageHelperBySql(Company.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    
    /**
     * 查询需方
     */
    public  PageHelper searchBuyer(String searchValue,Integer sellerCompanyId,Integer companyType,
    		int currentPage, int pageSize) throws Exception{    	
    	String sql="SELECT DISTINCT A.* FROM company A INNER JOIN company_relation B ON A.ID=B.BUYER_ID WHERE 1=1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    	   sql=sql+"AND (A.CCODE LIKE :searchValue OR A.CNAME LIKE :searchValue OR A.ENAME LIKE :searchValue) ";
    	   filterMap.put("searchValue", "%"+searchValue+"%");
    	}
    	if((sellerCompanyId!=null) && (sellerCompanyId.intValue()>0)){
    		sql = sql + " AND B.SELLER_ID=:sellerCompanyId";
    		filterMap.put("sellerCompanyId", sellerCompanyId);
    	}
    	if(companyType!=null){
    		sql = sql + " AND A.COMPANY_TYPE=:companyType";
    		filterMap.put("companyType", companyType);
    	}
    	sql=sql+" ORDER BY A.ID";
    	searchBean.setFilterMap(filterMap); 
    	PageHelper ph=this.getPageHelperBySql(Company.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    /**
     * 获取公司的名字
     * @param companyId
     * @return
     * @throws Exception
     */
    public String getCompanyName(Integer companyId) throws Exception{
    	if (companyId==null){
    		return "";
    	}
    	Company company = (Company) this.findById(Company.class, companyId);
    	return company.getCname();
    }
    
    @Override
	public Map<Integer, String> getCompanyNames(String companyIds) throws Exception {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	if (companyIds == null || companyIds.equals("")) {
    		return map;
		}
    	String sql = "SELECT A.ID,A.CNAME FROM company A WHERE A.ID IN ("+companyIds+")";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	searchBean.setFilterMap(m);
    	
    	List<Object[]> list = this.getListResultBySql(null, sql, searchBean);
    	if (list != null && list.size() > 0) {
    		for (Object[] objects : list) {
    			map.put(Integer.valueOf(objects[0].toString()), objects[1] != null  ? objects[1].toString() : "");
    		}
		}    	
    	return map;
	}
	/**
     *  根据customOrder 的id获取一个供方公司，或是需方公司
     * @param customOrderId
     * @param sellerBuyer 1 供方，2 需方 
     * @return
     * @throws Exception
     */
    public Company getCompanyByCustomOrderId(Integer customOrderId,Integer sellerBuyer) throws Exception{
    	String sql="SELECT  A.* FROM company A WHERE ID = "; 
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	if (sellerBuyer.equals(Constants.SellerBuyer.BUYER.getValue())){
    		sql=sql+"(SELECT DISTINCT BUYERID FROM custom_order WHERE ID=:customOrderId)";
    	}else{
    		sql=sql+"(SELECT DISTINCT SELLERID FROM custom_order WHERE ID=:customOrderId)";
    	}
    	filterMap.put("customOrderId", customOrderId);    	
    	searchBean.setFilterMap(filterMap); 
    	return (Company)this.getObjectResultBySql(Company.class, sql, searchBean);
    }
    /**
     *  是否存在该公司 存在的话直接返回改公司的id,否则的话返回 -1
     * @param companyName
     * @return
     * @throws Exception
     */
    public Integer existCompany(String companyName) throws Exception{
    	String sql="SELECT A.ID FROM company A WHERE A.CNAME=:companyName "; 
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("companyName", companyName);    	
    	searchBean.setFilterMap(filterMap);
        Object o= this.getAttributeValueBySql(sql, searchBean);
    	return (o==null)?-1:(Integer)o;
    }
    
    /**
     * 获取Company表最大的ccode编码 编码前缀要根据type区分一下
     * @param
     * @return
     * @throws Exception
     */
    public  String getMaxCode(Integer type) throws Exception{    	
    	String sql="SELECT MAX(A.CCODE) FROM company A WHERE A.COMPANY_TYPE=:type";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("type", type);
    	searchBean.setFilterMap(m);
    	Object  o =this.getObjectResultBySql(null, sql, searchBean);
    	String pre="",yymm="",tempnum="";    	
    	if (type==1){
    		pre=Constants.CodePre.COMPANY_TITLE.getValue();	//运营公司
    	}else if(type==2){
    		pre=Constants.CodePre.CUSTOMER_TITLE.getValue();//客户	
    	}else if(type==3){
    		pre=Constants.CodePre.RECOMPANY_TITLE.getValue(); //客户的收货公司	
    	}else if(type==4){
    		pre=Constants.CodePre.SUPPLIER_TITLE.getValue(); //供应商	
    	}else if(type==5){
    		pre=Constants.CodePre.TRANSPORT_TITLE.getValue();//货运公司
    	}		
    	if ((o==null) || ("".equals(o))){ //不存在 则去字典里面找CustomOrder 编码的前缀
    		yymm=DateUtil.getYyMMddStrForDate(new Date());
    		tempnum= pre+yymm+"0000";
    	}else{
    		tempnum=o.toString();
    	}
    	tempnum=StringUtil.increaseOne(tempnum,pre,4);
    	return tempnum;
    }

    /**
     * 查询公司
     * @param searchValue
     * @param companyType
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public  PageHelper searchCompany(String searchValue,Integer companyId,Integer companyType,Integer status,
    		int currentPage, int pageSize) throws Exception{   
    	String sql="SELECT DISTINCT A.* FROM company A  ";
    	Map<String, Object> m=new HashMap<String, Object>();
    	if (CompanyType.CUSTOMER.getValue().equals(companyType)){ //如果类型是客户
    		sql=sql+" ,customer B WHERE A.ID=B.COMPANY_ID ";    		

    	}else{
    		sql=sql+" WHERE 1=1";
    	}
    	SearchBean searchBean=new SearchBean();
    	if ((searchValue!=null) && (!"".equals(searchValue)) ){
    		sql=sql+"  AND (A.CCODE LIKE :searchValue OR A.CNAME LIKE :searchValue OR A.ENAME LIKE :searchValue OR A.PINYIN=:searchValue ";
    		sql=sql+" OR A.CSNAME=:searchValue OR A.ESNAME=:searchValue) ";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	if (companyId!=null && companyId.intValue()>0){
    		sql = sql + " AND A.ID=:companyId";
    		m.put("companyId", companyId);
    	}
    	if ((companyType!=null) && (companyType.intValue()>0)){
    		sql = sql + " AND A.COMPANY_TYPE=:companyType";
    		m.put("companyType", companyType);
    	}
    	if ((status!=null) && (status.intValue()>0)){
    		sql = sql + " AND A.STATUS=:status ";
    		m.put("status",status);
    	}
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m); 
    	PageHelper ph=this.getPageHelperBySql(Company.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    public PageHelper searchTransCompany(Integer opCompanyId,String searchValue, Integer companyType,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT DISTINCT A.* FROM company A INNER JOIN company_relation B ON A.ID=B.SELLER_ID WHERE 1=1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue)) ){
    		sql=sql+"  AND (A.CCODE LIKE :searchValue OR A.CNAME LIKE :searchValue OR A.ENAME LIKE :searchValue) ";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	if ((companyType!=null) && (companyType.intValue()>0)){
    		sql = sql + " AND A.COMPANY_TYPE=:companyType ";
    		m.put("companyType", companyType);
    	}
    	if((opCompanyId!=null) && (opCompanyId.intValue()>0)){
    		sql = sql + " AND (B.BUYER_ID=:opCompanyId) ";
    		m.put("opCompanyId", opCompanyId);
    	}
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m); 
    	PageHelper ph=this.getPageHelperBySql(Company.class,sql, searchBean, currentPage, pageSize);
    	return ph;    	
    }
    //获取客户供应商
    public PageHelper searchCustomerSupplier(Integer customerCompanyId,String searchValue,Integer status,
    		int currentPage, int pageSize ) throws Exception{
    	String sql="SELECT A.* FROM company A INNER JOIN company_relation B ON A.ID = B.SELLER_ID " +
		" INNER JOIN company C ON B.BUYER_ID = C.ID AND C.COMPANY_TYPE=:revCompanyType WHERE 1=1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("revCompanyType", CompanyType.CUSTOMER.getValue());
    	if ((searchValue!=null) && (!"".equals(searchValue)) ){
    		sql=sql+" AND ((A.CCODE LIKE :searchValue) OR (A.CNAME LIKE :searchValue) OR (A.CSNAME LIKE :searchValue) OR "
    		+ " (A.ENAME LIKE :searchValue) OR (A.CSNAME LIKE :searchValue) OR (A.ESNAME LIKE :searchValue))";
    		m.put("searchValue",  "%"+searchValue+"%");
    	}
    	sql = sql + " AND A.COMPANY_TYPE=:companyType ";
    	m.put("companyType", Constants.CompanyType.SUPPLIER.getValue());
    	if (customerCompanyId!=null &&  customerCompanyId.intValue()>0){
    		sql = sql + " AND C.ID=:customerCompanyId " ;
    		m.put("customerCompanyId", customerCompanyId);
    	}
    	if ((status!=null) && (status.intValue()>0)){
    		sql = sql + " AND A.STATUS=:status " ;
    		m.put("status", status);
    	}
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m); 
    	PageHelper ph=this.getPageHelperBySql(Company.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    //获取经营公司供应商
    public PageHelper searchOpSuppler(Integer customerCompanyId,String searchValue,
    		int currentPage, int pageSize ) throws Exception{
    	String sql="SELECT A.* FROM company A INNER JOIN company_relation B ON A.ID = B.SELLER_ID " +
		" INNER JOIN company C ON B.BUYER_ID = C.ID AND C.COMPANY_TYPE=:revCompanyType WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("revCompanyType", CompanyType.OPERATION.getValue());
    	if ((searchValue!=null) && (!"".equals(searchValue)) ){
    		sql=sql+" AND ((A.CCODE LIKE :searchValue) OR (A.CNAME LIKE :searchValue) OR "
    		+ " (A.ENAME = :searchValue) OR (A.CSNAME = :searchValue) OR (A.ESNAME = :searchValue))";
    		m.put("searchValue",  "%"+searchValue+"%");
    	}
    	sql = sql + " AND A.COMPANY_TYPE=:companyType ";
    	m.put("companyType", Constants.CompanyType.SUPPLIER.getValue());
    	if (customerCompanyId!=null &&  customerCompanyId.intValue()>0){
    		sql = sql + " AND C.ID=:customerCompanyId " ;
    		m.put("customerCompanyId", customerCompanyId);
    	}
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Company.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    	
    }
    
  //获取经营公司供应商
    public PageHelper searchAssociated(Integer customerCompanyId,
    		int currentPage, int pageSize ) throws Exception{
    	String sql="SELECT A.* FROM company A INNER JOIN company_associated B ON A.ID = B.VICE_COMPANY_ID WHERE B.MAIN_COMPANY_ID=:customerCompanyId ORDER BY A.ID ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("customerCompanyId", customerCompanyId);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Company.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    	
    }
	@Override
	public int deleteAssociated(Integer customerCompanyId, Integer associatedId)
			throws Exception {
		String sql = "DELETE FROM company_associated WHERE MAIN_COMPANY_ID =:customerCompanyId AND VICE_COMPANY_ID = :associatedId ";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("customerCompanyId",customerCompanyId);
    	filterMap.put("associatedId",associatedId);
    	searchBean.setFilterMap(filterMap);
        int row=this.executeSQL(sql, searchBean);
		return row;
	}
	/**
	 * 删除供应商银行信息
	 * */
	@Override
	public int deletSpplerBankInfo(Integer supId) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM sup_bank_info WHERE ID=:supIds";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("supIds",supId);
    	searchBean.setFilterMap(filterMap);
    	int row=this.executeSQL(sql, searchBean);
		return row;
	}
	@Override
	public List searchSupplerList(Integer supId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM sup_bank_info A WHERE A.SUPCOMPANYID=:SUPID";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("SUPID",supId);
    	searchBean.setFilterMap(filterMap);
    	List list= this.getListResultBySql(SuplerBankInfo.class, sql, searchBean);
		return list;
	}
	//校验是否存在重复数据
	@Override
	public int checkBankInfo(Integer spuId, String bankAmount,
			String bankName) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM sup_bank_info A WHERE A.SUPCOMPANYID=:SUPID AND A.BANKACCOUNT=:AMOUNT AND A.BANKNAME=:BNAME";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("SUPID",spuId);
    	filterMap.put("AMOUNT",bankAmount);
    	filterMap.put("BNAME",bankName);
    	searchBean.setFilterMap(filterMap);
    	List list= this.getListResultBySql(SuplerBankInfo.class, sql, searchBean);
		return list.size();
	}
}
