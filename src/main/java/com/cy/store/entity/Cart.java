package com.cy.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode
/** 购物车数据的实体类 */
public class Cart extends BaseEntity implements Serializable {
    private Integer cid;//购物车号
    private Integer uid;//用户号
    private Integer pid;//商品号
    private Long price;//价格
    private Integer num;//数量


}
