package com.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by 谢益文 on 2017/7/13.
 */
@NodeEntity(label = "User")
public class User {
    @GraphId
    private Long graphId;

    @Index
    private Long userId;
    private String userName;
    private String userEmail;
    private Integer age;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "graphId=" + graphId + ", userId=" + userId + ", userName='" + userName
               + '\'' + ", userEmail='" + userEmail + '\'' + ", age=" + age + '}';
    }
}
