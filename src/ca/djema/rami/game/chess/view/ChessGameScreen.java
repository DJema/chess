package ca.djema.rami.game.chess.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGameScreen extends JFrame {

    private ArrayList<ArrayList<Panel>> panels = new ArrayList<ArrayList<Panel>>();
    private JPanel currentBoard;
    
    public ChessGameScreen() throws IOException {
        initComponents();
        refreshData();
    }

    
    /**
     * @throws IOException
     * 
     * Initializes all of the UI pieces
     */
    private void initComponents() throws IOException {

        setTitle("Chess Game");
        setSize(800, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentBoard = new JPanel();
        currentBoard.setLayout(new GridLayout(8, 8));
        
        ArrayList<Panel> row0 = new ArrayList<Panel>();
        ArrayList<Panel> row1 = new ArrayList<Panel>();
        ArrayList<Panel> row2 = new ArrayList<Panel>();
        ArrayList<Panel> row3 = new ArrayList<Panel>();
        ArrayList<Panel> row4 = new ArrayList<Panel>();
        ArrayList<Panel> row5 = new ArrayList<Panel>();
        ArrayList<Panel> row6 = new ArrayList<Panel>();
        ArrayList<Panel> row7 = new ArrayList<Panel>();
        
        panels.add(row0);
        panels.add(row1);
        panels.add(row2);
        panels.add(row3);
        panels.add(row4);
        panels.add(row5);
        panels.add(row6);
        panels.add(row7);
        
        //Black Rook on Light Panel
        Panel p70 = new Panel(7,0,false);
        p70.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackRookLight.png"))));
        panels.get(7).add(p70);
        currentBoard.add(p70);
        //Black Knight on Dark Panel
        Panel p71 = new Panel(7,1,true);
        p71.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackKnightDark.png"))));
        panels.get(7).add(p71);
        currentBoard.add(p71);
        //Black Bishop on Light Panel
        Panel p72 = new Panel(7,2,false);
        p72.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackBishopLight.png"))));
        panels.get(7).add(p72);
        currentBoard.add(p72);
        //Black Queen on Dark Panel
        Panel p73 = new Panel(7,3,true);
        p73.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackQueenDark.png"))));
        panels.get(7).add(p73);
        currentBoard.add(p73);
        //Black King on Light Panel
        Panel p74 = new Panel(7,4,false);
        p74.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackKingLight.png"))));
        panels.get(7).add(p74);
        currentBoard.add(p74);
        //Black Bishop on Dark Panel
        Panel p75 = new Panel(7,5,true);
        p75.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackBishopDark.png"))));
        panels.get(7).add(p75);
        currentBoard.add(p75);
        //Black Knight on Light Panel
        Panel p76 = new Panel(7,6,false);
        p76.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackKnightLight.png"))));
        panels.get(7).add(p76);
        currentBoard.add(p76);
        //Black Rook on Dark Panel
        Panel p77 = new Panel(7,7,true);
        p77.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackRookDark.png"))));
        panels.get(7).add(p77);
        currentBoard.add(p77);
        
        
        //Black Pawn on Dark Panel
        Panel p60 = new Panel(6,0,true);
        p60.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnDark.png"))));
        panels.get(6).add(p60);
        currentBoard.add(p60);
        //Black Pawn on Light Panel
        Panel p61 = new Panel(6,1,false);
        p61.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnLight.png"))));
        panels.get(6).add(p61);
        currentBoard.add(p61);
        //Black Pawn on Dark Panel
        Panel p62 = new Panel(6,2,true);
        p62.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnDark.png"))));
        panels.get(6).add(p62);
        currentBoard.add(p62);
        //Black Pawn on Light Panel
        Panel p63 = new Panel(6,3,false);
        p63.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnLight.png"))));
        panels.get(6).add(p63);
        currentBoard.add(p63);
        //Black Pawn on Dark Panel
        Panel p64 = new Panel(6,4,true);
        p64.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnDark.png"))));
        panels.get(6).add(p64);
        currentBoard.add(p64);
        //Black Pawn on Light Panel
        Panel p65 = new Panel(6,5,false);
        p65.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnLight.png"))));
        panels.get(6).add(p65);
        currentBoard.add(p65);
        //Black Pawn on Dark Panel
        Panel p66 = new Panel(6,6,true);
        p66.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnDark.png"))));
        panels.get(6).add(p66);
        currentBoard.add(p66);
        //Black Pawn on Light Panel
        Panel p67 = new Panel(6,7,false);
        p67.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnLight.png"))));
        panels.get(6).add(p67);
        currentBoard.add(p67);
  
        
        // Add empty squares
        int counter = 1;
        for (int i = 5; i >= 2; i--) {
            for(int j = 0; j < 8; j++) {
                if(counter == 0) {
                    Panel p = new Panel(i,j,true);
                    p.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("DarkPanel.bmp"))));
                    panels.get(i).add(p);
                    currentBoard.add(p);
                    counter = 1;
                } else {
                    Panel p = new Panel(i,j,false);
                    p.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("LightPanel.bmp"))));
                    panels.get(i).add(p);
                    currentBoard.add(p);
                    counter = 0; 
                }
            }  
            if(counter == 0) {
                counter = 1;
            }else {
                counter = 0;
            }
        }
        
        
        //White Pawn on Light Panel
        Panel p10 = new Panel(1,0,false);
        p10.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnLight.png"))));
        panels.get(1).add(p10);
        currentBoard.add(p10);
        //White Pawn on Dark Panel
        Panel p11 = new Panel(1,1,true);
        p11.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnDark.png"))));
        panels.get(1).add(p11);
        currentBoard.add(p11);
        //White Pawn on Light Panel
        Panel p12 = new Panel(1,2,false);
        p12.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnLight.png"))));
        panels.get(1).add(p12);
        currentBoard.add(p12);
        //White Pawn on Dark Panel
        Panel p13 = new Panel(1,3,true);
        p13.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnDark.png"))));
        panels.get(1).add(p13);
        currentBoard.add(p13);
        //White Pawn on Light Panel
        Panel p14 = new Panel(1,4,false);
        p14.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnLight.png"))));
        panels.get(1).add(p14);
        currentBoard.add(p14);
        //White Pawn on Dark Panel
        Panel p15 = new Panel(1,5,true);
        p15.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnDark.png"))));
        panels.get(1).add(p15);
        currentBoard.add(p15);
        //White Pawn on Light Panel
        Panel p16 = new Panel(1,6,false);
        p16.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnLight.png"))));
        panels.get(1).add(p16);
        currentBoard.add(p16);
        //White Pawn on Dark Panel
        Panel p17 = new Panel(1,7,true);
        p17.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnDark.png"))));
        panels.get(1).add(p17);
        currentBoard.add(p17);
        
        
        //White Rook on Dark Panel
        Panel p00 = new Panel(0,0,false);
        p00.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteRookDark.png"))));
        panels.get(0).add(p00);
        currentBoard.add(p00);
        //White Knight on Light Panel
        Panel p01 = new Panel(0,1,true);
        p01.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteKnightLight.png"))));
        panels.get(0).add(p01);
        currentBoard.add(p01);
        //White Bishop on Dark Panel
        Panel p02 = new Panel(0,2,false);
        p02.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteBishopDark.png"))));
        panels.get(0).add(p02);
        currentBoard.add(p02);
        //White Queen on Light Panel
        Panel p03 = new Panel(0,3,true);
        p03.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteQueenLight.png"))));
        panels.get(0).add(p03);
        currentBoard.add(p03);
        //White King on Dark Panel
        Panel p04 = new Panel(0,4,false);
        p04.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteKingDark.png"))));
        panels.get(0).add(p04);
        currentBoard.add(p04);
        //White Bishop on Light Panel
        Panel p05 = new Panel(0,5,true);
        p05.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteBishopLight.png"))));
        panels.get(0).add(p05);
        currentBoard.add(p05);
        //White Knight on Dark Panel
        Panel p06 = new Panel(0,6,false);
        p06.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteKnightDark.png"))));
        panels.get(0).add(p06);
        currentBoard.add(p06);
        //White Rook on Light Panel
        Panel p07 = new Panel(0,7,true);
        p07.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhiteRookLight.png"))));
        panels.get(0).add(p07);
        currentBoard.add(p07);
        
        add(currentBoard);
        
        
        setVisible(true);
     
    }
    
    private void movePawn(int x1, int y1, int x2, int y2, String player) throws IOException {
        Panel origin = panels.get(x1).get(y1);
        Panel destination = panels.get(x2).get(y2);
        
        if(origin.isDarkPanel()) {
            panels.get(x1).get(y1).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("DarkPanel.bmp"))));
        } else {
            panels.get(x1).get(y1).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("LightPanel.bmp"))));
        }
        
        if(destination.isDarkPanel() && player.equals("white")) {
            panels.get(y2).get(y2).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnDark.png"))));
        } else if (destination.isDarkPanel() && !player.equals("white")) {
            panels.get(x2).get(y2).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnDark.png"))));
        } else if (!destination.isDarkPanel() && player.equals("white")) {
            panels.get(x2).get(y2).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("WhitePawnLight.png"))));
        } else {
            panels.get(x2).get(y2).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("BlackPawnLight.png"))));
        }
        
        refreshData();
        
        
    }

    private void refreshData() {
        
        remove(currentBoard);
        currentBoard = new JPanel();
        currentBoard.setLayout(new GridLayout(8, 8));
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j ++) {
                currentBoard.add(panels.get(i).get(j));
            }
        }
        add(currentBoard);    
    }
    
}
