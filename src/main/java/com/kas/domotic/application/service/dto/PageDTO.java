package com.kas.domotic.application.service.dto;

import com.google.common.collect.ImmutableList;

public class PageDTO<T> {

	private final ImmutableList<T> content;
	private final long totalSize;
	private final boolean firstPage;
	private final boolean lastPage;
	
	private PageDTO(ImmutableList<T> content, long totalSize, boolean firstPage, boolean lastPage) {
		if(content == null) {
			throw new IllegalArgumentException("Element list must not be null");
		}
		this.content = content;
		this.totalSize = totalSize;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
			
	}
	
	public ImmutableList<T> getContent() {
		return content;
	}
	
	public long getTotalSize() {
		return totalSize;
	}
	
	public boolean isFirstPage() {
		return firstPage;
	}
	
	public boolean isLastPage() {
		return lastPage;
	}

	public static <T> PageDTO<T> of(ImmutableList<T> content, int number, long totalSize) {
		int totalPages = content.size() == 0 ? 1 : (int)((double) totalSize/ (double) content.size() + 1);
		boolean firstPage = !(number > 0);
		boolean lastPage = !(number + 1 < totalPages);
		return new PageDTO<>(content, totalSize, firstPage, lastPage);
	}
	
	public static <T> PageDTO<T> of(ImmutableList<T> content) {
		return PageDTO.of(content, 0, content.size());
	}
}
