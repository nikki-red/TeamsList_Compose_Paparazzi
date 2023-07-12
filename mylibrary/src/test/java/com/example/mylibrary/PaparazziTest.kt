package com.example.mylibrary

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_3_XL
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import com.example.mylibrary.data.Datasource
import com.example.mylibrary.model.Affirmation

class PaparazziTest {

    @get:Rule
    var paparazzi = Paparazzi(
        //deviceConfig = DeviceConfig.NEXUS_5.copy(softButtons = false, screenHeight = 1),
        //renderingMode = SessionParams.RenderingMode.V_SCROLL
        deviceConfig = PIXEL_3_XL,
        theme = "Theme.Material.Light.NoActionBar"
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testEntireMusicApp() {
        paparazzi.snapshot {
            MusicApp()
        }
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testTopBar() {
        paparazzi.snapshot {
            Scaffold(
                topBar = {
                    MusicTopBar()
                }
            ) {
                Text("Body")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testMusicList() {
        paparazzi.snapshot { 
            MusicList(affirmationList = Datasource().loadAffirmations())
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testMusicCardClosed() {
        paparazzi.snapshot {
            //MusicCardWrapper(Affirmation(R.string.affirmation1, R.string.name1, R.drawable.image1))
            var expandedState = remember { mutableStateOf(false) }
            MusicCard(
                affirmation = Affirmation(R.string.affirmation1, R.string.name1, R.drawable.image1, expandedState),
                //expandedState = expandedState
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testMusicCardExpanded() {
        paparazzi.snapshot {
            var expandedState = remember { mutableStateOf(true) }
            MusicCard(
                affirmation = Affirmation(R.string.affirmation1, R.string.name1, R.drawable.image1, expandedState),
                //expandedState = expandedState
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testMusicListExpanded() {
        var affirmationList = Datasource().loadAffirmations()
        paparazzi.snapshot {
            affirmationList[1].expandedState = remember { mutableStateOf(true) }
            MusicList(affirmationList = affirmationList)
            //MusicApp()
        }
    }

}