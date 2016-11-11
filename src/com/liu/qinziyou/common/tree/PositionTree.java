package com.liu.qinziyou.common.tree;
/**
 * 这个是权限树类，他的数据用来以树状显示所有权限
 * @author Administrator
 *
 */
public class PositionTree {
	
	  private String id = "";//本级Id
	  private String pid = ""; //父级ID
	  private String text = ""; //模块名称
	  private boolean expanded = false; //是否展开
	  private boolean hasChildren; //是否有子节点
	  private Integer positionLevel;// 职称水平
	  private Integer positionStatus;// 职称使用状态
	  private Integer positionId;// 职称ID
	  private boolean select=false;// 选中状态 
	  
	  public PositionTree(){
	  }
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public boolean isExpanded() {
			return expanded;
		}
		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}
		public Integer getPositionLevel() {
			return positionLevel;
		}
		public void setPositionLevel(Integer positionLevel) {
			this.positionLevel = positionLevel;
		}
		public Integer getPositionStatus() {
			return positionStatus;
		}
		public void setPositionStatus(Integer positionStatus) {
			this.positionStatus = positionStatus;
		}
		public boolean isSelect() {
			return select;
		}
		public void setSelect(boolean select) {
			this.select = select;
		}
		public Integer getPositionId() {
			return positionId;
		}
		public void setPositionId(Integer positionId) {
			this.positionId = positionId;
		}
		public boolean isHasChildren() {
			return hasChildren;
		}
		public void setHasChildren(boolean hasChildren) {
			this.hasChildren = hasChildren;
		}



}
