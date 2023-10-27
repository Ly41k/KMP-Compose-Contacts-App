package com.example.kmpcomposecontactsapp.contacts.presentation

import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem

sealed interface ContactListEvent {
    data object OnAddNewContactClick : ContactListEvent
    data object DismissContact : ContactListEvent
    data class OnFirstNameChanged(val value: String) : ContactListEvent
    data class OnLastNameChanged(val value: String) : ContactListEvent
    data class OnEmailChanged(val value: String) : ContactListEvent
    data class OnPhoneNumberChanged(val value: String) : ContactListEvent
    class OnPhotoPicked(val bytes: ByteArray) : ContactListEvent
    data object OnAddPhotoClicked : ContactListEvent
    data object SaveContact : ContactListEvent
    data class SelectContact(val contact: ContactItem) : ContactListEvent
    data class EditContact(val contact: ContactItem) : ContactListEvent
    data object DeleteContact : ContactListEvent
}