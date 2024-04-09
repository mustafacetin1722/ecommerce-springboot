package com.mustafa.ecommercespringboot.enums;

import java.util.stream.Stream;

public enum UserRole {
    USER("user"),
    ADMIN("admin");

    private String description;

    UserRole(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public static UserRole value(String description){
        return Stream.of(UserRole.values())
                .filter(x -> x.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }

}