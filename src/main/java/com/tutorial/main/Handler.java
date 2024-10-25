package com.tutorial.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
    for (int i = 0; i < object.size(); i++) {
        GameObject  tempObject = object.get(i);
        tempObject.tick();
        }
    }
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            GameObject  tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public int getEnemyCount() {
        int count = 0;
        for (GameObject obj : object) {
            if (obj.getId() == Id.Enemy) {
                count++;
            }
        }
        return count;
    }
    public void clearAllObjects() {
        object.clear();
    }

    public Player getPlayer() {
        for (GameObject tempObject : object) {
            if (tempObject.getId() == Id.Player) {
                return (Player) tempObject;
            }
        }
        return null;
    }
}

