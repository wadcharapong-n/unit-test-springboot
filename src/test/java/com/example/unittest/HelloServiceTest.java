package com.example.unittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

//@SpringBootTest()
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class HelloServiceTest {

    @InjectMocks
    private HelloService helloService;

    @Mock
    private HelloRepository helloRepository;

    @Test
    public void getMessageByName() {
        Hello hello = new Hello();
        hello.setMessage("Hello ABC");
        Mockito.when(helloRepository.findByName(Mockito.any())).thenReturn(Optional.of(hello));
        Assertions.assertEquals(helloService.getMessageByName("xxx"),"Hello ABC");
    }


    @DisplayName("should not update because hello not found")
    @Test
    public void updateMessageHello() throws Exception {
        Hello hello = new Hello();
        hello.setId(1l);
        hello.setName("Test");
        hello.setMessage("Hello ABC");

        Mockito.when(helloRepository.existsById(1l)).thenReturn(false);

        helloService.updateMessageHello(1l,"xxx");

        Mockito.verify(helloRepository, times(1)).existsById(Mockito.any());
    }

    @DisplayName("should not update because hello not found")
    @Test
    public void updateMessageHello2() throws Exception {
        Hello hello = new Hello();
        hello.setId(1L);
        hello.setName("Test");
        hello.setMessage("Hello ABC");

        Mockito.when(helloRepository.existsById(1L)).thenReturn(true);
        Mockito.when(helloRepository.findById(1L)).thenReturn(Optional.of(hello));

        helloService.updateMessageHello(1L,"xxx");

        Mockito.verify(helloRepository, times(1)).save(Mockito.any());
    }
}