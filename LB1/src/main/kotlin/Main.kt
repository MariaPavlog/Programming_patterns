package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val students = mutableListOf(

        Student(mapOf(
            "ID" to 1,
            "name" to "Ivan",
            "surname" to "Ivanovich",
            "secondname" to "Ivanov"
        )),
        Student(mapOf(
            "ID" to 2,
            "name" to "Jack",
            "surname" to "Jackov",
            "secondname" to "Jacksov",
            "phone" to "+79528459854",
            "telegram" to "@chips_the_unlimited",
            "email" to "jack999@mail.com",
            "git" to "https://github.com/jack999"
        )),
        Student(mapOf(
            "ID" to 3,
            "name" to "Ann",
            "surname" to "Anny",
            "secondname" to "Annovich",
            "telegram" to "@the_anna",
            "git" to "https://github.com/ann300"
        )),
        Student(mapOf(
            "ID" to 4,
            "name" to "Are",
            "surname" to "Who",
            "secondname" to "You",
            "git" to "https://gitlab.com/unknown"
        )),
        Student(mapOf(
            "ID" to 5,
            "name" to "Anastasia",
            "surname" to "Frolova",
            "secondname" to "Aleksandrovna",
            "email" to "blumwinx2000@mail.com"
        )),
    )
    students.forEach { it.show() }
}