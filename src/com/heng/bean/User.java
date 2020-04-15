package com.heng.bean;

import java.util.Objects;

/**
 @author 恒   2020/4/14 14:07 */

public class User {
    private int id;
    private String name;
    private String passWord;
    private String generd;
    private String address;

    public User() {}

    public User( String name, String passWord, String generd, String address) {
        this.name = name;
        this.passWord = passWord;
        this.generd = generd;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGenerd() {
        return generd;
    }

    public void setGenerd(String generd) {
        this.generd = generd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", passWord='" + passWord + '\'' + ", generd='" + generd + '\'' + ", address='" + address + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(passWord, user.passWord) && Objects.equals(generd, user.generd) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, passWord, generd, address);
    }
}
