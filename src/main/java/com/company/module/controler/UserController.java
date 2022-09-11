package com.company.module.controler;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.module.constant.ErrorCode;
import com.company.module.dto.BooleanDTO;
import com.company.module.dto.ErrorDTO;
import com.company.module.dto.LoginDTO;
import com.company.module.form.UserLoginForm;
import com.company.module.form.UserRegistForm;
import com.company.module.model.User;
import com.company.module.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

    private UserService userService;

    @RequestMapping(value = "/get-all")
    public Page<User> users(@PageableDefault(size = 5) Pageable pageable) {
	return userService.findAll(pageable);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginForm userForm, BindingResult binding) {

	// validate error
	if (binding.hasErrors()) {
	    return ResponseEntity.ok(new ErrorDTO(ErrorCode.VALIDATE_ERROR, "validate",  toDTO( binding.getFieldErrors())));
	}
	
	try {
	     User user = userService.login(userForm.getUsername(), userForm.getPassword());
	     if(user==null) {
		 return ResponseEntity.ok(new ErrorDTO(ErrorCode.LOGIN_ERROR, "login.error", List.of()));
	     }
	    return ResponseEntity.ok(new LoginDTO(user.getId(),"todo_token"));
	} catch (Exception e) {
	    return ResponseEntity.ok(new ErrorDTO(ErrorCode.UNKNOW_ERROR, "error", null));
	}
    }

    @PostMapping("/delete/{id}")
    ResponseEntity<?> delete(@RequestBody @Valid UserRegistForm userForm, BindingResult binding) {

	if (binding.hasErrors()) {
	    return ResponseEntity.ok(new ErrorDTO(ErrorCode.VALIDATE_ERROR, "validate", toDTO(binding.getFieldErrors())));
	}
	try {
	    return ResponseEntity.ok(new BooleanDTO(userService.delete(userForm)));
	} catch (Exception e) {
	    return ResponseEntity.ok(new ErrorDTO(ErrorCode.UNKNOW_ERROR, "error", null));
	}
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistForm userForm, BindingResult binding) {

	if (binding.hasErrors()) {
	    return ResponseEntity.ok(new ErrorDTO(ErrorCode.VALIDATE_ERROR, "validate", toDTO(binding.getFieldErrors())));
	}
	try {
	    return ResponseEntity.ok(userService.register(userForm.getUsername(), userForm.getPassword()));
	} catch (Exception e) {
	    return ResponseEntity.ok(new ErrorDTO(ErrorCode.UNKNOW_ERROR, "error", null));
	}
    }

}