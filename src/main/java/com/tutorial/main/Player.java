package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
    Random r = new Random();
    private Handler handler;

    public Player(int x, int y, Id id, Handler handler){
        super(x,y,id);
        this.handler = handler;
        loadImage("/images/player/player.png");
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x < 0) {
            x = 0; // Limite esquerdo
        } else if (x > Game.WIDTH - 91) {
            x = Game.WIDTH - 91; // Limite direito
        }

        if (y < 0) {
            y = 0;
        } else if (y > Game.HEIGHT - 97) {
            y = Game.HEIGHT - 97; // Limite inferior
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == Id.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    Game.gameState = Game.GameState.GAME_OVER;
                }
            }
        }

    }

    public void render(Graphics g) {
        super.render(g);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }
}
