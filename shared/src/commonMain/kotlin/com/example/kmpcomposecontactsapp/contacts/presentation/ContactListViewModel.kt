package com.example.kmpcomposecontactsapp.contacts.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactListViewModel : ViewModel() {

    private val _state = MutableStateFlow(ContactListState(contacts = contacts))
    val state = _state.asStateFlow()

    var newContact: ContactItem? by mutableStateOf(null)
        private set

    fun onEvent(event: ContactListEvent) {

    }
}

private val contacts = (1..50).map {
    ContactItem(
        id = it.toLong(),
        firstName = "First$it",
        lastName = "Last$it",
        email = "test$it@gmail.com",
        phoneNumber = "123456679",
        photoBytes = null
    )
}