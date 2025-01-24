package com.example.progarkex1.classes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {
    private float x, y;
    private float radius = 20;
    private float speedX = 8;
    private float speedY = 8;
    private final int screenWidth, screenHeight;

    public Ball(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        reset();
    }

    public void reset() {
        x = screenWidth / 2f;
        y = screenHeight / 2f;
        speedX = (Math.random() > 0.5 ? 1 : -1) * 8;
        speedY = (Math.random() > 0.5 ? 1 : -1) * 8;
    }

    public void update() {
        x += speedX;
        y += speedY;

        if (y - radius < 0 || y + radius > screenHeight) {
            speedY = -speedY;
        }
    }

    public boolean checkCollision(Paddle paddle) {
        return x - radius < paddle.getX() + paddle.getWidth() &&
                x + radius > paddle.getX() &&
                y + radius > paddle.getY() &&
                y - radius < paddle.getY() + paddle.getHeight();
    }

    public void reverseX() {
        speedX = -speedX;
    }

    public boolean isOutLeft() {
        return x - radius < 0;
    }

    public boolean isOutRight() {
        return x + radius > screenWidth;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, radius, paint);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
