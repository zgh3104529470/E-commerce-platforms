package com.cy.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
@Data
@EqualsAndHashCode
/** 订单数据的实体类 */
public class Order extends BaseEntity implements Serializable {
    private Integer oid;//单号
    private Integer uid;//下单用户
    private String recvName;//用户名
    private String recvPhone;//手机号
    private String recvAddress;//地址
    private Long totalPrice;//合计价格
    private Integer status;//支付状态0:1:2:3未支付，已支付，已取消，已完成
    private Date orderTime;//下单时间
    private Date payTime;//支付时间

}
