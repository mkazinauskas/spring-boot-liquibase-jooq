package com.modzo.jooq;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static nu.studer.sample.public_.tables.Users.USERS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JooqApplicationTests {

    @Autowired
    DSLContext context;

    @Test
    public void contextLoads() {
        context.insertInto(USERS)
                .set(USERS.EMAIL, "email@test.com")
                .set(USERS.NAME, "test name")
                .set(USERS.SURNAME, "test surname")
                .execute();

        assert context.select(USERS.NAME).from(USERS)
                .where(USERS.EMAIL.eq("email@test.com"))
                .fetchOne(USERS.NAME).equals("test name");
    }

}
