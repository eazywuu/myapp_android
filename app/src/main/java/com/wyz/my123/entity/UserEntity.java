package com.wyz.my123.entity;

public class UserEntity {
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 昵称
     */
    private String displayName;
    /**
     * 做题总数
     */
    private Long doTotal;

    public UserEntity(Integer id, String userName, String userPwd, String displayName, Long doTotal) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
        this.displayName = displayName;
        this.doTotal = doTotal;
    }

    public UserEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getDoTotal() {
        return doTotal;
    }

    public void setDoTotal(Long doTotal) {
        this.doTotal = doTotal;
    }
}
