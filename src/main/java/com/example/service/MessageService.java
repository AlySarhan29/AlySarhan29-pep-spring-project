package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;

import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //done
    public Message CreateMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255){
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
        return messageRepository.deleteMessageById(id);
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
