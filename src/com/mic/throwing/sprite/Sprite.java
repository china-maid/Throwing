package com.mic.throwing.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Sprite {
    
    private int     length           = 0;    // ֡���г���
    private int     currentPosition  = 0;    // ��ǰ֡�����±�
    private int[][] framesXY         = null; // ����֡������ͼƬ������
    protected int   frameWidth       = 0;    // ֡�Ŀ��
    protected int   frameHeight      = 0;    // ֡�ĸ߶�
    private Bitmap  bigFrames        = null; // ���ͼƬ
    private int     cols             = 0;
    private int     rows             = 0;
    protected int   PositionX        = 0;    // �����X����
    protected int   PositionY        = 0;    // �����Y����
                                             
    private int[]   frameSequence    = null; // �����֡����
    private int     sequencePosition = 0;    // �����֡���е���ʾ�±�
     public Sprite() {
        // TODO Auto-generated constructor stub
    }
    public Sprite(Bitmap image, int frameWidth, int frameHeight) {
        // ��ʼ��
        this.bigFrames = image;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        
        framesXY = initSprite(image, frameWidth, frameHeight);
        this.length = framesXY.length;
    }
    
    // ��ʼ������
    private int[][] initSprite(Bitmap bitmap, int frameWidth, int frameHeight) {
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        this.cols = Math.round(imageWidth / frameWidth);// ����
        this.rows = Math.round(imageHeight / frameHeight);// ����
        
        int[][] tempXYs = new int[cols * rows][2];
        for (int i = 0, k = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++, k++) {
                tempXYs[k][0] = j * frameWidth;
                tempXYs[k][1] = i * frameHeight;
            }
        }
        
        return tempXYs;
    }
    
    // ��õ�ǰ֡���±�
    public int getFrame() {
        return this.currentPosition;
    }
    
    // ���֡���еĳ���
    public int getFrameSequenLength() {
        return framesXY.length;
    }
    
    // ��õ�ǰ֡���е���һ��֡
    public void nextFrame() {
        // ����Զ���֡����Ϊ�գ���ʹ��Ĭ�ϵ�
        if (frameSequence == null) {
            currentPosition++;
            if (currentPosition > length - 1) {
                currentPosition = 0;
            }
        } else {
            sequencePosition++;
            if (sequencePosition > frameSequence.length - 1) {
                sequencePosition = 0;
            }
            currentPosition = frameSequence[sequencePosition];
        }
    }
    
    // ��õ�ǰ֡���е���һ��֡
    public void prevFrame() {
        if (this.frameSequence == null) {
            currentPosition--;
            if (currentPosition < 0) {
                currentPosition = length - 1;
            }
        } else {
            sequencePosition--;
            if (sequencePosition < 0) {
                sequencePosition = frameSequence.length - 1;
            }
            currentPosition = frameSequence[sequencePosition];
        }
        
    }
    
    // ���õ�ǰ֡
    public void setFrame(int sequenceIndex) {
        if (sequenceIndex >= 0 && sequenceIndex < length) {
            this.currentPosition = sequenceIndex;
        }
    }
    
    // ����λ��
    public void setPosition(int positionX, int positionY) {
        this.PositionX = positionX;
        this.PositionY = positionY;
    }
    
    public int getPositionX() {
        return PositionX;
    }
    
    public int getPositionY() {
        return PositionY;
    }
    
    // ����֡����
    public void setFrameSequence(int[] sequence) {
        
        if (this.frameSequence != null) {
            currentPosition = sequence[0];
            sequencePosition = 0;
        } else {
            this.frameSequence = sequence;
        }
    }
    
    // �ƶ�
    public void move(int x, int y) {
        PositionX += x;
        PositionY += y;
    }
    
    public void paint(Canvas canvas, Paint paint) {
        Log.d("ddd", "" + this.PositionX);
        Log.d("ddd", "" + this.PositionY);
        Bitmap bitmap = Bitmap.createBitmap(this.bigFrames,
            this.framesXY[currentPosition][0],
            this.framesXY[currentPosition][1], this.frameWidth,
            this.frameHeight);
        canvas.drawBitmap(bitmap, PositionX, PositionY, paint);
    }
}