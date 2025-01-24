package com.example.progarkex1.tasks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import com.example.progarkex1.classes.Helicopter;

import java.util.ArrayList;
import java.util.List;

public class Task3 extends Task1 {
    private final List<Helicopter> helicopters = new ArrayList<>();
    private int aniTick;

    public Task3(Context context) {
        super(context);

        int numHelicopters = 3;
        for (int i = 0; i < numHelicopters; i++) {
            helicopters.add(new Helicopter());
        }
    }

    @Override
    public void render() {
        Canvas can = surHolder.lockCanvas();
        if (can != null) {
            can.drawColor(Color.BLACK);
            for (Helicopter heli : helicopters) {
                can.drawBitmap(heli.getSprite(), heli.getX(), heli.getY(), null);
            }

            surHolder.unlockCanvasAndPost(can);
        }
    }

    @Override
    public void update(double delta) {
        for (Helicopter heli : helicopters) {
            heli.move(delta);
        }
        updateAnimation(delta);
        handleCollisions();
    }

    private void updateAnimation(double delta) {
        aniTick += (int) (delta * 1000);
        int aniSpeed = 100;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            for (Helicopter heli : helicopters) {
                heli.updateAnimationIndex();
            }
        }
    }

    private void handleCollisions() {
        for (int i = 0; i < helicopters.size(); i++) {
            Helicopter heliA = helicopters.get(i);

            heliA.checkWallCollision(getWidth(), getHeight());

            for (int j = i + 1; j < helicopters.size(); j++) {
                Helicopter heliB = helicopters.get(j);
                if (heliA.isCollidingWith(heliB)) {
                    heliA.bounceOff(heliB);
                }
            }
        }
    }

}
