package com.jalexy.androidjetpack.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jalexy.androidjetpack.R
import com.jalexy.androidjetpack.domain.models.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.result_required_answers_text),
        count
    )
}

@BindingAdapter("currentAnswers")
fun bindCurrentAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.result_score_text),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercent(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.result_required_percentage_text),
        count
    )
}

@BindingAdapter("score")
fun bindScore(textView: TextView, result: GameResult) {
    textView.text = String.format(
        textView.context. getString(R.string.result_score_percentage_text),
        getPercentOfRightAnswers(result)
    )
}

private fun getPercentOfRightAnswers(result: GameResult) = with(result) {
    if(countQuestions == 0) {
        0
    } else {
        ((countRightAnswers / countQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("finishIcon")
fun bindFinishIcon(imageView: ImageView, isWin: Boolean) {
    imageView.setImageResource(getResultImage(isWin))
}

private fun getResultImage(isWin: Boolean): Int =
    if(isWin) {
        R.drawable.ic_check_circle
    } else {
        R.drawable.ic_loose
    }