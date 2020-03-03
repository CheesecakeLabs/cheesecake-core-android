package io.cheesecakelabs.core.utils.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

fun <T> AppCompatActivity.observe(liveData: MutableLiveData<T>, callback: (T?) -> Unit) = liveData.observe(this, Observer { callback(it) })

fun AppCompatActivity.observeUnit(liveData: MutableLiveData<Unit>, callback: () -> Unit) = liveData.observe(this, Observer { callback() })