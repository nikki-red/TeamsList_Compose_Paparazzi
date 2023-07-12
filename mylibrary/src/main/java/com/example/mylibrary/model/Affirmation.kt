package com.example.mylibrary.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @StringRes val nameResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    var expandedState: MutableState<Boolean>
)