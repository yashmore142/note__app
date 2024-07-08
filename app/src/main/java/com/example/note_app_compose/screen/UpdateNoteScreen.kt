package com.example.note_app_compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note_app_compose.R
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNoteScreen(noteId: Int, mViewModel: NoteViewModel, navController: NavController) {


    var title by remember { mutableStateOf("") }
    var noteid = 0
    var pin = false
    var description by remember { mutableStateOf("") }
    var backGroundColor by remember { mutableStateOf(R.color.white) }

    mViewModel.getSingleNote(noteId)
    val owner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        mViewModel.getSingleNote!!.observe(owner) {
            noteid = it.id
            title = it.title
            description = it.description
            backGroundColor = it.color
            pin = it.pin
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = backGroundColor))
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Update Note", style = TextStyle(
                        Color.Black,
                        24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(20.dp)
                )
                Button(
                    onClick = {
                        val note = Note(
                            id = noteId,
                            title,
                            description,
                            backGroundColor,
                            System.currentTimeMillis(),
                            pin
                        )

                        mViewModel.updateNote(note)
                        navController.popBackStack()
                    }, modifier = Modifier
                        .padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        )
                ) {
                    Text(text = "Update", fontSize = 16.sp)
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = colorResource(id = R.color.purple_200),
                            shape = CircleShape
                        )
                        .clickable {
                            backGroundColor = R.color.purple_200
                        }
                )
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = colorResource(id = R.color.white), shape = CircleShape)
                        .clickable {
                            backGroundColor = R.color.white
                        }
                )
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = colorResource(id = R.color.teal_200),
                            shape = CircleShape
                        )
                        .clickable {
                            backGroundColor = R.color.teal_200
                        }
                )
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = colorResource(id = R.color.yellow), shape = CircleShape)
                        .clickable {
                            backGroundColor = R.color.yellow
                        }
                )
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = colorResource(id = R.color.pink), shape = CircleShape)
                        .clickable {
                            backGroundColor = R.color.pink
                        }
                )
            }
            OutlinedTextField(
                value = title, onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black
                ),
                label = {
                    Text(text = "Title", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            )

            OutlinedTextField(
                value = description, onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                textStyle = TextStyle(color = Color.Black),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black
                ),
                label = {
                    Text(text = "Description", color = Color.Black)
                }
            )

        }
    }
}