package io.cheesecakelabs.core.utils.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadImage(
    requestManager: RequestManager,
    url: String?,
    placeholder: Int? = null,
    requestOptions: RequestOptions? = null,
    error: Int? = null,
    glideListener: ((Any?) -> Unit)? = null
) {
    requestManager
        .load(url)
        .run {
            if (placeholder != null) placeholder(placeholder)
            else this
        }
        .run {
            if (requestOptions != null) apply(requestOptions)
            else this
        }
        .run {
            if (error != null) error(error)
            else this
        }
        .run {
            if (glideListener != null) {
                listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        glideListener(e)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        glideListener(resource)
                        return false
                    }
                })
            } else this

        }
        .into(this)
}

fun ImageView.loadImage(
    context: Context,
    url: String?,
    placeholder: Int? = null,
    requestOptions: RequestOptions? = null,
    error: Int? = null,
    listener: ((Any?) -> Unit)? = null
) {
    loadImage(
        Glide.with(context),
        url,
        placeholder = placeholder,
        requestOptions = requestOptions,
        error = error,
        glideListener = listener
    )
}