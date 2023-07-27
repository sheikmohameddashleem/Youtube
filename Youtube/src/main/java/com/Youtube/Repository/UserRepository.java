package com.Youtube.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Youtube.Model.User;

public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

	public Optional<User> findByusername(String username);
}
