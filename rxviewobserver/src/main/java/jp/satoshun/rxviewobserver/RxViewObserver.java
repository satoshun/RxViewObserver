package jp.satoshun.rxviewobserver;

import android.view.View;

public class RxViewObserver {
    public static RxViewObserver create(final View view) {
        return new RxViewObserver(view);
    }

    private RxViewObserver(final View view) {
    }
}
