package request;

import client.Content;

public class Request {

    private int type;
    private String fromUser;
    private String password;
    private String toUser;
    private String toGroup;
    private Content content;
    public String id;

    private boolean login = true;
    private String avatar;

    public Request() {

    }

    public Request(int type, String fromUser, String password, String toUser, String toGroup, Content content) {
        this.type = type;
        this.fromUser = fromUser;
        this.password = password;

        this.toUser = toUser;
        this.toGroup = toGroup;
        this.content = content;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToGroup() {
        return toGroup;
    }

    public void setToGroup(String toGroup) {
        this.toGroup = toGroup;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
    
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Request{" + "type=" + type + ", fromUser=" + fromUser + ", password=" + password + ", toUser=" + toUser + ", toGroup=" + toGroup + ", content=" + content + ", login=" + login + '}';
    }

}
