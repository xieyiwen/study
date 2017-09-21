package com.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by 谢益文 on 2017/7/13.
 */
@RelationshipEntity(type = "IS_FRIEND_OF")
public class Friend {
    @StartNode
    private User startUser;
    @EndNode
    private User endUser;

    public User getStartUser() {
        return startUser;
    }

    public void setStartUser(User startUser) {
        this.startUser = startUser;
    }

    public User getEndUser() {
        return endUser;
    }

    public void setEndUser(User endUser) {
        this.endUser = endUser;
    }
}
