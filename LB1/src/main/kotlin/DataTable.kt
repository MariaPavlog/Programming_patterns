package org.example

class DataTable(private val array: List<List<Any?>>){
    operator fun get(row: Int, col: Int) = array[row][col]
fun getRowAmount() = array.size
fun getColAmount() = if (array.isNotEmpty()) array[0].size else 0
}