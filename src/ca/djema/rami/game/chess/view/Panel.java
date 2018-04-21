package ca.djema.rami.game.chess.view;

import javax.swing.JButton;

public class Panel extends JButton {

    private int xCoordinate;
    private int yCoordinate;
    private boolean isDarkPanel;
    
    public Panel() {
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                // Add action
      
            }
        });
    }
    
    public Panel(int xCoordinate, int yCoordinate, Boolean isDarkPanel) {
        
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isDarkPanel = isDarkPanel;
        
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

    public boolean isDarkPanel() {
        return isDarkPanel;
    }

    public void setDarkPanel(boolean isDarkPanel) {
        this.isDarkPanel = isDarkPanel;
    }
    
    
}
