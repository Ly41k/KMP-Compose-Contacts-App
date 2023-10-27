package com.example.kmpcomposecontactsapp.contacts.data

import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem
import database.ContactEntity

fun ContactEntity.toContactItem(): ContactItem = ContactItem(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phoneNumber = phoneNubmer,
    photoBytes = null
)