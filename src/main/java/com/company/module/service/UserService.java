package com.company.module.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.module.form.UserRegistForm;
import com.company.module.model.User;
import com.company.module.repo.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public User login(String id, String password) {
	return repository.findByIdAndSerect(id, password);

    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> findAll(Pageable pageable) {
	Assert.notNull(pageable, "Pageable must not be null!");
	return repository.findAll(pageable);
    }

    public User register(String username, String password) {
	return repository.save(new User(username,password,false));
    }

    public Optional<User> findById(String username) {
	return repository.findById(username);
    }

    public boolean delete(UserRegistForm userForm) {
	  Optional<User> find = repository.findById(userForm.getUsername());
	  find.ifPresentOrElse(a->{a.setDeleted(true);}, ()->{});
	  return find.isPresent();
	  
    }

}
