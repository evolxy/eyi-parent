package com.xu.server.base.controller;


import org.springframework.data.repository.CrudRepository;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/16 9:49
 */


public class BaseController<T, M extends CrudRepository<T, ID>, ID> {


}
