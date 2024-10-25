package com.tutorial.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Explosion extends GameObject {
    private Handler handler;
    public static BufferedImage[] explosionImages; // Array de imagens da animação de explosão
    private int currentFrame = 0; // Frame atual da explosão
    private int animationSpeed = 5; // Velocidade de animação (ajuste conforme necessário)
    private int frameCounter = 0; // Contador para trocar de frame

    public Explosion(int x, int y, Handler handler) {
        super(x, y, Id.Explosion);
        explosionImages = explosionImages;
        this.handler = handler;
    }

    public static void loadExplosionImages() throws IOException {
        try {
            explosionImages = new BufferedImage[24];
            explosionImages[0] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_00_delay-0.05s.png"));
            explosionImages[1] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_01_delay-0.05s.png"));
            explosionImages[2] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_02_delay-0.05s.png"));
            explosionImages[3] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_03_delay-0.05s.png"));
            explosionImages[4] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_04_delay-0.05s.png"));
            explosionImages[5] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_05_delay-0.05s.png"));
            explosionImages[6] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_06_delay-0.05s.png"));
            explosionImages[7] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_07_delay-0.05s.png"));
            explosionImages[8] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_08_delay-0.05s.png"));
            explosionImages[9] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_09_delay-0.05s.png"));
            explosionImages[10] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_10_delay-0.05s.png"));
            explosionImages[11] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_11_delay-0.05s.png"));
            explosionImages[12] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_12_delay-0.05s.png"));
            explosionImages[13] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_13_delay-0.05s.png"));
            explosionImages[14] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_14_delay-0.05s.png"));
            explosionImages[15] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_15_delay-0.05s.png"));
            explosionImages[16] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_16_delay-0.05s.png"));
            explosionImages[17] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_17_delay-0.05s.png"));
            explosionImages[18] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_18_delay-0.05s.png"));
            explosionImages[19] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_19_delay-0.05s.png"));
            explosionImages[20] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_20_delay-0.05s.png"));
            explosionImages[21] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_21_delay-0.05s.png"));
            explosionImages[22] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_22_delay-0.05s.png"));
            explosionImages[23] = ImageIO.read(Explosion.class.getResource("/images/explosion/frame_23_delay-0.55s.png"));
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds() {
        return null;
    }

    public void tick() {
        frameCounter++;
        if (frameCounter >= animationSpeed) {
            frameCounter = 0; // Reseta o contador
            currentFrame++; // Vai para o próximo frame
            if (currentFrame >= explosionImages.length) {
                handler.removeObject(this);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (currentFrame < explosionImages.length) {
            g.drawImage(explosionImages[currentFrame], x, y, null); // Desenha o frame atual da explosão
        }
    }
}
