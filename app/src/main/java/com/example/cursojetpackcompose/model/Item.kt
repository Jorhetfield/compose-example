package com.example.cursojetpackcompose.model

data class Item(
    val id: Int,
    val title: String,
    val subtitle: String,
    val thumb: String
)
val itemList = (1..1000).map { id ->    Item(
    id = id,
    title = "Title $id",
    subtitle = "Subtitle $id",
    thumb = "https://loremflickr.com/400/400?lock=$id"
)
}