package com.company.module.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRegistForm {

    @NotEmpty(message = "username.empty")
    @Length(min = 4, max = 32, message = "username.length.4.to.16")
    @Pattern(regexp = "^[a-z]+$", message = "username.contains.only.az")
    String username;

    @Length(min = 8, max = 32, message = "password.length.8.to.32")
    @Pattern(regexp = ".[\\*\\!~@#\\$%\\^&].*", message = "message.must.contains.special")
    @Pattern(regexp = ".[A-Z].*", message = "message.must.contains.AZ")
    @Pattern(regexp = ".[a-z].*", message = "message.must.contains.az")
    @Pattern(regexp = ".[0-9].*", message = "message.must.contains.09")
    String password;

}