package com.xu.server.admin.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xu.server.admin.article.pojo.entity.Catalog;
import com.xu.server.admin.article.repository.ICatalogRepository;
import com.xu.server.admin.article.service.ICatalogService;
import com.xu.server.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/27 15:26
 */
@Service
public class CatalogServiceImpl extends BaseServiceImpl<Catalog, ICatalogRepository> implements ICatalogService {

	@Override
	public List<Catalog> getOrSave(List<Catalog> catalogs) {
		Map<String, Integer> map = new HashMap<>();
		List<String> names = new ArrayList<>();
		for (int i = 0; i < catalogs.size(); i++) {
			String name = catalogs.get(i).getCatalogName();
			names.add(name);
			map.put(name, i);
		}
		LambdaQueryWrapper<Catalog> qw = new LambdaQueryWrapper<>();
		qw.in(Catalog::getCatalogName, names).eq(Catalog::getDelFlag, 0);
		List<Catalog> list = list(qw);
		if (list.size() == catalogs.size()) {
			return list;
		}
		// 找出未存在数据库中的数据
		List<String> savedNames = list.stream().map(Catalog::getCatalogName).collect(Collectors.toList());
		List<Catalog> needSaveList = new ArrayList<>();
		map.forEach((k, v) -> {
			if (!savedNames.contains(k)) {
				needSaveList.add(catalogs.get(v));
			}
		});
		if (CollectionUtils.isNotEmpty(needSaveList)) {
			saveBatch(needSaveList);
			list.addAll(needSaveList);
		}
		return list;
	}
}
