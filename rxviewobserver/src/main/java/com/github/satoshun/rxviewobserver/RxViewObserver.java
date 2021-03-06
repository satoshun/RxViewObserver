package com.github.satoshun.rxviewobserver;

import android.support.annotation.CheckResult;
import android.view.View;

import rx.Observable;
import rx.functions.Func0;

public class RxViewObserver {

    @CheckResult
    public static Observable<View> scroll(final View view) {
        return Observable.create(new ScrollEventOnSubscribe(view));
    }

    @CheckResult
    public static Observable<View> globalFocus(final View view) {
        return Observable.create(new GlobalFocusEventOnSubscribe(view));
    }

    @CheckResult
    public static Observable<Boolean> touchMode(final View view) {
        return Observable.create(new TouchModeEventOnSubscribe(view));
    }

    @CheckResult
    public static Observable<View> preDrawn(final View view) {
        return preDrawn(view, PreDrawnEventOnSubscribe.ALWAY_TRUE);
    }

    @CheckResult
    public static Observable<View> preDrawn(final View view, Func0<Boolean> predicate) {
        return Observable.create(new PreDrawnEventOnSubscribe(view, predicate));
    }
}
