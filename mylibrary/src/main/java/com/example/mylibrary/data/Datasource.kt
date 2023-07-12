package com.example.mylibrary.data

import androidx.compose.runtime.mutableStateOf
import com.example.mylibrary.R
import com.example.mylibrary.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.string.name1, R.drawable.image1, mutableStateOf(false)),
            Affirmation(R.string.affirmation2, R.string.name2, R.drawable.image2, mutableStateOf(false)),
            Affirmation(R.string.affirmation3, R.string.name3, R.drawable.image3, mutableStateOf(false)),
            Affirmation(R.string.affirmation4, R.string.name4, R.drawable.image4, mutableStateOf(false)),
            Affirmation(R.string.affirmation5, R.string.name5, R.drawable.image5, mutableStateOf(false)),
            Affirmation(R.string.affirmation6, R.string.name6, R.drawable.image6, mutableStateOf(false)),
            Affirmation(R.string.affirmation7, R.string.name7, R.drawable.image7, mutableStateOf(false)),
            Affirmation(R.string.affirmation8, R.string.name8, R.drawable.image8, mutableStateOf(false)),
            Affirmation(R.string.affirmation9, R.string.name9, R.drawable.image9, mutableStateOf(false)),
            Affirmation(R.string.affirmation10, R.string.name10, R.drawable.image10, mutableStateOf(false)))
    }
}