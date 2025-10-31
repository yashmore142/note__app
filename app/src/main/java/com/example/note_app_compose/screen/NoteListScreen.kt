package com.example.note_app_compose.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import com.example.note_app_compose.R

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.viewmodel.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(mViewModel: NoteViewModel, navController: NavController) {
    var list by remember { mutableStateOf<List<Note>>(emptyList()) }

    val lifecycle = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        mViewModel.getAllNotes.observe(lifecycle) {
            Log.i("demoList", it.toString())
            list = it
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_note") },
                modifier = Modifier.padding(15.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null)
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {

            if (list.isNotEmpty()) {
                LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), content = {
                    items(list!!.size) {
                        NoteItem(list[it], mViewModel, navController)
                    }

                })
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.data_not_found),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun NoteItem(note: Note, noteViewModel: NoteViewModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .combinedClickable(
                onDoubleClick = {

                },
                onClick = {
                    navController.navigate("update_note/${note.id}")
                }
            ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            colorResource(id = note.color)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = note.title, maxLines = 1,
                style = TextStyle(
                    Color.Black,
                    22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = note.description,
                maxLines = 4,
                fontSize = 18.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    if (note.pin) {
                        painterResource(id = R.drawable.baseline_favorite_24)
                    } else {
                        painterResource(id = R.drawable.iv_fav)

                    },
                    contentDescription = null,

                    modifier = Modifier.clickable {
                        if (note.pin) {
                            note.pin = false
                        } else {
                            note.pin = true
                        }
                        noteViewModel.updateNote(note)
                    }
                )
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier

                        .clickable {
                            noteViewModel.deleteNote(note)
                        }
                )
            }

        }
    }
}