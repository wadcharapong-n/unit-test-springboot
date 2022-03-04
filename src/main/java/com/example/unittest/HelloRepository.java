package com.example.unittest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HelloRepository extends JpaRepository<Hello,Long> {

    Optional<Hello> findByName(String name);
}
