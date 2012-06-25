package com.mic.throwing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.mic.throwing.sprite.Window;

public class MainView extends SurfaceView implements Callback, Runnable {
    int           keyCode     = 0;
    String        keyAction   = "";
    Thread        gameThread  = null;
    boolean       isGame      = true;
    SurfaceHolder holder      = null;
    Window        window1     = null;
    Window        window2     = null;
    Window        window3     = null;
    Paint         backPaint   = null;
    Paint         forePaint   = null;
    int           i           = 0;
    public int    downb_seq[] = { 9, 10, 11, 12 };
    
    public MainView(Context context) {
        super(context);
        setFocusable(true);
        getHolder().addCallback(this);
        holder = this.getHolder();
        Point size = getWindowSize(context);
        initWindows(context, size);
        initPaints();
    }
    
    private Point getWindowSize(Context context) {
        Point size = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        return size;
    }
    
    private void initPaints() {
        backPaint = new Paint();
        backPaint.setColor(Color.BLACK);
        forePaint = new Paint();
        forePaint.setColor(Color.BLUE);
    }
    
    private void initWindows(Context context, Point size) {
        window2 = new Window(context, size);
        window1 = new Window(context, size);
        window3 = new Window(context, size);
        window2.setPosition(size.x / 2 - window2.frameWidth / 2, 20);
        window1.setPosition(size.x / 2 - window1.frameWidth / 2 - (window1.frameWidth + 30), 20 + window1.frameHeight / 2);
        window3.setPosition(size.x / 2 - window3.frameWidth / 2 - (window3.frameWidth + 30), 20 + window3.frameHeight / 2);
    }
    
    @Override
    public void run() {
        while (isGame) {
            input();
            logic();
            doDraw();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void start() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
    
    public void stop() {
        isGame = false;
        if (gameThread != null) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void input() {
        //hero.nextFrame();
    }
    
    public void logic() {
    }
    
    public void doDraw() {
        Canvas c = null;
        try {
            c = holder.lockCanvas();
            synchronized (holder) {
                paint(c);
            }
        } finally {
            if (c != null) {
                holder.unlockCanvasAndPost(c);
            }
        }
    }
    
    public void paint(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), backPaint);
        window1.paint(canvas, forePaint);
        window2.paint(canvas, forePaint);
        window3.paint(canvas, forePaint);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        start();
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        stop();
    }
}
