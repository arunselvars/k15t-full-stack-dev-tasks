package com.k15t.pat.repository;

import com.k15t.pat.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

  Users findUserByEmail(String email);
}
