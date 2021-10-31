package deghat.farhad.common.presentation

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

@BindingAdapter("app:textId")
fun setTextById(textView: TextView, @StringRes stringId: Int){
    if(stringId != 0)
        textView.text = textView.context.getString(stringId)
}