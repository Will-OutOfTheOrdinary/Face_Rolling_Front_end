package com.example.face_rolling.ui.postLog.calendar


import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.face_rolling.MyViewModel
import io.github.boguszpawlowski.composecalendar.SelectableCalendar

import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.rememberSelectableWeekCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode

@Composable
fun Calendar() {
    val calendarState = rememberSelectableCalendarState()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(calendarState = calendarState)

        SelectionControls(selectionState = calendarState.selectionState)
    }
}

@Composable
private fun SelectionControls(
    selectionState: DynamicSelectionState,
) {
//    Text(
//        text = "Calendar Selection Mode",
//        style = MaterialTheme.typography.displayLarge,
//    )
//    SelectionMode.values().forEach { selectionMode ->
//        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
//
//            RadioButton(
//                selected = selectionState.selectionMode == selectionMode,
//                onClick = { selectionState.selectionMode = selectionMode }
//            )
//            Text(text = selectionMode.name)
//            Spacer(modifier = Modifier.height(4.dp))
//        }
//    }

//    Text(
//        text = "Selection: ${selectionState.selection.joinToString { it.toString() }}",
//        style = MaterialTheme.typography.displayLarge,
//    )
}
