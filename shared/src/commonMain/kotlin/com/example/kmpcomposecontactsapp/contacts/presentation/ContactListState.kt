package com.example.kmpcomposecontactsapp.contacts.presentation

import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem

data class ContactListState(
    val contacts: List<ContactItem> = emptyList(),
    val recentlyAddedContacts: List<ContactItem> = emptyList(),
    val selectedContact: ContactItem? = null,
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
)