package com.arman97h.gearpulltorefresh.drawable;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.arman97h.gearpulltorefresh.R;
import com.arman97h.gearpulltorefresh.gear.BaseGear;
import com.arman97h.gearpulltorefresh.gear.ConnectibleGear;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Sigmatix on 2/26/2019.
 */
public class MechanismDrawable extends BaseRefreshDrawable {

    private static final float RATIO_GEAR_VIEW_HEIGHT = 0.7f;
    private static final int BASE_GEAR_POSITION = 0;
    private static final int INITIAL_OFFSET = 0;
    private static final int INITIAL_ANGE = 0;
    private static final int MAX_ANIMATING_ANGLE = 360;
    private static final int MAX_ROTATING_ANGLE = 180;
    private static final int ANIMATION_DURATION = 2000;
    private static final String ANIMATION_METHOD_NAME = "animateAngle";

    private int viewHeight;
    private Matrix matrix;
    private float offset;
    private boolean isRunning;

    private float animatingAngle;
    private ObjectAnimator objectAnimator;
    private List<BaseGear> gears;

    private float baseGearSize;

    public MechanismDrawable(Context context, int viewHeightDP, int refreshOffsetDP) {
        super(context, viewHeightDP, refreshOffsetDP);
        initObjectAnimator();
        initiateDimens();
    }

    private void initiateDimens() {
        matrix = new Matrix();
        gears = new ArrayList<>();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        viewHeight = (int) (viewHeightDP * metrics.density);
        baseGearSize = viewHeight * RATIO_GEAR_VIEW_HEIGHT;

        BaseGear mainBaseGear;
        mainBaseGear = new BaseGear(R.drawable.detail_center, context);
        mainBaseGear.setSize(baseGearSize);
        gears.add(mainBaseGear);

        ConnectibleGear secondGear;
        secondGear = new ConnectibleGear(R.drawable.detail_2, context, 12f, 0.9777f);
        secondGear.setConnectedBaseGear(mainBaseGear, 1.8f, -4 / 5f, 3 / 5f);
        gears.add(secondGear);

        ConnectibleGear thirdGear;
        thirdGear = new ConnectibleGear(R.drawable.detail_5, context, -14.5f, 0.9777f);
        thirdGear.setConnectedBaseGear(mainBaseGear, 1.6f, +4 / 5f, -3 / 5f);
        gears.add(thirdGear);

        ConnectibleGear fourthGear;
        fourthGear = new ConnectibleGear(R.drawable.detail_3, context, -5, 0.94f);
        fourthGear.setConnectedBaseGear(thirdGear, 1.0f, +4 / 5f, 3 / 5f);
        gears.add(fourthGear);

        ConnectibleGear fifthGear;
        fifthGear = new ConnectibleGear(R.drawable.detail_3, context, 3, 0.94f);
        fifthGear.setConnectedBaseGear(secondGear, 8 / 9f, -0.57f, -0.82f);
        gears.add(fifthGear);
    }

    @Override
    public void setPercent(float offset, boolean invalidate) {
        this.offset = offset;
        if (offset < viewHeight) {
            invalidateSelf();
        }
    }

    @Override
    public void start() {
        isRunning = true;
        objectAnimator.start();
    }

    @Override
    public void stop() {
        matrix.reset();
        offset = INITIAL_OFFSET;
        gears.get(BASE_GEAR_POSITION).setAngle(INITIAL_ANGE);
        objectAnimator.cancel();
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.translate(0, viewHeight - baseGearSize / 2f - offset / 2);
        prepareBaseGear(canvas);
    }

    private void prepareBaseGear(Canvas canvas) {
        gears.get(BASE_GEAR_POSITION).setCenterX(canvas.getWidth() / 2);
        gears.get(BASE_GEAR_POSITION).setCenterY(gears.get(BASE_GEAR_POSITION).getRadius());

        if (!isRunning) {
            gears.get(BASE_GEAR_POSITION).setAngle(MAX_ROTATING_ANGLE * offset / viewHeight);
        } else {
            gears.get(BASE_GEAR_POSITION).setAngle(animatingAngle);
        }
        drawGears(canvas);
    }

    private void drawGears(Canvas canvas) {
        for (BaseGear baseGear : gears) {
            Matrix matrix = this.matrix;
            matrix.reset();
            baseGear.draw(matrix, canvas);
        }
    }

    private void initObjectAnimator() {
        objectAnimator = ObjectAnimator.ofFloat(this, ANIMATION_METHOD_NAME, MAX_ANIMATING_ANGLE);
        objectAnimator.setInterpolator(null);
        objectAnimator.setDuration(ANIMATION_DURATION);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    private void setAnimateAngle(float angle) {
        animatingAngle = angle;
        invalidateSelf();
    }
}
