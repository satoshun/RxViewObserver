package jp.satoshun.rxviewobserver;

import android.support.annotation.CheckResult;
import android.view.View;

import rx.Observable;

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
}
