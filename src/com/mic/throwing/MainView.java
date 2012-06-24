package com.mic.throwing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.mic.throwing.sprite.Window;
import com.mic.throwing.sprite.Window.Position;

public class MainView extends SurfaceView implements Callback,Runnable{
    int screenWidth;
	int keyCode = 0;
	String keyAction = "";
	Thread gameThread = null;
	boolean isGame = true;
	SurfaceHolder holder = null;
	Window window1 = null;
	Window window2 = null;
	Window window3 = null;
	Paint backPaint = null;
	Paint forePaint = null;
	int i=0;
	
	public int downb_seq[]={9,10,11,12};
	public MainView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		setFocusable(true);
		getHolder().addCallback(this);
		holder = this.getHolder();
		Bitmap imageTemp = BitmapFactory.decodeResource(getResources(), R.drawable.window);
		Bitmap image = Bitmap.createScaledBitmap(imageTemp, 200, 170, true);
		
		window2 = new Window(image, context, Position.center);
        window1 = new Window(image, context, Position.left);
        window3 = new Window(image, context, Position.right);
		// ���û���
		backPaint = new Paint();
		backPaint.setColor(Color.BLACK);
		
		forePaint = new Paint();
		forePaint.setColor(Color.BLUE);
		
		
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isGame)
		{
			input();
			logic();
			doDraw();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
	}
	//��ʼ��Ϸ���߳�
	public void start()
	{
		if(gameThread == null)
		{
		gameThread = new Thread(this);
		gameThread.start();
		}
	}
	//ֹͣ��Ϸ���߳�
	public void stop()
	{
		isGame = false;
		if(gameThread != null)
		{
			try {
				gameThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//�����ж�
	public void input()
	{
		//hero.nextFrame();
	}
	//�߼��ж�
	public void logic()
	{
		
	}
	public void doDraw()
	{
		Canvas c = null;
		try
		{
		c = holder.lockCanvas();
		synchronized (holder) {
			paint(c);
		}
		}finally{
			if(c != null)
			{
			holder.unlockCanvasAndPost(c);
			}
		}
	}
	//��ͼ
	public void paint(Canvas canvas)
	{
		canvas.drawRect(0, 0, getWidth(), getHeight(), backPaint);
		window1.paint(canvas, forePaint);
		window2.paint(canvas, forePaint);
		window3.paint(canvas, forePaint);
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
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