package com.xu.server.base.controller;


import com.xu.commons.result.Result;
import com.xu.server.base.pojo.bo.PageParam;
import com.xu.server.base.pojo.entity.BaseEntity;
import com.xu.server.base.service.IBaseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:49
 */

@Slf4j
public class BaseController<T extends BaseEntity, M extends IBaseService<T>> {
    @Autowired
    protected M service;

    @GetMapping("/list")
    @ApiOperation("get List")
    public Result<List<T>> list(@RequestBody(required = false) T entity) {
        List<T> list = service.list(entity);
        log.info("get list size= {}", list.size());
        log.trace("get list {}", list);
        return Result.ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("get page")
    public Result<?> page(@RequestParam int current, @RequestParam int size, T entity) {
        if (current < 1) {
            return Result.failed("无效的页数");
        }
        Page<T> res = service.page(current, size, entity);
        PageParam<T> p = PageParam.convertToPage(res);
        return Result.ok(p);
    }

    @GetMapping("/{id}")
    @ApiOperation("get by id")
    public Result<?> getById(@PathVariable Long id) {
        return Result.ok(service.getById(id));
    }

    @PutMapping("/")
    @ApiOperation("update by id")
    public Result<?> updateById(@RequestBody T entity) {
        return Result.ok(service.saveOrUpdate(entity)) ;
    }

    @PostMapping("/")
    @ApiOperation("save")
    public Result<?> save(@RequestBody T entity) {
        return Result.ok(service.saveOrUpdate(entity)) ;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete by id")
    public Result<?> removeById(@PathVariable Long id) {
        return Result.ok(service.removeById(id));
    }
}
