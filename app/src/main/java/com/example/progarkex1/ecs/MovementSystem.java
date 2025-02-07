package com.example.progarkex1.ecs;

import java.util.List;

public class MovementSystem {
    private final List<Entity> entities;
    private final int screenWidth;
    private final int screenHeight;

    public MovementSystem(List<Entity> entities, int screenWidth, int screenHeight) {
        this.entities = entities;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void update() {
        for (Entity entity : entities) {
            PositionComponent position = entity.getComponent(PositionComponent.class);
            MovementComponent movement = entity.getComponent(MovementComponent.class);
            TargetComponent target = entity.getComponent(TargetComponent.class);

            if (position != null && movement != null && target != null) {
                float dx = target.x - position.x;
                float dy = target.y - position.y;

                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                float speed = movement.speed;

                if (distance > speed) {
                    position.x += (dx / distance) * speed;
                    position.y += (dy / distance) * speed;
                } else {
                    position.x = target.x;
                    position.y = target.y;
                }

                if (position.x < 0) {
                    position.x = 0;
                }
                if (position.x > screenWidth - movement.entityWidth) {
                    position.x = screenWidth - movement.entityWidth;
                }
                if (position.y < 0) {
                    position.y = 0;
                }
                if (position.y > screenHeight - movement.entityHeight) {
                    position.y = screenHeight - movement.entityHeight;
                }
            }
        }


    }
}
