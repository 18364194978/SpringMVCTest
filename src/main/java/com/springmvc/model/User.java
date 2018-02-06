package com.springmvc.model;
import java.io.Serializable;
/**
 * Created by xie on 2018/2/6.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String password;
    private String sex;
    public User(){}
    public User(String id,String username,String password,String sex){
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
    }
    public String getId(){return id;}

    public void setId(String id){this.id =id;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username =username;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password =password;}

    public String getSex(){return sex;}

    public void setSex(String sex){this.sex =sex;}
    @Override
    public String toString(){
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
