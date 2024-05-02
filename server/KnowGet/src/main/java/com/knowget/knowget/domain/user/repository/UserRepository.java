package com.knowget.knowget.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowget.knowget.global.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
