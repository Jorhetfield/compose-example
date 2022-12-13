@file:OptIn(ExperimentalMaterialApi::class)

package com.example.cursojetpackcompose.home

import android.media.ImageReader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cursojetpackcompose.model.Item
import com.example.cursojetpackcompose.model.itemList

enum class Action(val text: String, val alert: Boolean = false) { CLONE("Clone"), DELETE("Delete", true) }

@Composable
fun Home(vm: HomeViewModel = viewModel()) {
//    HomeList(vm.state, onActionClicked = vm::actionClick)
    HomeGrid(vm.state, onActionClicked = vm::actionClick)
}

@Composable
fun HomeList(state: List<Item>, onActionClicked: (Action, Int) -> Unit) {

    LazyColumn {
        itemsIndexed(state) { index, item ->

            if (index != 0)
                Divider()

            ItemList(
                item = item,
                onActionClick = onActionClicked,
                index = index
            )
        }
    }
}

@Composable
fun HomeGrid(state: List<Item>, onActionClicked: (Action, Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(state) { index, item ->
            Column {
                ItemGrid(
                    item = item,
                    onActionClick = onActionClicked,
                    index = index
                )
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(text = item.title, modifier = Modifier.weight(1f).padding(8.dp))
                    MoreActionsIcon(onActionClick = { action -> onActionClicked(action, index) })
                }
            }
        }
    }
}

@Composable
fun ItemList(item: Item, onActionClick: (Action, Int) -> Unit, index: Int) {
    ListItem(
        text = { Text(text = item.title) },
        secondaryText = { Text(text = item.subtitle) },
        icon = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumb)
                    .crossfade(true)
                    .build(),
                contentDescription = item.title,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        },
        trailing = {
            MoreActionsIcon { action ->
                onActionClick(action, index)
            }

        }
    )

}

@Composable
fun ItemGrid(item: Item, onActionClick: (Action, Int) -> Unit, index: Int) {
    Column {
        AsyncImage(
            model = item.thumb,
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
    }


}

@Composable
private fun MoreActionsIcon(onActionClick: (Action) -> Unit) {
    var dropdowVisible by rememberSaveable { mutableStateOf(false) }
    IconButton(onClick = { dropdowVisible = true }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Actions")
        DropdownMenu(expanded = dropdowVisible, onDismissRequest = { dropdowVisible = false }) {
            Action.values().forEach { action ->
                DropdownMenuItem(onClick = {
                    dropdowVisible = false
                    onActionClick(action)
                }) {
                    Text(text = action.text, color = if (action.alert) Color.Red else Color.Unspecified)
                }
            }
        }
    }
}