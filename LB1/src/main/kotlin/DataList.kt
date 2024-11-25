package org.example

abstract class DataList(private val array: List<Any>) {
    private val selectedIndices: MutableList<Int> = mutableListOf()
    fun getSelected() = selectedIndices.toList()
    abstract fun getNames()
    abstract fun getData()
}