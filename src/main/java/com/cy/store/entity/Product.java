package com.cy.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode
/** 商品数据的实体类 */
public class Product extends BaseEntity implements Serializable {
    private Integer id;//商品ID
    private Integer categoryId;//分类
    private String itemType;//系列
    private String title;//品名
    private String sellPoint;//卖点描述
    private Long price;//价格
    private Integer num;//剩余数量
    private String image;//图片
    private Integer status;//状态1:上2：下3：删除
    private Integer priority;

}
