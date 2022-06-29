package com.xu.server.api.article.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.server.api.article.pojo.vo.ApiArticleVo;
import com.xu.server.api.article.pojo.vo.ApiCatalogVo;
import com.xu.server.api.article.repository.ApiArticleRepository;
import com.xu.server.api.article.service.IApiArticleService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/6/29 16:46
 */
@Service
@AllArgsConstructor
public class ApiArticleServiceImpl implements IApiArticleService {
	private final ApiArticleRepository apiArticleRepository;

	@Override
	public Page<ApiArticleVo> queryArticlePageByUserId(int current, int size, Long userId) {
		Map<Long, Integer> artIdxMap = new HashMap<>(16);
		Page<ApiArticleVo> page = new Page<>(current, size);
		Page<ApiArticleVo> vos = apiArticleRepository.selectApiArticleInfoList(page, userId);
		List<ApiArticleVo> records = vos.getRecords();
		if (CollectionUtils.isEmpty(records)) {
			return vos;
		}
		List<Long> articleIds = new ArrayList<>();
		for (int i = 0; i < records.size(); i++) {
			Long id = records.get(i).getId();
			artIdxMap.put(id, i);
			articleIds.add(id);
		}

		List<ApiCatalogVo> apiCatalogVos = apiArticleRepository.selectCatalogByArticleId(articleIds);
		for (ApiCatalogVo vo : apiCatalogVos) {
			Long id = vo.getArticleId();
			Integer idx = artIdxMap.get(id);
			if (idx != null) {
				ApiArticleVo articleVo = records.get(idx);
				List<ApiCatalogVo> catalogs = articleVo.getCatalogs();
				if (CollectionUtils.isEmpty(catalogs)) {
					catalogs = new ArrayList<>();
					articleVo.setCatalogs(catalogs);
				}
				catalogs.add(vo);
			}
		}
		return vos;
	}
}
