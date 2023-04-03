package com.example.demo.backend.repository;

import com.example.demo.backend.domain.UserCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCarRepository extends JpaRepository<UserCar, Long> {

}
