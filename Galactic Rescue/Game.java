import lang.stride.*;
import java.util.*;
import greenfoot.*;

public class Game extends World {
    private Rocket_ rocket;
    private boolean instructionsDisplayed;
    
    public Game() {
        super(600, 600, 1);
        instructionsDisplayed = false; // Display para las instrucciones
        prepare();
    }

    public void act() {
        if (!instructionsDisplayed) {
            displayInstructions();
            instructionsDisplayed = true;
        }
    }
    
    public void displayInstructions() {
        String title = "Galactic Rescue";
        // Mostrar las instrucciones en la pantalla
        showText("Instructions:\n Save all the Groots!\n There is a dangerous red ball rain caused by polution. \n The Groots are in danger.\n Avoid getting near the rain or it will damage you. \nIf you get sick, you'll have to restart.", getWidth() / 2, getHeight() / 2);
        showText(title, getWidth() / 2, getHeight() / 2 - 100); // Display el title
        
        Greenfoot.delay(500); // Delay para mostrar las instrucciones
        
        showText("", getWidth() / 2, getHeight() / 2 - 100); // Limpiar title
        showText("", getWidth() / 2, getHeight() / 2); // limpiar el texto de las instrucciones
    }
    


    private boolean isColliding(Actor actor1, Actor actor2) {
        int width1 = actor1.getImage().getWidth(); // cuando rocket se posiciona sobre groot
        int height1 = actor1.getImage().getHeight();
        int width2 = actor2.getImage().getWidth();
        int height2 = actor2.getImage().getHeight();

        int x1 = actor1.getX() - width1 / 2;
        int y1 = actor1.getY() - height1 / 2;
        int x2 = actor2.getX() - width2 / 2;
        int y2 = actor2.getY() - height2 / 2;

        return x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2;
    }

    public void showGameOver() {
        showText("Game Over", getWidth() / 2, getHeight() / 2);
        Greenfoot.delay(20); // Delay para mostrar el mensaje "Game Over"
        Greenfoot.setWorld(new Game());
    }
    
    public void died(){
        if (rocket.getHealth() <= 0) {
            Greenfoot.setWorld(new Game()); 
        }
    }
    
    private void prepare() {
        rocket = new Rocket_(); // características del personaje
        addObject(rocket, getWidth() / 2, getHeight() / 2);
        groot groot = new groot();
        addObject(groot, 131, 486);
        groot groot2 = new groot();
        addObject(groot2, 178, 240);
        groot groot3 = new groot();
        addObject(groot3, 551, 82);
    }
}
