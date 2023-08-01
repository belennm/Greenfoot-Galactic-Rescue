import greenfoot.*;

public class Scar extends Actor {
    private int speed = 2; // Velocidad de movimiento de Scar
    private World world;
    
    public Scar(World world) {
        this.world = world;
    }
    
    public void act() {
        followRocket();
        moveUpDown();
    }
    
    public void followRocket() {
        // 
        Game game = (Game) getWorld();
        Rocket_ rocket = game.getObjects(Rocket_.class).get(0);
        
        turnTowards(rocket.getX(), rocket.getY());
    }
    
    public void moveUpDown() {
        // Comprobar colisión con Rocket
        Game game = (Game) getWorld();
        Rocket_ rocket = game.getObjects(Rocket_.class).get(0);
        
        if (isTouching(Rocket_.class)) {
            rocket.decreaseHealth();
            
            if (rocket.getHealth() <= 0) {
                game.showGameOver(); // llamar showGameOver() de  Game class
                Greenfoot.setWorld(new Game());
                return; // exit 
            }
        }
        
        setLocation(getX(), getY() + speed); // mover a lo largo del eje Y
        
        // invertir dirección si se alcanza el límite superior o inferior del mundo
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            speed *= -1; // se invierte
        }
    }


    
    private void showGameOver() {
        world.showText("Game Over", world.getWidth() / 2, world.getHeight() / 2);
        Greenfoot.delay(50); // delay para mostrar "Game Over" message
    }
}
