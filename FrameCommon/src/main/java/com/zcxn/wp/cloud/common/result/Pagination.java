/**   
 * @Title: Pagination.java 
 * @Package: com.sitech.prm.iease.framework.base.dao 
 * @CopyRright (c)2008-2020: si-tech 
 * @Project: 易企算 iease-base Maven Webapp
 * @Description: 页面信息类
 * @author 人云 wanghwa@si-tech.com.cn
 * @date 2016-6-15 下午4:46:49 
 * @version V2.0   
 */
package com.zcxn.wp.cloud.common.result;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @Title: Pagination
 * @Description: 完成页面分页
 * @author 人云 wanghwa@si-tech.com.cn
 */
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = -5884976706259160221L;
	/**
	 * 上一页
	 */
	private long preIndex;
	/**
	 * 当前页
	 */
	private long curIndex;
	/**
	 * 下一页
	 */
	private long nextIndex;
	/**
	 * 每页条数
	 */
	private long pageSize;
	/**
	 * 总条数
	 */
	private long rowsCount;

	/**
	 * 总页数
	 */
	private long pagesCount;
	/**
	 * 对象列表
	 */
	private List<T> items;

	/**
	 * 
	 * 分页类构建函数
	 * 
	 */
	public Pagination() {
		updateInfo(0, 0, 0);
	}

	public void setPreIndex(long preIndex) {
		this.preIndex = preIndex;
	}

	public void setCurIndex(long curIndex) {
		this.curIndex = curIndex;
	}

	public void setNextIndex(long nextIndex) {
		this.nextIndex = nextIndex;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 
	 * @Description: 分页类构建函数
	 * @coustructor 方法.
	 * @param pageIndex
	 * @param pageSize
	 */
	public Pagination(long pageIndex, long pageSize) {
		updateInfo(pageIndex, pageSize, 0);
	}

	/**
	 * 
	 * @Description: 分页类构建函数
	 * @coustructor 方法.
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页记录数
	 * @param rowsCount
	 *            记录总数
	 */
	public Pagination(long pageIndex, long pageSize, long rowsCount) {
		updateInfo(pageIndex, pageSize, rowsCount);
	}

	/**
	 * 
	 * @Title: getItems
	 * @Description: 获取当前面记录
	 * @param @return 参数说明
	 * @return List<T> 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 
	 * @Title: setItems
	 * @Description: 设置当前页记录
	 * @param @param items 参数说明
	 * @return void 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}

	/**
	 * 
	 * @Title: getCurIndex
	 * @Description: 获取当前页码
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public long getCurIndex() {
		return curIndex;
	}

	/**
	 * 
	 * @Title: getNextIndex
	 * @Description: 获取下一页码
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public long getNextIndex() {
		return nextIndex;
	}

	/**
	 * 
	 * @Title: getPagesCount
	 * @Description: 获取总页数
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public long getPagesCount() {
		return pagesCount;
	}

	/**
	 * 
	 * @Title: getPageSize
	 * @Description: 获取每页记录数
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public long getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * @Title: getPreIndex
	 * @Description: 获取上一页码
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public long getPreIndex() {
		return preIndex;
	}

	/**
	 * 
	 * @Title: getRowsCount
	 * @Description: 获取总记录数
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public long getRowsCount() {
		return rowsCount;
	}

	/**
	 * 
	 * @Title: getFirstIndex
	 * @Description: 获取首页码
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@JsonIgnore
	public long getFirstIndex() {
		return 1;
	}

	/**
	 * 
	 * @Title: getLastIndex
	 * @Description: 获取末页码
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	@JsonIgnore
	public long getLastIndex() {
		return pagesCount;
	}

	private void updateInfo(long pageIndex, long pageSize, long rowsCount) {

		if (pageSize > 0) {

			this.curIndex = pageIndex;
			this.rowsCount = rowsCount;
			this.pageSize = pageSize;

			// 确定页数
			pagesCount = (rowsCount + pageSize - 1) / pageSize;
			// 确定当前页码
			if (curIndex <= 0)
				curIndex = 1;
			if (curIndex > pagesCount)
				curIndex = pagesCount;
			// 确定下一页码
			nextIndex = curIndex + 1;
			if (nextIndex > pagesCount)
				nextIndex = pagesCount;
			// 确定上一页码
			preIndex = curIndex - 1;
			if (preIndex <= 0)
				preIndex = 1;
		} else {
			this.preIndex = 1;
			this.curIndex = 1;
			this.nextIndex = 1;
			this.pageSize = 0;
			this.pagesCount = 1;
		}
	}

	/**
	 * 
	 * @Title: setRowsCount
	 * @Description: 设置总记录数
	 * @param @param rowsCount 参数说明
	 * @return void 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public void setRowsCount(long rowsCount) {
		updateInfo(curIndex, pageSize, rowsCount);
	}

	/**
	 * 
	 * @Title: setPagesCount
	 * @Description: 设置总页数
	 * @param @param pagesCount 参数说明
	 * @return void 返回类型
	 * @throws
	 * @author:人云 wanghwa@si-tech.com.cn
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public void setPagesCount(long pagesCount) {
		this.pagesCount = pagesCount;
	}

	@Override
	public String toString() {
		return "Pagination [preIndex=" + preIndex + ", curIndex=" + curIndex
				+ ", nextIndex=" + nextIndex + ", pageSize=" + pageSize
				+ ", rowsCount=" + rowsCount + ", pagesCount=" + pagesCount
				+ ", items=" + items + "]";
	}

}
