package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    private long lastShotTime;
    private final long shotCooldown = 500;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        int playerWidth = 91; // Largura do player
        int playerHeight = 97; // Altura do player

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == Id.Player) {
                int speed = 5;
                if (key == KeyEvent.VK_W) tempObject.setVelY(-speed);
                if (key == KeyEvent.VK_S) tempObject.setVelY(speed);
                if (key == KeyEvent.VK_A) tempObject.setVelX(-speed);
                if (key == KeyEvent.VK_D) tempObject.setVelX(speed);

                long currentTime = System.currentTimeMillis();
                if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
                        && (currentTime - lastShotTime) >= shotCooldown) {

                    int projectileX = tempObject.getX() + (playerWidth / 2);
                    int projectileY = tempObject.getY() + (playerHeight / 2);

                    if (key == KeyEvent.VK_UP) {
                        handler.addObject(new Projectile(projectileX, projectileY, Id.Projectile, 0, -10, handler));
                    }
                    if (key == KeyEvent.VK_DOWN) {
                        handler.addObject(new Projectile(projectileX, projectileY, Id.Projectile, 0, 10, handler));
                    }
                    if (key == KeyEvent.VK_LEFT) {
                        handler.addObject(new Projectile(projectileX, projectileY, Id.Projectile, -10, 0, handler));
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        handler.addObject(new Projectile(projectileX, projectileY, Id.Projectile, 10, 0, handler));
                    }

                    lastShotTime = currentTime;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == Id.Player) {
                int speed = 0;
                if(key == KeyEvent.VK_W) tempObject.setVelY(speed);
                if (key == KeyEvent.VK_S) tempObject.setVelY(speed);
                if (key == KeyEvent.VK_A) tempObject.setVelX(speed);
                if (key == KeyEvent.VK_D) tempObject.setVelX(speed);
            }
        }
    }
}
