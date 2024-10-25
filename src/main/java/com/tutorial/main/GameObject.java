package com.tutorial.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class GameObject {
    protected  int x, y;
    protected Id id;
    protected int velX, velY;
    private BufferedImage image;

    public GameObject(int x, int y, Id id) {
        this.x = x;
        this.y = y;
        this.id = id;

    }

    public abstract Rectangle getBounds();

    public void loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void tick();
    public void render(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, null);
        }
    }


    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }
}
