package com.example.progarkex1.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.progarkex1.MainActivity;
import com.example.progarkex1.R;

public enum Vehicles {
    HELICOPTER(R.drawable.heli1, R.drawable.heli2, R.drawable.heli3, R.drawable.heli4);


    private final Bitmap[][] helicopter = new Bitmap[2][4];

    Vehicles(int... resIDs) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        for (int i = 0; i < resIDs.length; i++) {
            helicopter[0][i] = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resIDs[i], options);
        }

        for (int i = 0; i < helicopter[0].length; i++) {
            if (helicopter[0][i] != null) {
                helicopter[1][i] = flipBitmap(helicopter[0][i]);
            }
        }
    }

    private Bitmap flipBitmap(Bitmap source) {
        if (source == null) return null;
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, false);
    }



    public Bitmap getSprite(int direction, int sprite) {
        return helicopter[direction][sprite];
    }
}
