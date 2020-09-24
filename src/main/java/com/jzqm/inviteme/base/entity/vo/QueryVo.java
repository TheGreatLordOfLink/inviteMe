package com.jzqm.inviteme.base.entity.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.jzqm.inviteme.common.utils.JsonUtils;


public class QueryVo {
	
	private int pageIndex=1;
	private int pageSize=10;
	private int rowTotal=0;
	
	private LinkedHashMap<String,Object> cond=new LinkedHashMap<String, Object>();
	private LinkedHashMap<String,String> orderBy=new LinkedHashMap<String, String>();
	
	private List<Map> condList = new ArrayList<Map>();
	
	private String groupby;
	
	
	public int getPageIndex() {
		return pageIndex;
	}
	public QueryVo setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}
	public int getPageSize() {
		return pageSize;
	}
	public QueryVo setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	
	public int getOffset() {
		return pageIndex*pageSize;
	}
	
	public HashMap<String, Object> getCond() {
		return cond;
	}
	public void setCond(LinkedHashMap<String, Object> cond) {
		this.cond = cond;
	}
	
	public QueryVo appendCond(String key,String value){
		if(this.cond!=null){
			this.cond.put(key, value);
		}
		return this;
	}
	public QueryVo appendCondArray(String key,String[] value){
		if(this.cond!=null){
			this.cond.put(key, value);
		}
		return this;
	}
	public QueryVo appendCond(String key,int value){
		if(this.cond!=null){
			this.cond.put(key, value);
		}
		return this;
	}
	public QueryVo removeCond(String key){
		if(this.cond!=null){
			this.cond.remove(key);
		}
		return this;
	}
	
	public LinkedHashMap<String, String> getOrderBy() {
		return orderBy;
	}
	
	public void setOrderBy(LinkedHashMap<String, String> orderBy) {
		this.orderBy = orderBy;
	}
	
	public String getGroupby() {
		return groupby;
	}
	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}
	
	
	public void setRowTotal(int totalRecord) {
		rowTotal = totalRecord;
	}
	public List<Map> getCondList() {
		return condList;
	}
	public void setCondList(List<Map> condList) {
		this.condList = condList;
	};
	
	/**
	 * 生成唯一编号
	 * @return
	 */
	public String queryId(){
		
		String queryId= Base64.encodeBase64URLSafeString(JsonUtils.toJson(this).getBytes());
//		System.out.println("queryId="+queryId);
		return queryId;
	}
	
	/**
	 * 格式化逗号分割的条件
	 * @param condName
	 * @return
	 */
	public String[] formatArray(String condName){
		if(cond!=null ){
			String condValue = (String)cond.get(condName);
			return condValue!=null? condValue.split(","):null;
		}
		return null;
	}
}