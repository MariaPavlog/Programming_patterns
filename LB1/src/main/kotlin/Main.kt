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
            "email" to "lol2000@mail.com"
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
fun FileTestLab2() {
    val studList = Student.readFromTxt("C://Users//HP//IdeaProjects//Programming_patterns//LB1//src//main//kotlin//testfile_lab2.txt")
    studList.forEach { println(it.getInfoSt()) }
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
    students.forEach { println(it.getInfoSt())}
    val StudentsSHORT = mutableListOf(
        StudentShort(students[0]),
        StudentShort(students[1]),
        StudentShort(students[2]),
        StudentShort(students[3]),
        StudentShort(students[4]),
        StudentShort(6, "Студент Дыня О., git не указан, контакты не указаны"),
        StudentShort(7, "Студент Иванов Н. Н., git: https://github.com/ivan, связаться можно по номеру телефона: +79528459855")
    )
    StudentsSHORT.forEach { it.show() }
    print("\nEx 7:\n")
    FileTestLab2()
}