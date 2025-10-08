package com.thelazybattley.core.util

sealed class TimeAgo {
    data class Minutes(val value: Int) : TimeAgo()
    data class Hours(val value: Int) : TimeAgo()
    data class Days(val value: Int) : TimeAgo()
}
