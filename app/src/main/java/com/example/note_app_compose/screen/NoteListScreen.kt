package com.example.note_app_compose.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.viewmodel.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(mViewModel: NoteViewModel,navController: NavController) {
    var list : List<Note> = listOf()
     mViewModel.getAllNotes.observe(LocalLifecycleOwner.current){
      Log.i("demoList", it.toString())
         list = it
     }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate("add_note") }, modifier = Modifier.padding(15.dp)) {
            Icon(Icons.Default.Edit, contentDescription = null)
        }
    }) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()) {

            if (!list.isNullOrEmpty()) {
                LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), content = {
                    items(list!!.size) {
                        NoteItem(list[it])
                    }
                })
            }
        }
    }
}

@Preview
@Composable
fun NoteItem(note: Note){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),

        ) {
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(
                text = note.title, style = TextStyle(
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

        }
    }
}