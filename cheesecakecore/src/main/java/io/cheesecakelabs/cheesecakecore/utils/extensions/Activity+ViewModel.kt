package io.cheesecakelabs.cheesecakecore.utils.extensions

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

fun <T> AppCompatActivity.observe(liveData: MutableLiveData<T>, callback: (T?) -> Unit) = liveData.observe(this, Observer { callback(it) })

fun AppCompatActivity.observeUnit(liveData: MutableLiveData<Unit>, callback: () -> Unit) = liveData.observe(this, Observer { callback() })