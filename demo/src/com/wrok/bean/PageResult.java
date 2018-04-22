package com.wrok.bean;
import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @param <T>
 */
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = -8741766802354222579L;

	private int pageSize; // 每页显示多少条记录
	
	private int currentPage; //当前第几页数据
	
	private int totalRecord; // 一共多少条记录
	
	private int totalPage; // 一共多少页记录
	
	private List<T> dataList; //要显示的数据
	
	public PageResult(int pageNum, int pageSize, List<T> sourceList){
		if(sourceList == null || sourceList.isEmpty()){
			return;
		}
		
		// 总记录条数
		this.totalRecord = sourceList.size();
		
		// 每页显示多少条记录
		this.pageSize = pageSize;
		
		//获取总页数
		this.totalPage = this.totalRecord / this.pageSize;
		if(this.totalRecord % this.pageSize !=0){
			this.totalPage = this.totalPage + 1;
		}
		
		// 当前第几页数据
		this.currentPage = this.totalPage < pageNum ?  this.totalPage : pageNum;
				
		// 起始索引
		int fromIndex	= this.pageSize * (this.currentPage -1);
		
		// 结束索引
		int toIndex  = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
				
		this.dataList = sourceList.subList(fromIndex, toIndex);
	}
	
	public PageResult(){
		
	}


	/**
	 *
	 * @param pageSize 每一页显示的条数
	 * @param pageNum	当前行数
	 * @param totalRecord 总共有多少条记录
	 * @param dataList
	 */
	public PageResult(int pageSize, int pageNum, int totalRecord,
			List<T> dataList) {
		
		super();
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0) totalPage++;
		int currentPage = pageNum/pageSize;
		if(pageNum%pageSize!=0)currentPage++;
		
		
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
