package com.ubaya.lulusgaming.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.lang.Exception


@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url:String){
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener{ picasso, uri, exception ->
        exception.printStackTrace()
    }
    picasso.build().load(url).into(imageView, object : com.squareup.picasso.Callback {
        override fun onSuccess() {
            imageView.visibility = View.VISIBLE
        }
        override fun onError(e: Exception?) {

        }
    })
}