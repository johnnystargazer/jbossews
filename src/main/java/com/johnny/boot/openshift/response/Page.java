package com.johnny.boot.openshift.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;

/**
 * 
 * <p>
 * Page.java
 * <li>Introduce</li>
 * <li>First draft at 28 Nov 2011</li>
 * 
 * @author <a href="mailto:johnnywalee@gmail.com">Johnny</a>
 */

public class Page<T> {

	public static final String ASC = "asc";
	public static final String DESC = "desc";

	protected int pageNo = 1;
	protected int pageSize = 10;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;
	protected long totalPages = 0;
	protected Collection<T> result = new ArrayList<T>();
	protected long totalCount = -1;
	private boolean hasNext;
	private int nextPage;
	private boolean hasPre;
	private int prePage;

	public Page() {

	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public Page<T> pageSize(int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public long getLast() {
		if (hasNext) {
			return getFirst() + pageSize - 1;
		} else {
			return totalCount;
		}
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		String[] orders = StringUtils.split(lowcaseOrder, '`');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr)
					&& !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException(" Order " + orderStr
						+ "setOrder");
			}
		}

		this.order = lowcaseOrder;
	}

	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils
				.isNotBlank(order));
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Collection<T> getResult() {
		return result;
	}

	public void setResult(final Collection<T> result) {
		this.result = result;

	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
		this.totalPages = getTotalPages();
		this.hasNext = isHasNext();
		this.hasPre = isHasPre();
		this.nextPage = getNextPage();
		this.prePage = getPrePage();

	}

	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public Sort sort() {

		String[] orderByArray = StringUtils.split(getOrderBy(), ',');
		String[] orderArray = StringUtils.split(getOrder(), ',');

		Assert.isTrue(orderByArray.length == orderArray.length, "not true");
		List<Order> orders = new ArrayList<Order>();
		for (int i = 0; i < orderByArray.length; i++) {
			if (Page.ASC.equals(orderArray[i])) {
				orders.add(new Order(Direction.ASC, orderByArray[i]));
			} else {
				orders.add(new Order(Direction.DESC, orderByArray[i]));
			}
		}
		return new Sort(orders);
	}

}
