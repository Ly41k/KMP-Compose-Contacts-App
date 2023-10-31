package com.example.kmpcomposecontactsapp.contacts.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem
import com.example.kmpcomposecontactsapp.contacts.presentation.ContactListEvent
import com.example.kmpcomposecontactsapp.contacts.presentation.ContactListState
import com.example.kmpcomposecontactsapp.core.presentation.BottomSheetFromWish

@Composable
fun AddContactSheet(
    state: ContactListState,
    newContact: ContactItem?,
    isOpen: Boolean,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheetFromWish(
        visible = isOpen,
        modifier = modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                if (newContact?.photoBytes == null) {
                    Box(modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(40))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable { onEvent(ContactListEvent.OnAddPhotoClicked) }
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            shape = RoundedCornerShape(40)
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                } else {
                    ContactPhoto(
                        contact = newContact,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { onEvent(ContactListEvent.OnAddPhotoClicked) }
                    )
                }
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.firstName.orEmpty(),
                    placeholder = "First name",
                    error = state.firstNameError,
                    onValueChanged = { onEvent(ContactListEvent.OnFirstNameChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.lastName.orEmpty(),
                    placeholder = "Last name",
                    error = state.lastNameError,
                    onValueChanged = { onEvent(ContactListEvent.OnLastNameChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.email.orEmpty(),
                    placeholder = "Email",
                    error = state.emailError,
                    onValueChanged = { onEvent(ContactListEvent.OnEmailChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                ContactTextField(
                    value = newContact?.phoneNumber.orEmpty(),
                    placeholder = "Phone number",
                    error = state.phoneNumberError,
                    onValueChanged = { onEvent(ContactListEvent.OnPhoneNumberChanged(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { onEvent(ContactListEvent.SaveContact) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(text = "Save") }
            }
            IconButton(onClick = { onEvent(ContactListEvent.DismissContact) }) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = "Close")
            }
        }
    }
}

@Composable
private fun ContactTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            placeholder = { Text(text = placeholder) },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        error?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
    }
}