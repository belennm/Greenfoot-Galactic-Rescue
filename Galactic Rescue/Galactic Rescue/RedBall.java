import greenfoot.*;

public class RedBall extends Actor {
    private boolean shouldRemove = false;

    public void act() {
        
        checkCollision();
        moveDown();

        if (shouldRemove) {
            removeSelf();
        }
    }

    public void checkCollision() {
        
        Game game = (Game) getWorld();
        Rocket_ rocket = game.getObjects(Rocket_.class).get(0);

        if (isTouching(Rocket_.class)) {
            rocket.decreaseHealth();
            setDamage(0); // remover red ball

            if (rocket.getHealth() <= 0) {
                game.showGameOver();
            }
        }
    }

    public void setDamage(int damage) {

        shouldRemove = true; // marcar red ball para removerla
    }

    private void removeSelf() {
        Game game = (Game) getWorld();
        game.removeObject(this);
    }

    public void moveDown() {
        setLocation(getX(), getY() + 2); // mover para abajo 2 pixels
        if (getY() >= getWorld().getHeight() - 1) {
            // si red ball alcanza la parte de abajo del mundo se marcará para removerla
            shouldRemove = true;
        }
    }
}