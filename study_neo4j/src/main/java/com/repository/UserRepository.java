package com.repository;

import com.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 谢益文 on 2017/7/13.
 */
@Repository
public interface  UserRepository extends GraphRepository<User> {
    @Query("start user = node({0}) "
           + " match (user)-[:IS_FRIEND_OF]->(u) return u")
    List<User> getFriends(User user);
}
