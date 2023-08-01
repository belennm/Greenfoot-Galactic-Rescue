import lang.stride.*;
import java.util.*;
import java.awt.Font;
import greenfoot.*;

public class Game extends World {
    private Rocket_ rocket;
    private Loki loki;
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
        } else {
            spawnRedBalls();
            moveScar();
            checkCollision();
        }
    }
    
    public void displayInstructions() {
        String title = "Galactic Rescue";
        // Mostrar las intrucciones en pantalla
        showText("\n\nSave all the Groots!\n There is a dangerous red ball of polution \ncaused by Scarlett Witch. \n The Groots are in danger. \n Avoid getting near Loki. \nIf you touch him, you'll have to restart. \n Good luck in your mission", getWidth() / 2, getHeight() / 2);
        showText(title, getWidth() / 2, getHeight() / 2 - 100); // Display el title
 
        Greenfoot.delay(500); // Delay para mostrar las instrucciones
        
        showText("", getWidth() / 2, getHeight() / 2 - 100); // Limpiar title
        showText("", getWidth() / 2, getHeight() / 2); // limpiar el texto de las instrucciones
    }
    
    public void spawnRedBalls() {
        if (Greenfoot.getRandomNumber(100) < 2) {
            addObject(new RedBall(), Greenfoot.getRandomNumber(getWidth()), 0);
        }
    }

    public void moveScar() {
        List<Scar> scars = getObjects(Scar.class);
        if (scars.isEmpty()) {
            addObject(new Scar(this), getWidth() / 2, getHeight() - 50);
        }
    }

    public void checkCollision() {
        List<RedBall> redBalls = getObjects(RedBall.class);

        for (RedBall redBall : redBalls) {
            if (isColliding(redBall, rocket)) {
                redBall.setDamage(0);
                rocket.decreaseHealth();

                if (rocket.getHealth() <= 0) {
                    showGameOver();
                    Greenfoot.setWorld(new Game());
                }
            }
        }
    }

    private boolean isColliding(Actor actor1, Actor actor2) {
        int width1 = actor1.getImage().getWidth();
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
        loki = new Loki(rocket);
        addObject(rocket, getWidth() / 2, getHeight() / 2);
        addObject(loki, getWidth() / 2, 50);
        Scar scar = new Scar(this);
        addObject(scar, 481, 232);
        groot groot = new groot();
        addObject(groot, 131, 486);
        groot groot2 = new groot();
        addObject(groot2, 178, 240);
        groot groot3 = new groot();
        addObject(groot3, 551, 82);
    }
}
