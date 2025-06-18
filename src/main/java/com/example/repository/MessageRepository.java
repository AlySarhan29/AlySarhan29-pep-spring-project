package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query("DELETE FROM Message m WHERE m.id = :id")
    int deleteMessageById(@Param("id") long id);

    List<Message> findMessagesByPostedBy(long postedBy);

    
}
