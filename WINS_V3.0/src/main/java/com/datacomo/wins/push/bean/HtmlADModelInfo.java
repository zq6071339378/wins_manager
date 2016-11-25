package com.datacomo.wins.push.bean;

/**
 * 
 * @author liwenjie
 *
 */
public class HtmlADModelInfo {
	
	/**
	 * 标签ID
	 */
	private String adid;
	
	/**
	 * 类型
	 */
	private String adModel;
	
	/**
	 * 标题
	 */
	private String adTitle;
	
	/**
	 * 描述
	 */
	private String adDesc;
	
	private String components;

	private String listtype;
	
	private String value="";
	
	private String imgurl="";
	
	private String imgsize="";
	
	private String group="";
	private int sort=0;
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getAdModel() {
		return adModel;
	}
	public void setAdModel(String adModel) {
		this.adModel = adModel;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getAdDesc() {
		return adDesc;
	}
	public void setAdDesc(String adDesc) {
		this.adDesc = adDesc;
	}
	public String getComponents() {
		return components;
	}
	public void setComponents(String components) {
		this.components = components;
	}
	public String getListtype() {
		return listtype;
	}
	public void setListtype(String listtype) {
		this.listtype = listtype;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getImgsize() {
		return imgsize;
	}
	public void setImgsize(String imgsize) {
		this.imgsize = imgsize;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
