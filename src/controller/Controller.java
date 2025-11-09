package controller;

import irrgarten.Directions;
import irrgarten.Game;
import UI.TextUI;


public class Controller {
    
    private Game game;
    private TextUI view;
    
    public Controller(Game game, TextUI view) {
        this.game = game;
        this.view = view;
    }
    
    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState()); 
            Directions direction = view.nextMove(); 
            endOfGame = game.nextStep(direction);
        }
        view.showGame(game.getGameState());
        System.out.println("El jugador " + game.getGameState().getCurrentPlayer() + " ha ganado la partida.");
    }
    
}
