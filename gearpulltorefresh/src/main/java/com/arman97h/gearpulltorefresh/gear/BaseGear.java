package com.arman97h.gearpulltorefresh.gear;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class BaseGear {

    private float size;
    private Bitmap gear;
    float radius;

    float centerX;
    float centerY;
    float angle;

    public BaseGear(int gearResourceId, Context context) {
        this.gear = BitmapFactory.decodeResource(context.getResources(), gearResourceId);
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
        this.radius = size / 2;
        this.gear = Bitmap.createScaledBitmap(this.gear, (int) size, (int) size, true);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void draw(Matrix matrix, Canvas canvas) {
        matrix.postTranslate(centerX - radius, centerY - radius);
        matrix.postRotate(angle, centerX, centerY);
        canvas.drawBitmap(gear, matrix, null);
    }
}
