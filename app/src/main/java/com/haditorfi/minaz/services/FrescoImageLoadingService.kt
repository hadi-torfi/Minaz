package com.haditorfi.minaz.services

import com.facebook.drawee.view.SimpleDraweeView
import com.haditorfi.minaz.view.MyImageView

class FrescoImageLoadingService : ImageLoadingService {
    override fun load(imageView: MyImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(imageUrl)
        else
            throw IllegalStateException("ImageView must be instance of SimpleDraweeView")
    }
}