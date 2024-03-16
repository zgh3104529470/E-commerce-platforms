package com.cy.store.controller;

import com.cy.store.entity.Product;
import com.cy.store.entity.ResponseResult;
import com.cy.store.service.IProductService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public ResponseResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new ResponseResult<>(OK, "查询成功",data);
    }
    @RequestMapping("cate_list")
    public ResponseResult<List<Product>> getCateList(Integer categoryId) {
        List<Product> data = productService.findCateList(categoryId);
        return new ResponseResult<>(OK, "查询成功",data);
    }

    @GetMapping("{id}/details")
    public ResponseResult<Product> getById(@PathVariable("id") Integer id) {
        // 调用业务对象执行获取数据
        Product data = productService.findById(id);
        // 返回成功和数据
        return new ResponseResult<>(OK,"查询成功", data);
    }
}
