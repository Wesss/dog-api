package com.wesdevelop.dogapi.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dog {
    private long id;
    private String name;
    private String ownerName;
    private String notes;

    public Dog() {
        // Jackson deserialization
    }

    public Dog(long id, String name, String ownerName, String notes) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.notes = notes;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getOwnerName() {
        return ownerName;
    }

    @JsonProperty
    public String getNotes() {
        return notes;
    }
}