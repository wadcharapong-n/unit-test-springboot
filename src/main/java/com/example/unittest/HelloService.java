package com.example.unittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HelloService {

    @Autowired
    HelloRepository helloRepository;

    public String getMessageByName(String name){
        return helloRepository.findByName(name).orElse(new Hello()).getMessage();
    }

    public void updateMessageHello(Long helloId,String message){
        if(helloRepository.existsById(helloId)){
            Hello hello = helloRepository.findById(helloId).orElse(new Hello());
            hello.setMessage(message);
            helloRepository.save(hello);
        }
    }

    public void updateMessageHelloNotCheckExist(Long helloId,String message) throws Exception {
        Hello hello = helloRepository.findById(helloId).orElseThrow(Exception::new);
        hello.setMessage(message);
        helloRepository.save(hello);
    }

}
