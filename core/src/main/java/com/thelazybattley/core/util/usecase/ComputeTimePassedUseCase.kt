package com.thelazybattley.core.util.usecase

import com.thelazybattley.core.util.TimeAgo

interface ComputeTimePassedUseCase {

    operator fun invoke(time: String) : TimeAgo

}
