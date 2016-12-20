
package javaproject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class TicTacToe extends ArrayList<String> {

    private String pos; //REQUIRED FOR FUNCTION ISFINISHED();
    
    public TicTacToe(String pos) {
        this.pos=pos;
        for (int i = 0; i <= 9; i++) {
            this.add(String.valueOf(i));
        }
    }

    //CALCULATES POSITION IN PIXELS
    public static Point CalculatePosX(int pos, int width, int height) {
        Point point = new Point();
        
        int count =1;
        for (int i=0;i<=2;i++){
            for (int j=0;j<=2;j++){
                if (count==pos){
                    point.setLocation(j*width, i*height);
                    return point;
                }
                count++;
            }
        }
        
        return point;
    }

    @Override
    public String set(int index, String element) {
        if ((this.get(index).equals("X"))||(this.get(index).equals("O"))){
            return "";
        }
        else{
            return super.set(index, element);
        }
    }
    
    
    //RETURNS POSITION IF FIELD IS NOT FINISHED
    //IF FINISHED IT RETURNS X OR O
    public String isFinished() {

        if ((this.get(1).equals(this.get(5)))&&(this.get(1).equals(this.get(9)))){
            return this.get(1);
        }
        //VERTICAL CHECK
        for (int i = 1; i <= 3; i++) {
            if ((this.get(i).equals(this.get(i+3)))&&(this.get(i).equals(this.get(i+6)))){
                return this.get(i);
            }
        }
        //HORIZONTAL CHECK
        for (int i = 1; i <= 7; i=i+3) {
            if ((this.get(i).equals(this.get(i+1)))&&(this.get(i).equals(this.get(i+2)))){
                return this.get(i);
            }
        }

        if ((this.get(3).equals(this.get(5)))&&(this.get(3).equals(this.get(7)))){
            return this.get(3);
        }
        return pos;

    }
    
    public Boolean isFull(){
        int count = 0;
        for (int i=1;i<=9;i++){
            if (("X".equals(this.get(i)))||("O".equals(this.get(i)))){
                count++;
            }
        }
        return count==9;
    }

    public void drawGrid(Graphics2D g, int x, int y, int width, int height) {
        int offset = 10;
        // DRAW SMALL GRID
        //VERTICAL
        g.drawLine((x + width / 3), y + offset, (x + width / 3), (y + height) - offset);
        g.drawLine(x + (width / 3) * 2, y + offset, x + (width / 3) * 2, y + height - offset);
        // HORIZONTAL
        g.drawLine(x + offset, y + (height / 3), x + width - offset, y + (height / 3));
        g.drawLine(x + offset, y + (height / 3) * 2, x + width - offset, y + (height / 3) * 2);

    }
    

    public void drawMoves(Graphics g, Point posX, int width, int height) {
        int offset = 10;
        for (int i = 1; i < this.size(); i++) {
            //CALCULATE POSITION IN PIXEL FOR SMALL GRID
            Point tmp = CalculatePosX(i, width / 9, height / 9);
            double innery = tmp.getY();
            double innerx = tmp.getX();
            
            //DRAW MOVES IN SMALL GRID
            if ("X".equals(this.get(i))) {
                //DRAWING X
                g.drawLine((int) (posX.getX() + innerx) + offset, (int) (posX.getY() + innery) + offset, (int) (posX.getX() + innerx) + width / 9 - offset, (int) (posX.getY() + innery) + height / 9 - offset);
                g.drawLine((int) (posX.getX() + innerx) + offset, (int) (posX.getY() + innery) + height / 9 - offset, (int) (posX.getX() + innerx) + width / 9 - offset, (int) (posX.getY() + innery) + offset);
            } else {
                //DRAWING O
                if ("O".equals(this.get(i))) {
                    g.drawOval((int) (posX.getX() + innerx) + offset, (int) (posX.getY() + innery) + offset, width / 9 - offset * 2, height / 9 - offset * 2);
                }
            }
        }
    }
    
    public void drawBackground(Graphics g,Point point,int width,int height){        
        g.fillRect((int)point.getX(),(int) point.getY(), width, height);
    }
}
