
package javaproject;

import java.util.ArrayList;

public class Users extends ArrayList<User> {

    public boolean containUser(String userName) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    
    public int containUserName(String username) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getUserName().equals(username)){
                return i;
            }
        }
        return -1;
    }
    
    public void switchTurns(){
        for (int i=0;i<this.size();i++){
            this.get(i).setTurn(!(this.get(i).isTurn()));
        }
    }
    
    public User getActiveUser(){
        for (int i=0;i<size();i++){
            if (this.get(i).isTurn()){
                return this.get(i);
            }
        }
        return null;
    }
}
