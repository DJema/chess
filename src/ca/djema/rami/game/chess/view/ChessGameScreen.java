package ca.djema.rami.game.chess.view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGameScreen extends JFrame {

    private ArrayList<ArrayList<Panel>> panels = new ArrayList<ArrayList<Panel>>();
    
    
    public ChessGameScreen() {
        initComponents();
        refreshData();
    }

    private void initComponents() {

        setTitle("Chess Game");
        JPanel jp = new JPanel();
        setSize(1000, 1000);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                jp.add(new Panel());
            }  
        }
        add(jp);
        setVisible(true);

    }

    private void refreshData() {

    }
    
}
