package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.entity.ResponseResult;
import com.cy.store.entity.User;
import com.cy.store.service.IOrderService;
import com.cy.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

    @PostMapping("create")
    public ResponseResult<Order> create(@RequestBody Integer[] cids, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        User user = userService.getByUid(uid);
        String username = user.getUsername();
        // 调用业务对象执行业务
        Order data = orderService.create(cids, uid, username);
        // 返回成功与数据
        return new ResponseResult<>(OK, "下单成功",data);
    }
    @PostMapping("pay")
    public ResponseResult<Order> create(@RequestBody Order order, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        User user = userService.getByUid(uid);
        // 调用业务对象执行业务
        Order data = orderService.pay(order);
        // 返回成功与数据
        return new ResponseResult<>(OK, "支付成功",data);
    }
}
