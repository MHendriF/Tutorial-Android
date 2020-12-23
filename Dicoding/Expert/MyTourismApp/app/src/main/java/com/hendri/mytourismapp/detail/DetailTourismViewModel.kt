package com.hendri.mytourismapp.detail

import androidx.lifecycle.ViewModel
import com.hendri.mytourismapp.core.data.TourismRepository
import com.hendri.mytourismapp.core.data.source.local.entity.TourismEntity

class DetailTourismViewModel(private val tourismRepository: TourismRepository) : ViewModel() {
    fun setFavoriteTourism(tourism: TourismEntity, newStatus:Boolean) = tourismRepository.setFavoriteTourism(tourism, newStatus)
}

