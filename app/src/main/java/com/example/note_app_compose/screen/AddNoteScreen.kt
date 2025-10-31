package com.example.note_app_compose.screen

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note_app_compose.R
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddNoteScreen(mViewModel: NoteViewModel, navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var backGroundColor by remember { mutableStateOf(R.color.white) }

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
                    text = "Add Note", style = TextStyle(
                        Color.Black,
                        24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(20.dp)
                )
                Button(
                    onClick = {
                        val note = Note(
                            title = title,
                            description = description,
                            createdAt = System.currentTimeMillis(),
                            color = backGroundColor,
                            id = 0
                        )
                        mViewModel.addNote(note)
                        navController.popBackStack()
                    }, modifier = Modifier
                        .padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        )
                ) {
                    Text(text = "Add", fontSize = 16.sp)
                }

            }

            // Keep track of selected color ID
            var selectedColor by remember { mutableStateOf<Int?>(null) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val colors = listOf(
                    R.color.purple_200,
                    R.color.teal_700,
                    R.color.teal_200,
                    R.color.yellow,
                    R.color.pink
                )

                colors.forEach { colorId ->
                    val color = colorResource(id = colorId)
                    val borderWidth by animateDpAsState(
                        targetValue = if (selectedColor == colorId) 3.dp else 0.dp,
                        label = ""
                    )
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(color = color, shape = CircleShape)
                            .border(borderWidth, Color.Black, CircleShape)
                            .clickable { selectedColor = colorId
                                backGroundColor = colorId}
                    )

                }
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