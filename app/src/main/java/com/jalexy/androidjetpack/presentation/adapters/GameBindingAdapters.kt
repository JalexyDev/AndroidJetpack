package com.jalexy.androidjetpack.presentation.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("percentRightAnswers")
fun bindPercentRightAnswers(progressBar: ProgressBar, count: Int) {
    progressBar.setProgress(count, true)
}

@BindingAdapter("progressTintColor")
fun bindProgressTintColor(progressBar: ProgressBar, goodState: Boolean) {
    val color = getColorByState(goodState, progressBar.context)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(goodState: Boolean, context: Context): Int {
    val colorResId = if(goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("answersTextColor")
fun bindAnswersTextColor(textView: TextView, goodState: Boolean) {
    textView.setTextColor(getColorByState(goodState, textView.context))
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}