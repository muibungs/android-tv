package com.example.myapplication.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import timber.log.Timber

fun ImageView.loadImage(url: String? = "", @DrawableRes placeholder: Int) {
    url?.let {
        val realPath = ImageUtil.getRealPath(this.context, url)
        Glide.with(this).load(realPath)
            .placeholder(placeholder)
            .error(placeholder)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.d("url: $url, realPath: $realPath")
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    this@loadImage.setImageDrawable(resource)
                    return true
                }
            })
            .into(this)
    } ?: run {
        Glide.with(this).load(placeholder)
            .into(this)
    }
}