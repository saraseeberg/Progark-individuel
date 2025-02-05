package com.example.progarkex1;

import com.example.progarkex1.tasks.GamePanel;

public class GameLoop implements Runnable{

    private static GameLoop instance;
    private Thread gameThread;

    public GameLoop() {
        gameThread = new Thread(this);
    }

    public static synchronized GameLoop getInstance() {
        if (instance == null) {
            instance = new GameLoop();
        }
        return instance;
    }


    @Override
    public void run() {

        while (true) {
            long startTime = System.currentTimeMillis();

            GamePanel.getInstance().update();
            GamePanel.getInstance().render();

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
        if (!gameThread.isAlive()) {
            gameThread.start();
        }
    }
}
