package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

// This method is no longer used. It was initially added due to a misunderstanding of the delete operation —
// I assumed it might return the number of deleted records for a given ID, or allow multiple deletions per ID.
// I've kept it for now simply because I like how it looks :)
    @Query("DELETE FROM Message m WHERE m.messageId = :id")
    int deleteMessageById(@Param("id") long id);

    List<Message> findMessagesByPostedBy(Integer postedBy);


    
}
