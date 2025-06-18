package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;

import com.example.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    MessageRepository messageRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //todo
    public Message CreateMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255){
            return null;
        }
        return messageRepository.save(message);
    }

    public List<Message> retrieveAllMessages(){
        return messageRepository.findAll();
    }

    //todo
    public Message retrieveMessageByID(int id){
        return messageRepository.getById(id);
    }

    //todo
    public int deleteMessageById(int id){
        Message messageToDel = messageRepository.getById(id);
        if(messageToDel==null){
            return 0;
        }
        return messageRepository.deleteMessageById(id);
    }

    //todo 
    public Message updateMessageById(int id, Message message){
        Message msg = messageRepository.getById(id);
        if( msg ==null){
            return null;
        }
        msg.setMessageText(message.getMessageText());
        return messageRepository.save(msg);
    }

    //todo
    public List<Message> retrieveMessagesByUser(int postedBy){   
        return messageRepository.findMessagesByPostedBy(postedBy);
    }


}
