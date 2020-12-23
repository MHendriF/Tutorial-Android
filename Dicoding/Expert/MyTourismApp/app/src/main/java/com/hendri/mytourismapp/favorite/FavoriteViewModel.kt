package com.hendri.mytourismapp.favorite

import androidx.lifecycle.ViewModel
import com.hendri.mytourismapp.core.data.TourismRepository

class FavoriteViewModel(tourismRepository: TourismRepository) : ViewModel() {

    val favoriteTourism = tourismRepository.getFavoriteTourism()

}

