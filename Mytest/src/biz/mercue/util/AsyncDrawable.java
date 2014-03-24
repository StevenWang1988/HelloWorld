package biz.mercue.util;

import java.lang.ref.WeakReference;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class AsyncDrawable extends BitmapDrawable {
    private final WeakReference<LoadBitmap> bitmapWorkerTaskReference;

    public AsyncDrawable(Resources res, Bitmap bitmap,
    		LoadBitmap bitmapWorkerTask) {
        super(res, bitmap);
        bitmapWorkerTaskReference =
            new WeakReference<LoadBitmap>(bitmapWorkerTask);
    }

    public LoadBitmap getBitmapWorkerTask() {
        return bitmapWorkerTaskReference.get();
    }
}
