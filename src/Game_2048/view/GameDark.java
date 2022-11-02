package Game_2048.view;

import Game_2048.controller.GameController;
import Game_2048.controller.IGameController;
import Game_2048.controller.IGameView;
import processing.core.PApplet;





public class GameDark extends PApplet implements IGameView{
    public static void main(String[] args) {PApplet.main(GameDark.class);}


        IController controller;


    public GameDark(){setSize(1000, 1000);}


    public void setup() {

    this.controller = new GameController(this, width, height);

    }

    public void draw(){controller.nextFrame();}


    // Kästchen Hardcoden
    public void drawGame(){



    }

    public void drawGG() {
        background(0);
        textSize(64);
        text("GG YOU WON!", 350, 500);
    }


}
