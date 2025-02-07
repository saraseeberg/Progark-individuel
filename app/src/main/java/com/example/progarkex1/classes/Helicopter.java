package com.example.progarkex1.classes;

import android.graphics.Bitmap;

import com.example.progarkex1.ecs.TargetComponent;
import com.example.progarkex1.entities.Vehicles;
import com.example.progarkex1.ecs.Entity;
import com.example.progarkex1.ecs.PositionComponent;
import com.example.progarkex1.ecs.MovementComponent;

public class Helicopter extends Entity {

    private final Vehicles vehicle;
    private final int width = 162;
    private final int height = 65;
    private final int speed = 10;

    public Helicopter() {
        this.vehicle = Vehicles.HELICOPTER;

        addComponent(new PositionComponent(0, 0));
        addComponent(new MovementComponent(speed, width, height));
        addComponent(new TargetComponent(0, 0));
    }

    public Bitmap getSprite() {
        return vehicle.getSprite(1, 0);
    }

    public PositionComponent getPosition() {
        return getComponent(PositionComponent.class);
    }

    public TargetComponent getTarget() {
        return getComponent(TargetComponent.class);
    }
}

