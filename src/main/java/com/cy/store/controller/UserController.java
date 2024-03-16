package com.cy.store.controller;

import com.cy.store.entity.ChangePassword;
import com.cy.store.entity.ResponseResult;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import com.cy.store.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** 处理用户相关请求的控制器类 */
@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public ResponseResult reg(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        // 调用业务对象执行注册
        userService.reg(user);
        // 返回
        return new ResponseResult(200,"注册成功");
    }


    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        //校验用户名密码是否正确
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        Map<String, Object> map;
        if (loginUser != null) {
            //如果正确 生成token返回
            map = new HashMap<>();
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(),
                    String.valueOf(loginUser.getUid()), null);
            map.put("token", token);
        } else {
            //如果不正确 给出相应的提示
            return new ResponseResult(300, "用户名或密码错误，请重新登录");
        }
        return new ResponseResult(200, "登录成功", map);
    }

    @PostMapping("change_password")
    public ResponseResult changePassword(@RequestBody ChangePassword changePassword,
                                         HttpServletRequest request) {
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        System.out.println(jwtSubject);
        Integer uid = Integer.valueOf(jwtSubject);
        User user = userService.getByUid(uid);
        String username = user.getUsername();
        // 调用业务对象执行修改密码
        userService.changePassword(uid, username,changePassword.getOldPassword(), changePassword.getNewPassword());
        // 返回成功
        return new ResponseResult(OK,"修改成功");
    }

    @GetMapping("get_by_uid")
    public ResponseResult<User> getByUid(HttpServletRequest request) {
        System.out.println(1);
        // 从HttpServletRequest对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        System.out.println(jwtSubject);
        Integer uid = Integer.valueOf(jwtSubject);
        System.out.println(uid);
        // 调用业务对象执行获取数据
        User data = userService.getByUid(uid);
        // 响应成功和数据
        return new ResponseResult<>(OK,"获取成功",data);
    }

    @PostMapping("change_info")
    public ResponseResult changeInfo(@RequestBody User user, HttpServletRequest request) {
        // 从HttpServletRequest(token)对象中获取uid
        String jwtSubject = (String) request.getAttribute("jwtSubject");
        Integer uid = Integer.valueOf(jwtSubject);
        User user1 = userService.getByUid(uid);
        String username = user1.getUsername();
        System.out.println(uid);
        // 调用业务对象执行修改用户资料
        userService.changeInfo(uid, username, user);
        // 响应成功
        return new ResponseResult(OK,"用户信息上传成功");
    }
}

