package com.tbuonomo.viewpagerdotsindicator

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

fun View.setPaddingHorizontal(padding: Int) {
  setPadding(padding, paddingTop, padding, paddingBottom)
}

fun View.setPaddingVertical(padding: Int) {
  setPadding(paddingLeft, padding, paddingRight, padding)
}


@BindingAdapter("allColor")
fun DotsIndicator.allColor(@ColorInt color: Int) {
    this.dotsColor = color
    this.selectedDotColor = color
}