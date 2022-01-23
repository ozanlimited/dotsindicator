package com.tbuonomo.viewpagerdotsindicator

import android.view.View
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
fun DotsIndicator.allColor(@ColorRes colorRes: Int) {
    val color = ContextCompat.getColor(context, colorRes)
    this.dotsColor = color
    this.selectedDotColor = color
}