package com.example.vkapitest.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VkFriend extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;

    VkFriend(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public VkFriend() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
