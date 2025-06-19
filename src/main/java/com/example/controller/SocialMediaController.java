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

    //done
    @PostMapping(value ="/register")
    public ResponseEntity<Account> register(@RequestBody Account account){
        Account ac = accountService.Register(account);
        if(ac !=null){
            return ResponseEntity.status(200).body(ac);
        }else if(accountService.checkForDup(account) == null){
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(400).build();
    }

    //done
    @PostMapping(value = "/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account ac = accountService.Login(account);
        if(ac != null){
            return ResponseEntity.status(200).body(ac);           
        }
        return ResponseEntity.status(401).build();
    }

    //done
    @PostMapping(value = "/messages")
    public ResponseEntity<Message> creationMessage(@RequestBody Message message){
        Message msg = messageService.CreateMessage(message);
        if(msg != null){
            return ResponseEntity.ok(msg);
        }else{
            return ResponseEntity.status(400).build();
        }
        
    }

    //done
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages(){
        return ResponseEntity.ok(messageService.retrieveAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> retrieveMessageById(@PathVariable Integer messageId){
        Message msg = messageService.retrieveMessageByID(messageId);
        if(msg != null){
            return ResponseEntity.ok(msg);
        }
        return ResponseEntity.ok().build();
    }

    //done
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId ){
        int msgDelNum = messageService.deleteMessageById(messageId);
        if(msgDelNum > 0){
            return ResponseEntity.ok(msgDelNum);
        }
        return ResponseEntity.ok().build();
    }

    //done
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer messageId, @RequestBody Message message){
        Message msgUpd = messageService.updateMessageById(messageId, message);
        if(msgUpd != null){
            return ResponseEntity.ok(1);
        }
        return ResponseEntity.status(400).build();
    }

    //done
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> retrieveAllMessagesByUser(@PathVariable Integer accountId){
        return ResponseEntity.ok(messageService.retrieveMessagesByUser(accountId));
    }
}
