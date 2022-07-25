package com.xu.server.admin.article.service;

import com.xu.server.admin.article.pojo.entity.Catalog;
import com.xu.server.base.service.IBaseService;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/27 15:25
 */

public interface ICatalogService extends IBaseService<Catalog> {
	/**
	 * 获取 或者保存
	 * @param catalogs 标签
	 * @return list
	 */
	List<Catalog> getOrSave(List<Catalog> catalogs);
}
