package com.company.module.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserLoginForm {
    @NotEmpty(message = "username.empty")
    String username;
    String password;
}