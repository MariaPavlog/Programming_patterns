package org.example

class DataListStudentShort(studentShortArray: List<StudentShort>) : DataList(studentShortArray) {
    override fun getNames() = listOf("ID", "ФИО", "Git", "Контакт")
    override fun getData() = DataTable(buildList {
        for (index in array.indices) {
            select(index) // Выбираем каждый индекс, чтобы не получить пустой список
        }
        var count = 1

        for (index in getSelected()) {

            val student = array[index] as StudentShort
            add(listOf(count++, student.surnameWithInitials, student.git, student.contact))
        }
    })
}