package com.example.progarkex1.classes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle {
    private float x, y;
    private final float width = 20;
    private final float height = 200;
    private final float screenHeight;

    public Paddle(float x, float screenHeight) {
        this.x = x;
        this.y = screenHeight / 2 - height / 2;
        this.screenHeight = screenHeight;
    }

    public void move(float dy) {
        y += dy;

        if (y < 0) y = 0;
        if (y + height > screenHeight) y = screenHeight - height;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
