
package javaproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class DrawPanel extends JPanel {
    static Color green = new Color(10, 207, 0);
    static Color red = new Color(255, 13, 0);
    private UltimateTicTacToe mainGrid = new UltimateTicTacToe();
    private Graphics2D g;
    

    public DrawPanel() {
        super();
    }
       

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        g = (Graphics2D) g0;
        
        //DRAWING AVAILABLE MOVES
        //TEST IF THE GAME IS NOT FINISHED
        if (this.isEnabled()) {
            //DRAW NEXT AVAILABLE MOVE
            mainGrid.drawAvailable(g, this.getWidth(), this.getHeight());
        }else{
            //NOT ABLE TO PLAY
            g.setColor(Color.gray);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        
        
        // DRAW GRIDS
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        mainGrid.drawGrid(g, this.getWidth(), this.getHeight());
        
        
        //DRAW MOVES FROM PLAYERS
        mainGrid.drawMoves(g, this.getWidth(), this.getHeight());

    }

    public boolean makeMove(int x, int y, String sign) {
        //SAVES THE MOVE
        boolean validmove=mainGrid.makeMove(x, y, sign);
        //REPAINT DRAWPANEL
        if(validmove)
            this.repaint();
        return validmove;
    }
    public int isFinished(){
        if ((!"".equals(mainGrid.isFinished())) && (mainGrid.isFinished() != null)) {
            if (mainGrid.isFinished().equals("X")) {
                return 1;
            } else {
                if (mainGrid.isFinished().equals("O")) {
                    return 2;
                }

            }
        } else {
            if (mainGrid.isFull()) {
                return 3;
            }
        }
        return 0;
    }
}
