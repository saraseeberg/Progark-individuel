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

        while (true) {
            long startTime = System.currentTimeMillis();

            gamePanel.update();
            gamePanel.render();

            long deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime < 16) {
                try {
                    Thread.sleep(16 - deltaTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void startGameLoop() {
        gameThread.start();
    }
}
