package com.tutorial.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Projectile extends GameObject {

    private Handler handler;
    private List<Point> trail;
    private static final int TRAIL_LENGTH = 20;
    private int MAX_ENEMIES = 20;

    public Projectile(int x, int y, Id id, int velX, int velY, Handler handler) {
        super(x, y, id);
        this.velX = velX;
        this.velY = velY;
        this.handler = handler;
        trail = new ArrayList<>();
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        trail.add(new Point(x, y));

        if (trail.size() > TRAIL_LENGTH) {
            trail.remove(0);
        }

        // Remove o projétil se sair da tela
        if (x < 0 || x > Game.WIDTH || y < 0 || y > Game.HEIGHT) {
            handler.removeObject(this);
        }

        // Verifica colisões com inimigos
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == Id.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject); // Remove o inimigo
                    handler.addObject(new Explosion(tempObject.getX(), tempObject.getY(), handler));
                    handler.removeObject(this); // Remove o projétil

                    Game.killCount++;

                    // Gera exatamente 2 novos inimigos em posições aleatórias
                    spawnNewEnemies(tempObject.getX(), tempObject.getY());
                    spawnNewEnemies(tempObject.getX(), tempObject.getY());
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < trail.size(); i++) {
            Point p = trail.get(i);
            int alpha = (int) ((i / (double) TRAIL_LENGTH) * 255); // Gradiente de transparência
            g.setColor(new Color(255, 255, 0, alpha)); // Amarelo com transparência
            g.fillOval(p.x, p.y, 10, 10);
        }

        g.setColor(Color.orange);
        g.fillOval(x, y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }

    private void spawnNewEnemies(int x, int y) {
        int screenWidth = Game.WIDTH;
        int screenHeight = Game.HEIGHT;

        for (int i = 0; i < 2; i++) {

            int randomX = (int) (Math.random() * screenWidth);
            int randomY = (int) (Math.random() * screenHeight);

            if (!handler.getPlayer().getBounds().intersects(new Rectangle(randomX, randomY, 100, 100))) {
                handler.addObject(new Enemy(randomX, randomY, Id.Enemy));
            }
        }
    }
}
