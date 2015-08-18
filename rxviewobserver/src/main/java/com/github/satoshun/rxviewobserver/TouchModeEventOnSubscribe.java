package com.github.satoshun.rxviewobserver;

import android.view.View;
import android.view.ViewTreeObserver;

import rx.Observable;
import rx.Subscriber;


class TouchModeEventOnSubscribe implements Observable.OnSubscribe<Boolean> {
    private final View view;

    TouchModeEventOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super Boolean> subscriber) {
        final ViewTreeObserver.OnTouchModeChangeListener listener = new ViewTreeObserver.OnTouchModeChangeListener() {
            @Override
            public void onTouchModeChanged(boolean isInTouchMode) {
                subscriber.onNext(isInTouchMode);
            }
        };

        view.getViewTreeObserver().addOnTouchModeChangeListener(listener);
        subscriber.add(new MainHandlerSubscription() {
            @Override
            public void run() {
                view.getViewTreeObserver().removeOnTouchModeChangeListener(listener);
            }
        });
    }
}
