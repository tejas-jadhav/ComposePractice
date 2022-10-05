package com.example.composepractice.data.model

data class PracticeItem(
    val name: String,
    val description: String,
    val practice: Practice,
    val orderNumber: Int
): java.io.Serializable {

}
