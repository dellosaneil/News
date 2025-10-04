package com.thelazybattley.core.util.usecase.impl

import android.content.Context
import com.thelazybattley.core.R
import com.thelazybattley.core.util.usecase.ComputeTimePassedUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Instant
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ComputeTimePassedUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ComputeTimePassedUseCase {
    override fun invoke(time: String): String {
        val millis = Instant.parse(time).toEpochMilli()
        val currentMillis = Instant.now().toEpochMilli()
        val timePassed = currentMillis - millis
        val days = TimeUnit.MILLISECONDS.toDays(timePassed)
        val hours = TimeUnit.MILLISECONDS.toHours(timePassed) % 24
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timePassed) % 60
        val resources = context.resources
        return when {
            days > 0 -> resources.getQuantityString(R.plurals.days_ago, days.toInt(), days)
            hours > 0 -> resources.getQuantityString(R.plurals.hours_ago, hours.toInt(), hours)
            minutes > 0 -> resources.getQuantityString(R.plurals.minutes_ago, minutes.toInt(), minutes)
            else -> ""
        }
    }
}
