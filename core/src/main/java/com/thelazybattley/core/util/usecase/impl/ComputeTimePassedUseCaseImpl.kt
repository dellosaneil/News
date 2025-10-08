package com.thelazybattley.core.util.usecase.impl

import com.thelazybattley.core.util.TimeAgo
import com.thelazybattley.core.util.usecase.ComputeTimePassedUseCase
import java.time.Instant
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ComputeTimePassedUseCaseImpl @Inject constructor() : ComputeTimePassedUseCase {
    override fun invoke(time: String): TimeAgo {
        val millis = Instant.parse(time).toEpochMilli()
        val currentMillis = Instant.now().toEpochMilli()
        val timePassed = currentMillis - millis
        val days = TimeUnit.MILLISECONDS.toDays(timePassed)
        val hours = TimeUnit.MILLISECONDS.toHours(timePassed) % 24
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timePassed) % 60
        return when {
            days > 0 -> TimeAgo.Days(value = days.toInt())
            hours > 0 -> TimeAgo.Hours(value = hours.toInt())
            else -> TimeAgo.Minutes(value = minutes.toInt())
        }
    }
}
