package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    //done
    public Message CreateMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255){
            return null;
        }
        if (message.getPostedBy() == null || accountRepository.findById(message.getPostedBy()).isEmpty()) {
            return null; 
        }
        return messageRepository.save(message);
    }

    //done
    public List<Message> retrieveAllMessages(){
        return messageRepository.findAll();
    }

    //maybe
    public Message retrieveMessageByID(int id){
        if(messageRepository.findById(id) ==null){
            return null;
        }

        return messageRepository.findById(id).orElse(null);
    }

    //Done
    public int deleteMessageById(int id){
        Message messageToDel = messageRepository.findById(id).orElse(null);
        if(messageToDel == null){
            return 0;
        }
        // return messageRepository.deleteMessageById(id);
        messageRepository.deleteById(id);
        return 1;
    }

    //Done
    public Message updateMessageById(int id, Message message){
        Message msg = messageRepository.findById(id).orElse(null);
        if( msg ==null || message.getMessageText() ==null || message.getMessageText().isBlank() || message.getMessageText().length() >255){
            return null;
        }
        msg.setMessageText(message.getMessageText());
        return messageRepository.save(msg);
    }

    //Done
    public List<Message> retrieveMessagesByUser(int postedBy){   
        return messageRepository.findMessagesByPostedBy(postedBy);
    }


}
