package com.example.progarkex1.classes;

import android.graphics.Bitmap;

import com.example.progarkex1.entities.Vehicles;

import java.util.Random;

public class Helicopter {

    private final Vehicles vehicle;
    private float x;
    private float y;
    private float speedX = 5;
    private float speedY = 5;
    private final int width = 162;
    private final int height = 65;
    private int direction;
    private int aniIndex = 0;

    private static final Random random = new Random();

    public Helicopter() {
        this.vehicle = Vehicles.HELICOPTER;
        this.x = random.nextInt(1080-width);
        this.y = random.nextInt(2400-height);
        this.direction = speedX > 0 ? 1 : 0;
    }


    public Bitmap getSpriteSimple() {
        return vehicle.getSprite(direction, 0);
    }

    public Bitmap getSprite() {
        return vehicle.getSprite(direction, aniIndex);
    }


    public void move(double delta) {
        x += (float) (speedX * delta * 60);
        y += (float) (speedY * delta * 60);
    }

    public void updateAnimationIndex() {
        aniIndex++;
        if (aniIndex >= 4) {
            aniIndex = 0;
        }
    }

    public void checkWallCollision(int screenWidth, int screenHeight) {
        if (x < 0) {
            speedX = -speedX;
            x *= -1;
            direction = speedX > 0 ? 1 : 0;
        }
        if (x > screenWidth - width) {
            speedX = -speedX;
            float xDiff = Math.abs(screenWidth - x - width);
            x -= xDiff * 2;
            direction = speedX > 0 ? 1 : 0;
        }
        if (y < 0) {
            speedY = -speedY;
            y *= -1;
        }
        if (y > screenHeight - height) {
            speedY = -speedY;
            float yDiff = Math.abs(screenHeight - y - height);
            y -= yDiff * 2;
        }
    }

    public boolean isCollidingWith(Helicopter other) {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y;
    }

    public void bounceOff(Helicopter other) {
        float dx = (other.x + other.width / 2f) - (this.x + this.width / 2f);
        float dy = (other.y + other.height / 2f) - (this.y + this.height / 2f);

        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) return;

        dx /= distance;
        dy /= distance;

        float overlap = (width + other.width) / 2f - distance;
        this.x -= dx * overlap / 2f;
        this.y -= dy * overlap / 2f;
        other.x += dx * overlap / 2f;
        other.y += dy * overlap / 2f;

        float thisSpeedMagnitude = (float) Math.sqrt(this.speedX * this.speedX + this.speedY * this.speedY);
        float otherSpeedMagnitude = (float) Math.sqrt(other.speedX * other.speedX + other.speedY * other.speedY);

        this.speedX = -dx * thisSpeedMagnitude;
        this.speedY = -dy * thisSpeedMagnitude;

        other.speedX = dx * otherSpeedMagnitude;
        other.speedY = dy * otherSpeedMagnitude;

        this.direction = this.speedX > 0 ? 1 : 0;
        other.direction = other.speedX > 0 ? 1 : 0;
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

