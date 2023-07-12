package com.example.mylibrary

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mylibrary.data.Datasource
import com.example.mylibrary.model.Affirmation

@ExperimentalMaterial3Api
@Composable
fun MusicApp() {
    Scaffold(
        topBar = {
            MusicTopBar()
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 20.dp)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            MusicList(
                affirmationList = Datasource().loadAffirmations()
            )
        }
    }

}

@ExperimentalMaterial3Api
@Composable
fun MusicTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Teams",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun MusicList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            /*
            MusicCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
             */
            MusicCard(
                affirmation = affirmation
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MusicCardWrapper(affirmation: Affirmation) {
    var expandedState = remember { mutableStateOf(false) }

    MusicCard(
        affirmation = affirmation,
        modifier = Modifier
            .padding(8.dp)
            .clickable { expandedState.value = !expandedState.value }//,
        //expandedState = expandedState // Pass the expanded state as a parameter
    )
}

@ExperimentalMaterial3Api
@Composable
fun MusicCard(affirmation: Affirmation, modifier: Modifier = Modifier/*, expandedState: MutableState<Boolean>*/) {
    //var expandedState = remember { mutableStateOf(false) }
    Card(
        Modifier.padding(start = 8.dp, end = 8.dp, bottom = 12.dp)
            .clickable { affirmation.expandedState.value = !affirmation.expandedState.value }
    ) {
        Column {
            Row(
                Modifier.fillMaxWidth().wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Image(
                        painter = painterResource(id = affirmation.imageResourceId),
                        contentDescription = null,
                        modifier = modifier
                            .size(width = 68.dp, height = 68.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(10)),
                        contentScale = ContentScale.Crop
                    )
                }
                Box(Modifier.weight(1f)) {
                    Text(
                        text = LocalContext.current.getString(affirmation.stringResourceId),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 10.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    if (affirmation.expandedState.value) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                                .clickable { affirmation.expandedState.value = !affirmation.expandedState.value }
                        )
                    }
                    if (!affirmation.expandedState.value) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                                .clickable { affirmation.expandedState.value = !affirmation.expandedState.value }
                        )
                    }
                }
            }
            if (affirmation.expandedState.value) {
                Text(
                    text = "Artist: ${LocalContext.current.getString(affirmation.nameResourceId)}",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 22.sp)
                )
            }
        }
    }
}
