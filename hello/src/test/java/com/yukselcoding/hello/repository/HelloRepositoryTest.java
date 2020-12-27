package com.yukselcoding.hello.repository;


import com.yukselcoding.hello.entity.Hello;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE, connection = EmbeddedDatabaseConnection.H2)
public class HelloRepositoryTest {
    @Autowired
    private HelloRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByName_returnsHello() {
        String name = "Ozge";
        Hello savedHello = entityManager.persistFlushFind(new Hello(name));
        Hello hello = repository.findByName(name);
        assertEquals(hello.getName(), savedHello.getName());
    }

    @Test
    public void save_returnsHello() {
        String name = "Ozge";
        Hello hello = repository.save(new Hello(name));
        assertThat(hello).hasFieldOrPropertyWithValue("name", name);
    }

}
