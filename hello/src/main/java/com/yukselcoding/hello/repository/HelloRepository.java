package com.yukselcoding.hello.repository;


import com.yukselcoding.hello.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {
    Hello findByName(String name);
}
