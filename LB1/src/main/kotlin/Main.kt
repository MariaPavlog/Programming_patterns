package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val students = mutableListOf<Student>()
    students.add(
        Student(
            1,
            "Ivan",
            "Ivanov",
            "Ivanovich"
        )
    )
    students.add(
        Student(
            2,
            "Jack",
            "Jacsov",
            "Jacsovich",
            "+79528459854",
            "@chips_the_unlimited",
            "jack999@mail.com",
            "https://github.com/jack999")
    )
    students.add(
        Student(
            3,
            "Ann",
            "Anny",
            "Annovich",
            telegram="@the_anna",
            git="https://github.com/ann3000"))
    students.add(
        Student(
            4,
            "Anastasia",
            "Frolova",
            "Alexsandrovna",
            git="https://gitlab.com/anastasiafrolova2002"
        )
    )
    students.add(
        Student(
            5,
            "Maria",
            "Pavlogradskaya",
            "Alexsandrovna",
            email="maria2003@mail.com"
        )
    )
    students.forEach { it.show() }
}