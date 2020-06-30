package com.example.demo.repository;

import com.example.demo.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long> {

    @Query(value="select top(:numberMessage) me.text_message,me.date,me.user_id from message_entity as me " +
            " join friend_entity as fe on fe.id=me.friend_id " +
            " where me.friend_id=:id ORDER BY me.id DESC",nativeQuery = true)
    List<Object[]> getMessageByFriendId(@Param("id") String id,@Param("numberMessage") int numberMessage);

}
