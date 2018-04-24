package ca.djema.rami.game.chess.controller;

import java.util.ArrayList;

public class Moves {

    private ArrayList<Integer> listOfValidMovesX;
    private ArrayList<Integer> listOfValidMovesY;
    
    public Moves() {
        this.listOfValidMovesX = new ArrayList<Integer>();
        this.listOfValidMovesY = new ArrayList<Integer>();
    }
    
    public ArrayList<Integer> getListOfValidMovesX() {
        return listOfValidMovesX;
    }
    public void setListOfValidMovesX(ArrayList<Integer> listOfValidMovesX) {
        this.listOfValidMovesX = listOfValidMovesX;
    }
    public ArrayList<Integer> getListOfValidMovesY() {
        return listOfValidMovesY;
    }
    public void setListOfValidMovesY(ArrayList<Integer> listOfValidMovesY) {
        this.listOfValidMovesY = listOfValidMovesY;
    }
    
}
