package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.websocket.server.PathParam;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService= accountService;
        this.messageService = messageService;
    }

    @PostMapping(value ="/register")
    public ResponseEntity<Account> register(@RequestBody Account account){
        Account ac = accountService.Resgister(account);
        if(ac !=null){
            return ResponseEntity.status(200).body(ac);
        }else{

        }
        return null;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        return ResponseEntity.status(200).body(account);
    }

    @PostMapping(value = "/messages")
    public ResponseEntity<Message> creationMessage(@RequestBody Message message){
        return ResponseEntity.status(200).body(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages(){
        return null;
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<List<Message>> retrieveMessageById(@PathVariable Long messageId){
        return null;
    }

    @DeleteMapping("/messages/{messageId}")
    public Long deleteMessageById(@PathVariable Long messageId ){
        return null;
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Message> updateMessageById(@PathVariable Long messageId){
        return null;
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> retrieveAllMessagesByUser(){
        return null;
    }
}
