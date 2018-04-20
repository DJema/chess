package ca.djema.rami.game.chess.application;

import ca.djema.rami.game.chess.view.ChessGameScreen;

public class ChessGameApplication {

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChessGameScreen cgs = new ChessGameScreen();
                cgs.setVisible(true);
            }
        });
    }
    
}
