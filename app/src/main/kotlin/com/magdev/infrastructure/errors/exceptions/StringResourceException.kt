package com.magdev.infrastructure.errors.exceptions

import androidx.annotation.StringRes

open class StringResourceException(@StringRes val stringResourceId: Int): Exception()