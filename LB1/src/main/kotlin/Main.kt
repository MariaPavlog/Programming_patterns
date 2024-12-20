package org.example

fun testStudentListDB() {
    val students = StudentListDB()
    Database.connect()
    println(students.getStudentById(1)?.toStringRow())
    println(students.getStudentById(0))
    println()
    printDataTable(students.getStudentShortList(3, 2).getData())
    println()
    printDataTable(students.getStudentShortList(2, 4).getData())
    println()
    println(students.getStudentShortCount())
    students.add(Student(0, "Новый", "Студент", "Хе-хе"))
    println(students.getStudentShortCount())
    println(students.getStudentById(8)?.toStringRow())
    println(students.getStudentById(9)?.toStringRow())
    students.remove(7)
    println(students.remove(5))
    students.add(Student(0, "Ещё", "Студент", "", email = "123@456.789"))
    students.replace(8, Student(0, "Изменённый", "Студент", "", telegram = "@skullemoji"))
    println(students.remove(0))
    println(students.remove(100))
}
fun main() {

    testStudentListDB()
//    val students = mutableListOf(
//
//        Student(mapOf(
//            "ID" to 1,
//            "name" to "Ivan",
//            "surname" to "Ivanovich",
//            "secondname" to "Ivanov"
//        )),
//        Student(mapOf(
//            "ID" to 2,
//            "name" to "Jack",
//            "surname" to "Jackov",
//            "secondname" to "Jacksov",
//            "phone" to "+79528459854",
//            "telegram" to "@chips_the_unlimited",
//            "email" to "jack999@mail.com",
//            "git" to "https://github.com/jack999"
//        )),
//        Student(mapOf(
//            "ID" to 3,
//            "name" to "Ann",
//            "surname" to "Anny",
//            "secondname" to "Annovich",
//            "telegram" to "@the_anna",
//            "git" to "ann300"
//        )),
//        Student(mapOf(
//            "ID" to 4,
//            "name" to "Are",
//            "surname" to "Who",
//            "secondname" to "You",
//            "git" to "https://gitlab.com/unknown"
//        )),
//        Student(mapOf(
//            "ID" to 5,
//            "name" to "Maria",
//            "surname" to "Pavlogradskaya",
//            "secondname" to "Aleksandrovna",
//            "email" to "blumwinx2000@mail.com"
//        )),
//        Student(mapOf(
//            "ID" to 6,
//            "name" to "Irina",
//            "surname" to "IX",
//            "secondname" to "no secondname",
//            "email" to "lol2000@mail.com"
//        )),
//    )
    //println("\nLab 1 results:\n")
    //students.forEach { it.show() }
    //students.forEach { it.anyGit() }
    //students.forEach { it.anyContact() }
    //students[1].setContacts(mapOf("telegram" to null, "email" to "newemailcom.com"))
    //students[1].show()
    //println("\nLab 2 results:\n")
    //lab2()
    //lab2part2()
    //lab3TestGettingStudents()
    //lab3TestJSON()
    //lab3TestYAML()
    //lab3Test()
}
//fun FileTestLab2() {
//    //read
//    val studList = Student.readFromTxt("C://Users//HP//IdeaProjects//Programming_patterns//LB1//src//main//kotlin//testfile_lab2.txt")
//    studList.forEach { println(it.getInfoSt()) }
//    //write to
//    Student.writeToTxt("C://Users//HP//IdeaProjects//Programming_patterns//LB1//src//main//kotlin//testfileOUT_lab2.txt", studList)
//    val studList2 = Student.readFromTxt("C://Users//HP//IdeaProjects//Programming_patterns//LB1//src//main//kotlin//testfile_lab2.txt")
//    println()
//    studList2.forEach { println(it.getInfoSt()) }
//    require(studList2.toString() == studList.toString())
//}
//fun lab2() {
//    val students = mutableListOf(
//        Student("1,Павлоградская,Мария,Александровна,,,,"),
//        Student("2,Фамилия,Имя,Отчество,+79528459854,@mail_heck,ex@example.com,https://github.com/git_check"),
//        Student("3,Иванов,Иван,Иванович,,@ivan2002,,https://github.com/ivan_200002"),
//        Student("4,Surname,Name,SecondName,,,,https://gitlab.com/user"),
//        Student("5,Вишня,Олег,Петросович,,,cherry@mail.com,")
//        //Student("5,Вишня,Олег,Петросович,,,cherry@mail.com,"),
//
//    )
//
//    students.forEach { it.show() }
//    students.forEach { println(it.getInfoSt())}
//    val StudentsSHORT = mutableListOf(
//        StudentShort(students[0]),
//        StudentShort(students[1]),
//        StudentShort(students[2]),
//        StudentShort(students[3]),
//        StudentShort(students[4]),
//        StudentShort(6, "Студент Дыня О., git не указан, контакты не указаны"),
//        StudentShort(7, "Студент Иванов Н. Н., git: https://github.com/ivan, связаться можно по номеру телефона: +79528459855")
//    )
//    StudentsSHORT.forEach { it.show() }
//    print("\nEx 7:\n")
//    FileTestLab2()
//}
//fun lab2part2() {
//    val studList = Student.readFromTxt("C://Users//HP//IdeaProjects//Programming_patterns//LB1//src//main//kotlin//testfile_lab2.txt").map { StudentShort(it) }
//    studList.forEach { it.show() }
//
//    val dataList = DataListStudentShort(studList)
//    val names = dataList.getNames()
//    println(names)
//    dataList.select(0)
//    dataList.select(2)
//    val dataTable = dataList.getData()
//    for (i in 0..<dataTable.getRowCount()) {
//        for (j in 0..<dataTable.getColCount()) {
//            print("${dataTable[i, j]} ")
//        }
//        println()
//    }
//}

