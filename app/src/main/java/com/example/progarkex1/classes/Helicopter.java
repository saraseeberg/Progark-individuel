package com.example.progarkex1.classes;

import android.graphics.Bitmap;

import com.example.progarkex1.entities.Vehicles;

public class Helicopter {

    private final Vehicles vehicle;
    private float x;
    private float y;
    private final int width = 162;
    private final int height = 65;
    private int direction;

    public Helicopter() {
        this.vehicle = Vehicles.HELICOPTER;
        this.x = 0;
        this.y = 0;
        direction = 1;
    }

    public Bitmap getSprite() {
        return vehicle.getSprite(direction, 0);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y, int screenWidth, int screenHeight) {
        this.x = Math.max(0, Math.min(x, screenWidth - width));
        this.y = Math.max(0, Math.min(y, screenHeight - height));
    }
}

