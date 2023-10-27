package com.example.kmpcomposecontactsapp.contacts.domain

data class ContactItem(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val photoBytes: ByteArray?
) {
    fun getFullName(): String = "$firstName $lastName"
}