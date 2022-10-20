package com.example.movies.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun Lifecycle.observeAsSate(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsSate.addObserver(observer)
        onDispose {
            this@observeAsSate.removeObserver(observer)
        }
    }
    return state
}

inline fun Modifier.withNoRippleClickable(crossinline onClick: () -> Unit): Modifier =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }

fun formatDate(fromFormat: String, toFormat: String, dateToFormat: String): String {
    val inFormat = SimpleDateFormat(fromFormat, Locale.UK)
    var date: Date? = null
    try {
        date = inFormat.parse(dateToFormat)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    val outFormat = SimpleDateFormat(toFormat, Locale.UK)
    return outFormat.format(date ?: Date())
}