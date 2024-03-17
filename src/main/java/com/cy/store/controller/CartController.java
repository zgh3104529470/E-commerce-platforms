package com.cy.store.controller;

import com.cy.store.entity.Cart;
import com.cy.store.entity.ResponseResult;
import com.cy.store.entity.User;
import com.cy.store.service.ICartService;
import com.cy.store.service.IUserService;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IUserService userService;

    @PostMapping("/add_to_cart")
    public ResponseResult<Void> addToCart(@RequestBody Cart cart, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        User user = userService.getByUid(uid);
        String username = user.getUsername();
        System.out.println(cart.getPrice());
        System.out.println(cart.getNum());
        // 调用业务对象执行添加到购物车
        System.out.println(cart.getPid());
        cartService.addToCart(uid, cart.getPid(), cart.getNum(), username);
        // 返回成功
        return new ResponseResult<>(OK,"添加成功");
    }

    @GetMapping({"", "/"})
    public ResponseResult<List<CartVO>> getVOByUid(HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByUid(uid);
        // 返回成功与数据
        return new ResponseResult<List<CartVO>>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public ResponseResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        User user = userService.getByUid(uid);
        String username = user.getUsername();
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        // 返回成功
        return new ResponseResult<>(OK,"添加成功" ,data);
    }

    @GetMapping("list")
    public ResponseResult<List<CartVO>> getVOByCids(Integer[] cids, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByCids(uid, cids);
        // 返回成功与数据
        return new ResponseResult<>(OK, "查询成功",data);
    }
}
