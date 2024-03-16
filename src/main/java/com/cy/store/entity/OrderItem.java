package com.cy.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode
/** 订单中的商品数据 */
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;

}
