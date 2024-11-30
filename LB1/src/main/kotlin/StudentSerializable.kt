package org.example

import kotlinx.serialization.Serializable

@Serializable
data class StudentSerializable(val id: Int,
                               val surname: String,
                               val name: String,
                               val secondname: String,
                               val phone: String? = null,
                               val telegram: String? = null,
                               val email: String? = null,
                               val git: String? = null) {
    constructor(student: Student) : this(student.id,
        student.surname,
        student.name,
        student.secondname,
        student.phone,
        student.telegram,
        student.email,
        student.git)
}