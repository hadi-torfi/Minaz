package com.haditorfi.minaz.services

import com.haditorfi.minaz.view.MyImageView


interface ImageLoadingService {
    fun load(imageView: MyImageView, imageUrl: String)
}