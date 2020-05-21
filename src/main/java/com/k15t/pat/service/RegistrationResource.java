package com.k15t.pat.service;

import com.k15t.pat.domain.Users;
import com.k15t.pat.exception.PatUserException;
import com.k15t.pat.repository.UsersRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RegistrationResource {

  Logger logger = LoggerFactory.getLogger(RegistrationResource.class);
  @Autowired
  UsersRepository usersRepository;

  public Users save(Users user) {
    logger.info("Saving user :: {}", user);
    return Optional.of(user)
        .map(users -> {
              if (Objects.isNull(usersRepository.findUserByEmail(user.getEmail()))) {
                return usersRepository.save(users);
              } else {
                throw new PatUserException("User already registered", user.getEmail());
              }
            }
        )
        .orElseThrow(() -> new PatUserException("Users object cannot be null"));

  }

  public List<Users> getUsers() {

    return usersRepository.findAll();

  }
}
