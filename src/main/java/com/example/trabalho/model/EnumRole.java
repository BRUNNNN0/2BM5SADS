package com.example.trabalho.model;

public enum EnumRole {
        ADMIN("admin"),
        USER("user");

        public String role;
    EnumRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}
