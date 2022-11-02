package Game_2048.controller;


import Game_2048.model.Game_2048;


public class GameController implements IGameController{

    private Game_2048 game;

    private IGameView view;

    private GameState state;


    public  GameController(IGameView view, int width, int height){

        this.view = view;
        this.game = new Game_2048(width, height);
        this.state = GameState.Game;




    }


    @Override
    public void nextFrame() {
        switch (state){
            case Game -> {
                view.drawGame();

            }
            case GAME_OVER -> {
                view.drawGG;
            }
        }

    }

    @Override
    public void userInput(String input) {

        switch (state){

            case GAME -> {
                // Hier soll dann der Input des Users bearbeitet werden

            }

        }

    }
}
