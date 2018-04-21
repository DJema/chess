package ca.djema.rami.game.chess.application;

import java.io.IOException;

import ca.djema.rami.game.chess.view.ChessGameScreen;

public class ChessGameApplication {

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChessGameScreen cgs;
                try {
                    cgs = new ChessGameScreen();
                    cgs.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    
}