fun printDataTable(dataTable: DataTable) {

    for (i in 0..<dataTable.getRowCount()) {
        for (j in 0..<dataTable.getColCount()) {
            print("${dataTable[i, j]} ")

        }
        println()
    }}
fun lab3Test() {
    // TXT

    val students = StudentList(TXTFormatStrategy())
    students.load("C://Users//HP//IdeaProjects//Programming_patterns//LB1//src//main//kotlin//testfile_lab2.txt")
    println(students.getStudentShortCount())
    for (id in 1..4) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
    println()
    printDataTable(students.getStudentShortList(1, 3).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(1, 1).getData())
    println("=========================")
//    printDataTable(students.getStudentShortList(0, 3).getData())
//    println("=========================")
//    printDataTable(students.getStudentShortList(1, -1).getData())
//    println("=========================")
    printDataTable(students.getStudentShortList(2, 100).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(3, 1).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(2, 3).getData())

    println()
    students.sortByStudentName()
    printDataTable(students.getStudentShortList(1, 4).getData())

    println()
    students.add(Student(0, "Новый", "Студент", "Хе-хе"))
    printDataTable(students.getStudentShortList(1, 100).getData())
    println()
    println(students.getStudentById(3).toStringRow())
    students.replace(3, Student(0, "Изменённый", "Студент", "Хе-хе"))
    println(students.getStudentById(3).toStringRow())
    println()
    students.remove(3)
    for (id in 1..5) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
    println()
    students.add(Student(0, "Вернувшийся", "Студент", "Хе-хе"))
    students.add(Student(0, "Студент", "Номер", "Восемь"))
    for (id in 1..7) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            println(students.getStudentShortCount())
            students.save("lab3_output.txt")
        }}

//fun lab3TestJSON() {
    // val jsonObject = JsonObject(mapOf("id" to JsonPrimitive(1), "surname" to JsonPrimitive("Эзри")))

    //val students = StudentListJSON()
    students.add(Student(123, "Новый", "Студент", "Хе-хе"))
    students.remove(1)
    students.add(Student(mapOf("ID" to 100, "surname" to "Павлогрдаская", "name" to "Мария", "secondname" to "Александровна")))
    students.add(Student(mapOf("ID" to 101, "surname" to "Тестов", "name" to "Тест", "secondname" to "Тестович",
        "phone" to "+79876543210", "telegram" to "@test123", "email" to "test@example.com",
        "git" to "https://github.com/test123")))
    students.add(Student(mapOf("ID" to 102, "surname" to "Фамилия", "name" to "Имя", "secondname" to "",
        "telegram" to "@familiyaimya", "git" to "https://github.com/familiyaimya")))
    students.add(Student(123, "Новый", "Студент", "Студентович"))
    students.remove(5)
    students.add(Student(123, "Новый", "Студент", "Студентович"))
students.formatStrategy = JSONFormatStrategy()
    students.save("lab3_output.json")

    val students2 = StudentList(JSONFormatStrategy())
    students2.load("lab3_output.json")
    println(students.getStudentShortCount())
    for (id in 1..6) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
//}
//fun lab3TestYAML() {
    // val jsonObject = JsonObject(mapOf("id" to JsonPrimitive(1), "surname" to JsonPrimitive("Эзри")))

    //val students = StudentListYAML()
    students.add(Student(123, "Новый", "Студент", "Студентович"))
    students.remove(2)
    students.add(Student(mapOf("ID" to 100, "surname" to "Павлоградская", "name" to "Мария", "secondname" to "Александровна")))
    students.add(Student(mapOf("ID" to 101, "surname" to "Тестов", "name" to "Тест", "secondname" to "Тестович",
        "phone" to "+79876543210", "telegram" to "@test123", "email" to "test@example.com",
        "git" to "https://github.com/test123")))
    students.add(Student(mapOf("ID" to 102, "surname" to "Фамилия", "name" to "Имя", "secondname" to "",
        "telegram" to "@familiyaimya", "git" to "https://github.com/familiyaimya")))
    students.add(Student(123, "Новый", "Студент", "Студентович"))
    students.remove(6)
    students.add(Student(123, "Новый", "Студент", "Студентович"))
    students.formatStrategy = YAMLFormatStrategy()
    students.save("lab3_output.yaml")

    students2.formatStrategy = YAMLFormatStrategy()
    students2.load("lab3_output.yaml")
    println(students.getStudentShortCount())
    for (id in 1..6) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}
