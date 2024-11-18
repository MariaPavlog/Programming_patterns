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
            "git" to "ann300"
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
        Student(mapOf(
            "ID" to 6,
            "name" to "Irina",
            "surname" to "IX",
            "secondname" to "no secondname",
            "email" to "lol2000@mail.com",
            "phone" to "lol"
        )),
    )
    println("\nLab 1 results:\n")
    students.forEach { it.show() }
    students.forEach { it.anyGit() }
    students.forEach { it.anyContact() }
    students[1].setContacts(mapOf("telegram" to null, "email" to "newemail@com.com"))
    students[1].show()
    println("\nLab 2 results:\n")
    lab2()
}
fun lab2() {
    val students = mutableListOf(
        Student("1,Павлоградская,Мария,Александровна,,,,"),
        Student("2,Фамилия,Имя,Отчество,+79528459854,@mail_heck,ex@example.com,https://github.com/git_check"),
        Student("3,Иванов,Иван,Иванович,,@ivan2002,,https://github.com/ivan_200002"),
        Student("4,Surname,Name,SecondName,,,,https://gitlab.com/user"),
        Student("5,Вишня,Олег,Петросович,,,cherry@mail.com,")
        //Student("5,Вишня,Олег,Петросович,,,cherry@mail.com,"),

    )

    students.forEach { it.show() }
}