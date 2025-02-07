package com.example.progarkex1.ecs;

public class MovementComponent {
    public float speed;
    public int entityWidth, entityHeight;

    public MovementComponent(float speed, int entityWidth, int entityHeight) {
        this.speed = speed;
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
    }
}
