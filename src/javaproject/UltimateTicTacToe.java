
package javaproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


public class UltimateTicTacToe extends ArrayList<TicTacToe> {

    int lastMove = -1;
    String sign = "";

    public UltimateTicTacToe() {
        for (int i = 0; i <= 9; i++) {
            this.add(new TicTacToe(String.valueOf(i)));
        }
    }

    public Boolean makeMove(int x, int y, String sign) {
        this.sign = sign;
        if (lastMove != -1) {
            //TEST IF THE FIELD FOR THE NEXT MOVE IS'NT FINISHED ALREADY
            if ((String.valueOf(lastMove).equals(this.get(lastMove).isFinished())) && (!this.get(lastMove).isFull())) {
                //TEST IF MOVES IS VALID
                if (x == lastMove) {
                    //TEST IF THE FIELD IS ALREADY SET
                    if (this.get(x).set(y, sign).equals("")) {
                        return false;
                    } else {
                        lastMove = y;
                        return true;
                    }
                } 
                else {
                    return false;
                }
            } else {
                //TEST IF MOVE IS NOT IN A FINISHED FIELD
                if ((!("X".equals(this.get(x).isFinished()))) && ((!"O".equals(this.get(x).isFinished())))) {
                    //TEST IF THE FIELD IS ALREADY SET
                    if (this.get(x).set(y, sign).equals("")) {
                        return false;
                    } else {
                        lastMove = y;
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (this.get(x).set(y, sign).equals("")) {
                return false;
            } else {
                lastMove = y;
                return true;
            }
        }

    }

    //RETURNS SIGN OF THE WINNER
    public String isFinished() {
        //TEST IF GAME IS FINISHED
        if ((this.get(1).isFinished().equals(this.get(5).isFinished())) && (this.get(1).isFinished().equals(this.get(9).isFinished()))) {
            return this.get(1).isFinished();
        }
        //VERTICAL CHECK
        for (int i = 1; i <= 3; i++) {
            if ((this.get(i).isFinished().equals(this.get(i + 3).isFinished())) && (this.get(i).isFinished().equals(this.get(i + 6).isFinished()))) {
                return this.get(i).isFinished();
            }
        }
        //HORIZONTAL CHECK
        for (int i = 1; i <= 7; i = i + 3) {
            if ((this.get(i).isFinished().equals(this.get(i + 1).isFinished())) && (this.get(i).isFinished().equals(this.get(i + 2).isFinished()))) {
                return this.get(i).isFinished();
            }
        }

        if ((this.get(3).isFinished().equals(this.get(5).isFinished())) && (this.get(3).isFinished().equals(this.get(7).isFinished()))) {
            return this.get(3).isFinished();
        }
        return null;

    }

    void drawGrid(Graphics2D g, int width, int height) {

        int offset = 10;
        // DRAW MAIN GRID
        //VERTICAL
        g.drawLine((width / 3), offset, (width / 3), (height) - offset);
        g.drawLine((width / 3) * 2, offset, (width / 3) * 2, height - offset);
        // HORIZONTAL
        g.drawLine(offset, (height / 3), width - offset, (height / 3));
        g.drawLine(offset, (height / 3) * 2, width - offset, (height / 3) * 2);

        g.setStroke(new BasicStroke(1));

        //DRAW SMALL GRIDS
        for (int i = 1; i <= 9; i++) {
            Point pos = TicTacToe.CalculatePosX(i, width / 3, height / 3);
            this.get(i).drawGrid(g, (int) pos.getX(), (int) pos.getY(), (width / 3), (height / 3));
        }

    }

    void drawMoves(Graphics2D g, int width, int height) {
        int offset = 10;
        for (int i = 1; i < this.size(); i++) {
            //CALCULATE POSITION IN PIXEL FOR MAIN GRID
            Point tmp;
            tmp = TicTacToe.CalculatePosX(i, width / 3, height / 3);
            double outery = tmp.getY();
            double outerx = tmp.getX();
            //DRAW MOVES IN SMALL GRID
            g.setStroke(new BasicStroke(1));
            this.get(i).drawMoves(g, tmp, width, height);

            //IF FIELD IS FINISHED DRAW SIGN
            g.setStroke(new BasicStroke(3));
            if ("X".equals(this.get(i).isFinished())) {
                g.drawLine((int) (outerx) + offset, (int) outery + offset, (int) outerx + width / 3 - offset, (int) outery + height / 3 - offset);
                g.drawLine((int) outerx + offset, (int) outery + height / 3 - offset, (int) outerx + width / 3 - offset, (int) outery + offset);
            } else {
                if ("O".equals(this.get(i).isFinished())) {
                    g.drawOval((int) outerx + offset, (int) outery + offset, width / 3 - offset * 2, height / 3 - offset * 2);
                }
            }

        }
    }
    
    public Boolean isFull(){
        int count = 0;
        for (int i=1;i<=9;i++){
            if (("X".equals(this.get(i).isFinished()))||("O".equals(this.get(i).isFinished()))||(this.get(i).isFull())){
                count++;
            }
        }
        return count==9;
    }

    void drawAvailable(Graphics2D g, int width, int height) {
        //SET BACKGROUND COLOR
        if ("X".equals(sign)) {
            g.setColor(DrawPanel.green);
        } else {
            if ("O".equals(sign)) {
                g.setColor(DrawPanel.red);
            }
        }

        if (lastMove != -1) {
            Point tmp = TicTacToe.CalculatePosX(lastMove, width / 3, height / 3);

            if ((String.valueOf(lastMove).equals(this.get(lastMove).isFinished()))&&(!this.get(lastMove).isFull())) {
                //ONLY ONE OPTION
                this.get(lastMove).drawBackground(g, tmp, width / 3, height / 3);
            } else {
                //FREE CHOICE
                for (int i = 1; i < this.size(); i++) {
                    if (String.valueOf(i).equals(this.get(i).isFinished())) {
                        this.get(i).drawBackground(g, TicTacToe.CalculatePosX(i, width / 3, height / 3), width / 3, height / 3);
                    }
                }
            }
        } 
        
        //DRAW BACKGOUND GRAY IF FIELD IS FULL
        for (int i = 1; i < this.size(); i++) {
            if (this.get(i).isFull()) {
                g.setColor(Color.gray);
                this.get(i).drawBackground(g, TicTacToe.CalculatePosX(i, width / 3, height / 3), width / 3, height / 3);
            }
        }
    }

}
