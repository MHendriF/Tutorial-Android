package com.hendri.mytourismapp.home

import androidx.lifecycle.ViewModel
import com.hendri.mytourismapp.core.data.TourismRepository

class HomeViewModel(tourismRepository: TourismRepository) : ViewModel() {

    val tourism = tourismRepository.getAllTourism()

}

