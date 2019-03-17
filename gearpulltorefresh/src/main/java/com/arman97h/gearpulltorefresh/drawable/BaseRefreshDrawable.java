package com.arman97h.gearpulltorefresh.drawable;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public abstract class BaseRefreshDrawable extends Drawable implements Drawable.Callback, Animatable {

    int viewHeightDP;
    protected int refreshOffsetDP;
    protected Context context;

    BaseRefreshDrawable(Context context, int viewHeightDP, int refreshOffsetDP) {
        this.context = context;
        this.viewHeightDP = viewHeightDP;
        this.refreshOffsetDP = refreshOffsetDP;
    }

    public Context getContext() {
        return context;
    }


    public abstract void setPercent(float percent, boolean invalidate);

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }
}