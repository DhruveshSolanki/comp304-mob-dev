package com.example.dhruveshsolanki_comp304lab1_ex1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dhruveshsolanki_comp304lab1_ex1.model.Note

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var notes = remember { mutableStateOf(listOf<Note>()) }
            HomeScreen(
                notes = notes,
                onAddNote = {
//                    val intent = Intent(this, CreateNoteActivity::class.java)
//                    startActivity(intent)
                },
                onNoteClick = { note ->
                    // Handle note click event (navigate to View/Edit Note Activity)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(notes: MutableState<List<Note>>, onAddNote: () -> Unit, onNoteClick: (Note) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("QuickNotes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ){
        val notesState = notes.value
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(notesState) { note ->
                NoteItem(note = note, onClick = { onNoteClick(note) })
            }
        }
    }

}

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable(onClick = onClick),

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = note.content.take(50) + "...", style = MaterialTheme.typography.bodySmall)
        }
    }
}