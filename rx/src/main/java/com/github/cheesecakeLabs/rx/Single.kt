package com.github.cheesecakeLabs.rx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.setupDefaultSubscription(actionAfterTerminate: (() -> Unit)? = { Unit }): Single<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doAfterTerminate(actionAfterTerminate)
}
