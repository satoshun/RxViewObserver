package jp.satoshun.rxviewobserver;

import android.view.View;
import android.view.ViewTreeObserver;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

class PreDrawnEventOnSubscribe implements Observable.OnSubscribe<View> {
    final static Func0<Boolean> ALWAY_TRUE = new Func0<Boolean>() {
        @Override
        public Boolean call() {
            return true;
        }
    };

    private final View view;
    private final Func0<Boolean> predicate;

    PreDrawnEventOnSubscribe(View view, Func0<Boolean> predicate) {
        this.view = view;
        this.predicate = predicate;
    }

    @Override
    public void call(final Subscriber<? super View> subscriber) {
        final ViewTreeObserver.OnPreDrawListener listener = new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (predicate.call()) {
                    subscriber.onNext(view);
                    return true;
                }
                return false;
            }
        };

        view.getViewTreeObserver().addOnPreDrawListener(listener);
        subscriber.add(new MainHandlerSubscription() {
            @Override
            public void run() {
                view.getViewTreeObserver().removeOnPreDrawListener(listener);
            }
        });
    }
}
