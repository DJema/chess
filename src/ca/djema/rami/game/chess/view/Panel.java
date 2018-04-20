package ca.djema.rami.game.chess.view;

import javax.swing.JButton;

public class Panel extends JButton {

    private int xCoordinate;
    private int yCoordinate;
    
    public Panel() {
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                // Add action
      
            }
        });
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    
    
}
