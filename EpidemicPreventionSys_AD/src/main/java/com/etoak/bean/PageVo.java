package com.etoak.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {
	private int pageNumber;
	private int pageSize;
	private List<T> rows;
	private long total;
	private int pageCount; 
	private boolean first; 
	private boolean last; 
}
