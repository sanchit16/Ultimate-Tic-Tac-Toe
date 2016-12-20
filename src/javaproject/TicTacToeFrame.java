
package javaproject;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import static javax.swing.JOptionPane.YES_OPTION;

public class TicTacToeFrame extends JFrame{
    private final JPanel jPanel1;
    private UltimateTicTacToe field;
    private final JLabel lblTurn;
    private final DrawPanel drawPanel1;
    private Users users;
    private Component frame;
    private String result;
    private int option;
    
    private int CalculatePos(int XSquare, int YSquare) {
        int x = 0;

        switch (YSquare) {
            case 0:
                if (XSquare == 0) {
                    x = 1;
                }
                if (XSquare == 1) {
                    x = 2;
                }
                if (XSquare == 2) {
                    x = 3;
                }
                break;
            case 1:
                if (XSquare == 0) {
                    x = 4;
                }
                if (XSquare == 1) {
                    x = 5;
                }
                if (XSquare == 2) {
                    x = 6;
                }
                break;
            case 2:
                if (XSquare == 0) {
                    x = 7;
                }
                if (XSquare == 1) {
                    x = 8;
                }
                if (XSquare == 2) {
                    x = 9;
                }
                break;
        }
        return x;
    }
    
    public TicTacToeFrame(){
        super("TicTacToe");
        setVisible(true);
        setSize(500,535);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        users = new Users();
        field = new UltimateTicTacToe();
        users.add(new User("x", "X"));
        users.add(new User("o", "O"));
        users.get(0).setTurn(true);
        users.get(1).setTurn(false);
        jPanel1 = new javax.swing.JPanel();
        lblTurn = new javax.swing.JLabel("X's turn");
        drawPanel1 = new DrawPanel();
        drawPanel1.setEnabled(true);
        drawPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawPanel1MouseClicked(evt);
            }
        });
        jPanel1.add(lblTurn);
        drawPanel1.setPreferredSize(new Dimension(475,475));
        add(drawPanel1,BorderLayout.NORTH);
        add(jPanel1,BorderLayout.SOUTH);
        
    }
    
    private void drawPanel1MouseClicked(java.awt.event.MouseEvent evt) {
        //TEST IF GAME HAS ENDED
        if (drawPanel1.isEnabled()) {
            //TEST IF IT'S THE USERS TURN TO PLAY
                //CALCUTE BIG GRID(x)
                int widthPerSquare = drawPanel1.getWidth() / 3;
                int heightPerSquare = drawPanel1.getHeight() / 3;
                int XSquare = evt.getX() / widthPerSquare;
                int YSquare = evt.getY() / heightPerSquare;

                int x = CalculatePos(XSquare, YSquare);

                //CALCULATE SMALL GRID (y)
                int widthPerSquareSmall = drawPanel1.getWidth() / 9;
                int heightPerSquareSmall = drawPanel1.getHeight() / 9;
                XSquare = (evt.getX() - (XSquare * widthPerSquare)) / widthPerSquareSmall;
                YSquare = (evt.getY() - (YSquare * heightPerSquare)) / heightPerSquareSmall;

                int y = CalculatePos(XSquare, YSquare);

                boolean valid=makeMove(x, y,users.getActiveUser());
                if(valid){
                    users.switchTurns();
                    lblTurn.setText(users.getActiveUser().getSign()+"'s Turn");
                }
                int set=drawPanel1.isFinished();
                switch(set){
                    case 0: drawPanel1.setEnabled(true);
                        break;
                    case 1: drawPanel1.setEnabled(false);
                        result="X won";
                        lblTurn.setText(result);
                        break;
                    case 2: drawPanel1.setEnabled(false);
                        result="0 won";
                        lblTurn.setText(result);
                        break;
                    case 3: drawPanel1.setEnabled(false);
                        result="Match Draw";
                        lblTurn.setText(result);
                        break;
                }
                if(set!=0){
                Object[] options = {"New Game",
                    "Exit"};
                option = JOptionPane.showOptionDialog(frame,
                result,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]);
                if(option==YES_OPTION){
                    setVisible(false);
                    dispose();
                    TicTacToeFrame newframe=new TicTacToeFrame();
                }
                else{
                    setVisible(false);
                    dispose();
                }
                }
        }
    }
    
    
    public boolean makeMove(int x, int y,User user) {
            //GET MOVE FROM SERVER
            //TEST WHICH USER HAS PLAYED
            boolean valid;
            if (user.getSign()=="X") {
                valid=drawPanel1.makeMove(x, y, "X");
            } else {
                valid=drawPanel1.makeMove(x, y, "O");
            }
            return valid;
        }

}
