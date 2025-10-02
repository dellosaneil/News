package com.thelazybattley.feature.home

import androidx.lifecycle.ViewModel
import com.thelazybattley.core.network.usecase.FetchNewsByKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsByKeywordUseCase: FetchNewsByKeywordUseCase
): ViewModel() {

}
