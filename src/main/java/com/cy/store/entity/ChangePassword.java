package com.cy.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
}
