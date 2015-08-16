package jp.satoshun.rxviewobserver;

import android.view.View;
import android.view.ViewTreeObserver;

import rx.Observable;
import rx.Subscriber;


class GlobalFocusEventOnSubscribe implements Observable.OnSubscribe<View> {
    private final View view;

    GlobalFocusEventOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super View> subscriber) {
        final ViewTreeObserver.OnGlobalFocusChangeListener listener = new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                subscriber.onNext(newFocus);
            }
        };

        view.getViewTreeObserver().addOnGlobalFocusChangeListener(listener);
        subscriber.add(new MainHandlerSubscription() {
            @Override
            public void run() {
                view.getViewTreeObserver().removeOnGlobalFocusChangeListener(listener);
            }
        });
    }
}
