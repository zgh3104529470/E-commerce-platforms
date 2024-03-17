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
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

    @PostMapping("create")
    public ResponseResult<Order> create(@RequestBody String[] cids, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        User user = userService.getByUid(uid);
        String username = user.getUsername();
        System.out.println("1");
        Integer[] Cids = new Integer[10];
        for (int i = 0; i < cids.length; i++) {
            Cids[i] = Integer.valueOf(cids[i]);
        }
        // 调用业务对象执行业务
        Order data = orderService.create(Cids, uid, username);
        // 返回成功与数据
        return new ResponseResult<>(OK, "下单成功",data);
    }
    @PostMapping("pay_out")
    public ResponseResult<Order> payout(@RequestBody Order order, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        User user = userService.getByUid(uid);
        // 调用业务对象执行业务
        Order data = orderService.payOut(order);
        // 返回成功与数据
        return new ResponseResult<>(OK, "支付成功",data);
    }
}
