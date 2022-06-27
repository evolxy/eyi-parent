package com.xu.server.base.pojo.bo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

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
		List<T> content = page.getRecords();
		res.setContent(content);
		// 总页数
		long totalPages = page.getPages();
		res.setTotalPages(totalPages);

		long size = page.getSize();

		long current = page.getSize();
		res.setCurrent(current);
		res.setSize(size);
		return res;
	}
}
