package com.cy.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;
@Data
@EqualsAndHashCode
/** 用户数据的实体类 */
public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String niname;
    private String address;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private Integer isDelete;
}
