package com.example.progarkex1;

import com.example.progarkex1.tasks.Task1;

public class GameLoop implements Runnable{

    private Thread gameThread;
    private Task1 gamePanel;

    public GameLoop(Task1 gamePanel) {
        this.gamePanel = gamePanel;
        gameThread = new Thread(this);
    }

    @Override
    public void run() {
        long lastDelta = System.nanoTime();
        long nanoSec = 1_000_000_000;
        while (true) {
            long nowDelta = System.nanoTime();
            double timeSinceLastDelta = nowDelta - lastDelta;
            double delta = timeSinceLastDelta / nanoSec;

            gamePanel.update(delta);
            gamePanel.render();
            lastDelta = nowDelta;
        }
    }

    public void startGameLoop() {
        gameThread.start();
    }
}
