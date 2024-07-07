package com.comppot.mindsnack.ui.utils

import androidx.compose.ui.graphics.Color

fun Color.takeColorIf(condition: Boolean): Color = if (condition) this else Color.Transparent