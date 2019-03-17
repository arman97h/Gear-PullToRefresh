package com.arman97h.gearpulltorefresh.gear;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class ConnectibleGear extends BaseGear {
    private float distanceX;
    private float distanceY;
    private float ratio;
    private BaseGear connectedBaseGear;
    private float angelOffset;
    private float distanceRatio;

    public ConnectibleGear(int gearResourceId, Context context, float angelOffset, float distanceRatio) {
        super(gearResourceId, context);
        this.angelOffset = angelOffset;
        this.distanceRatio = distanceRatio;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public void setConnectedBaseGear(BaseGear connectedBaseGear, float ratio, float ratioX, float ratioY) {
        this.connectedBaseGear = connectedBaseGear;
        this.ratio = ratio;
        this.ratio = ratio;
        setSize(ratio * connectedBaseGear.getSize());

        float distance = (radius + connectedBaseGear.getRadius()) * distanceRatio;
        distanceX = ratioX * distance;
        distanceY = ratioY * distance;
    }

    @Override
    public void draw(Matrix matrix, Canvas canvas) {
        centerX = connectedBaseGear.getCenterX() + distanceX;
        centerY = connectedBaseGear.getCenterY() + distanceY;
        angle = 360 - 1 / ratio * connectedBaseGear.getAngle() + angelOffset;
        super.draw(matrix, canvas);
    }
}
