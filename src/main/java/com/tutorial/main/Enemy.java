package com.tutorial.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends GameObject {
    private static BufferedImage[] enemyImages;
    public static BufferedImage[] explosionImages;
    private BufferedImage currentImage;

    public Enemy(int x, int y, Id id) {
        super(x, y, id);
        currentImage = selectRandomImage();
        this.velX = 2;
        this.velY = 2;
    }

    private BufferedImage selectRandomImage() {
        int randomIndex = (int) (Math.random() * enemyImages.length);
        return enemyImages[randomIndex];
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x < 0 || x > Game.WIDTH - 32) {
            velX *= -1;
        }
        if (y < 0 || y > Game.HEIGHT - 32) {
            velY *= -1;
        }
    }

    public void render(Graphics g) {
        g.drawImage(currentImage, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 100);
    }

    public static void loadImages() {
        enemyImages = new BufferedImage[4];
        try {
            enemyImages[0] = ImageIO.read(Enemy.class.getResource("/images/enemies/enemy1.png"));
            enemyImages[1] = ImageIO.read(Enemy.class.getResource("/images/enemies/enemy2.png"));
            enemyImages[2] = ImageIO.read(Enemy.class.getResource("/images/enemies/enemy3.png"));
            enemyImages[3] = ImageIO.read(Enemy.class.getResource("/images/enemies/enemy4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
