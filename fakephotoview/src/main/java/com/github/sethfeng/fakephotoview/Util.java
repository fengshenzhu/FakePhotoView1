package com.github.sethfeng.fakephotoview;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.github.sethfeng.fakephotoview.fake.Fakeable;

public class Util {

    static void checkZoomLevels(float minZoom, float midZoom,
                                float maxZoom) {
        if (minZoom >= midZoom) {
            throw new IllegalArgumentException(
                    "Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        } else if (midZoom >= maxZoom) {
            throw new IllegalArgumentException(
                    "Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    static boolean hasDrawable(Fakeable imageView) {
        return imageView.getFakeDrawable() != null;
    }

    static boolean isSupportedScaleType(final ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (scaleType) {
            case MATRIX:
                throw new IllegalStateException("Matrix scale type is not supported");
        }
        return true;
    }

    static int getPointerIndex(int action) {
        return (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
    }

    public static boolean matrixChanged(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null && matrix2 == null) return false;
        if (matrix1 == null || matrix2 == null) return true;
        float value1[] = new float[9];
        float value2[] = new float[9];
        matrix1.getValues(value1);
        matrix2.getValues(value2);
        for (int i = 0; i < 9; i++) {
            if (Math.abs(value1[i] - value2[i]) > 0.1) {
                return true;
            }
        }
        return false;
    }
}
