package com.thelazybattley.core.util.usecase

interface ComputeTimePassedUseCase {

    operator fun invoke(time: String) : String

}
