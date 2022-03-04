package com.example.unittest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.Optional;

import static org.mockito.Mockito.times;

@SpringBootTest()
//@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
public class HelloServiceTestIn {

    @Autowired
    HelloService helloService;

    @Autowired
    HelloRepository helloRepository;

    @BeforeEach
    void setUp() {
        Hello hello = new Hello();
        hello.setId(1L);
        hello.setName("Test");
        hello.setMessage("Hello ABC");
        helloRepository.save(hello);
        System.out.println("Insert data mock !!!!");
    }

    @Test
    public void getMessageByName() {
        Assertions.assertEquals(helloService.getMessageByName("Test"),"Hello ABC");
    }


    @DisplayName("should update hello message")
    @Test
    public void updateMessageHello() {
        helloService.updateMessageHello(1L,"XXX");
        Hello hello = helloRepository.findById(1L).orElse(new Hello());
        Assertions.assertEquals("XXX",hello.getMessage());
    }

    @DisplayName("should not update because hello not found")
    @Test
    public void updateMessageHello2() {
        helloService.updateMessageHello(2L,"XXX");
        Hello hello = helloRepository.findById(1L).orElse(new Hello());
        Assertions.assertNotEquals("XXX",hello.getMessage());
    }

    @DisplayName("should update hello message 2")
    @Test
    public void updateMessageHelloNotCheckExist() throws Exception {
        helloService.updateMessageHelloNotCheckExist(1L,"XXX");
        Hello hello = helloRepository.findById(1L).orElse(new Hello());
        Assertions.assertEquals("XXX",hello.getMessage());
    }

    @DisplayName("should not update because hello not found 2")
    @Test()
    public void updateMessageHelloNotCheckExist2() {
        Assertions.assertThrows(Exception.class, () -> helloService.updateMessageHelloNotCheckExist(2L,"XXX"));
    }
}