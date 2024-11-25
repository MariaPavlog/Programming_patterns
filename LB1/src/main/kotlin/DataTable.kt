package org.example

class DataTable(private val array: Array<Array<Any>>){
fun get(row: Int, am: Int) = array[row][am]
fun getRowAmount() = array.size
fun getColAmount() = if (array.isNotEmpty()) array[0].size else 0
}