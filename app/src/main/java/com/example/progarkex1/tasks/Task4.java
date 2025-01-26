package com.example.progarkex1.tasks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.progarkex1.classes.Ball;
import com.example.progarkex1.classes.Paddle;

public class Task4 extends SurfaceView implements SurfaceHolder.Callback {
    private final Ball ball;
    private final Paddle paddleLeft;
    private final Paddle paddleRight;
    private int scoreLeft = 0, scoreRight = 0;
    private final int winningScore = 21;
    private boolean isRunning = false;
    private Thread gameThread;
    private boolean isGameOver = false;
    private String winningText = "";
    private final Paint paint = new Paint();
    private final Paint leftPaddlePaint = new Paint();
    private final Paint rightPaddlePaint = new Paint();

    public Task4(Context context) {
        super(context);
        getHolder().addCallback(this);

        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;

        ball = new Ball(screenWidth, screenHeight);
        paddleLeft = new Paddle(50, screenHeight);
        paddleRight = new Paddle(screenWidth - 70, screenHeight);

        paint.setColor(Color.WHITE);
        paint.setTextSize(50);


        leftPaddlePaint.setColor(Color.MAGENTA);
        leftPaddlePaint.setTextSize(50);

        rightPaddlePaint.setColor(Color.CYAN);
        rightPaddlePaint.setTextSize(50);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
                gameThread = new Thread(() -> {
            while (isRunning) {
                long startTime = System.currentTimeMillis();

                update();
                render();

                long deltaTime = System.currentTimeMillis() - startTime;
                if (deltaTime < 16) {
                    try {
                        Thread.sleep(16 - deltaTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
        try {
            if (gameThread != null) {
                gameThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        if (isGameOver) return;
        ball.update();

        if (ball.checkCollision(paddleLeft) || ball.checkCollision(paddleRight)) {
            ball.reverseX();
        }

        if (ball.isOutLeft()) {
            scoreRight++;
            ball.reset();
        } else if (ball.isOutRight()) {
            scoreLeft++;
            ball.reset();
        }

        if (scoreLeft == winningScore || scoreRight == winningScore) {
            isGameOver = true;
            winningText = "Winner: " + (scoreLeft == winningScore ? "Left Paddle" : "Right Paddle");
        }
    }

    private void render() {
        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);

            ball.draw(canvas, paint);
            paddleLeft.draw(canvas, leftPaddlePaint);
            paddleRight.draw(canvas, rightPaddlePaint);

            canvas.drawText("Score: " + scoreLeft, 100, 100, paint);
            canvas.drawText("Score: " + scoreRight, getWidth() - 300, 100, paint);

            if (isGameOver) {
                Paint winningPaint = new Paint();
                winningPaint.setColor(Color.WHITE);
                winningPaint.setTextSize(90);

                Paint playAgainPaint = new Paint();
                playAgainPaint.setColor(Color.WHITE);
                playAgainPaint.setTextSize(50);

                canvas.drawText("Game Over!", getWidth() / 2f - (winningPaint.measureText("Game Over!") / 2) , getHeight() / 2f - 500, winningPaint);
                canvas.drawText(winningText, getWidth() / 2f - (winningPaint.measureText(winningText) / 2), getHeight() / 2f - 300, winningPaint);
                canvas.drawText("Press anywhere on the screen to play again", getWidth() / 2f - (playAgainPaint.measureText("Press anywhere on the screen to play again") / 2), getHeight() / 2f + 300, playAgainPaint);

            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isGameOver) {
            reset();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float touchY = event.getY();

            if (event.getX() < (float) getWidth() / 2) {
                paddleLeft.move(touchY - paddleLeft.getY());
            } else {
                paddleRight.move(touchY - paddleRight.getY());
            }
        }
        return true;
    }

    public void reset() {
        isGameOver = false;
        scoreLeft = 0;
        scoreRight = 0;
    }
}
