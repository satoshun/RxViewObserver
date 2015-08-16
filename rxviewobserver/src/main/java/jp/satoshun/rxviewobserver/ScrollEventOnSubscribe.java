package jp.satoshun.rxviewobserver;

import android.view.View;
import android.view.ViewTreeObserver;

import rx.Observable;
import rx.Subscriber;


class ScrollEventOnSubscribe implements Observable.OnSubscribe<View> {
    private final View view;

    ScrollEventOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super View> subscriber) {
        final ViewTreeObserver.OnScrollChangedListener listener = new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                subscriber.onNext(view);
            }
        };

        view.getViewTreeObserver().addOnScrollChangedListener(listener);
        subscriber.add(new MainHandlerSubscription() {
            @Override
            public void run() {
                view.getViewTreeObserver().removeOnScrollChangedListener(listener);
            }
        });
    }
}
