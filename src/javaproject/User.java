
package javaproject;

public class User {
    
    private String userName;
    private String sign;
    private Boolean turn;

    public User(String userName, String sign) {
        this.userName = userName;
        this.sign=sign;
        this.turn=null;
    }
    
    public User(String userName){
        this.userName = userName;
        this.turn = null;
    }

    public String getUserName() {
        return userName;
    }

    public String getSign() {
        return sign;
    }
    
    public Boolean isTurn() {
        return turn;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setTurn(Boolean turn) {
        this.turn = turn;
    }
}

