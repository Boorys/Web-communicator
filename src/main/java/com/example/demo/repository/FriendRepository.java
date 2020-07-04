package com.example.demo.repository;


import com.example.demo.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    FriendEntity findFriendEntityById(long id);

    @Query(value="select * from (\n" +
            "select u_e.first_name, fe.id as friend_id , u_e.user_id user_id_friend,ue.user_id from user_entity as ue join friend_entity as fe on ue.id = fe.owner_id\n" +
            "join user_entity as u_e on u_e.id = fe.friend_id\n" +
            "union\n" +
            "select u_e.first_name, fe.id as friend_id, u_e.user_id user_id_friend ,ue.user_id from user_entity as ue join friend_entity as fe on ue.id = fe.friend_id\n" +
            "join user_entity as u_e on u_e.id = fe.owner_id)a\n" +
            "\n" +
            "where a.user_id = :userId",nativeQuery = true)
    List<Object[]> findFriendGetDtoByUserId(@Param("userId") String userId);


    @Query(value="select first_name,user_id from user_entity\n" +
            "where first_name like :firstName%",nativeQuery = true)
    List<Object[]> findByFirstName(String firstName);

}
