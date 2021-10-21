package com.kas.domotic.application.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageParams {

	
	private int page;
	private int size;
	private Direction direction;
	private String columnName;
	
	private PageParams(int page, int size, Direction direction, String columnName) {
		super();
		this.page = page;
		this.size = size;
		this.direction = direction;
		this.columnName = columnName;
	}
	
	public static PageParams of(int page, int size, Direction direction, String columnName) {
		return new PageParams(page, size, direction, columnName);
	}
	
	public Sort getSort() {
		return Sort.by(direction, columnName);
	}
	
	public PageRequest getPageRequest() {
		return PageRequest.of(page, size, direction, columnName);
	}
	
	public int page() {
		return page;
	}
	
	public int size() {
		return size;
	}
	
}
