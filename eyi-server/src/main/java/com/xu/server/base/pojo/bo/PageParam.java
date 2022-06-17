package com.xu.server.base.pojo.bo;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/17 14:42
 */
@Data
public class PageParam<T> {
	private long current;

	private long size;

	private long totalPages;

	private long totalElements;

	private List<T> content;

	public static <T> PageParam<T> convertToPage(Page<T> page) {
		PageParam<T> res = new PageParam<>();
		// 数据
		List<T> content = page.getContent();
		res.setContent(content);
		// 总页数
		int totalPages = page.getTotalPages();
		res.setTotalPages(totalPages);

		int size = page.getSize();
		long totalElements = page.getTotalElements();
		int current = page.getNumber() + 1;
		res.setCurrent(current);
		res.setSize(size);
		res.setTotalElements(totalElements);
		return res;
	}
}
