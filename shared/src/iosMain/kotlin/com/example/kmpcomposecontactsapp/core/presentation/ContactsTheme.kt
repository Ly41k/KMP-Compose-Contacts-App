package com.example.kmpcomposecontactsapp.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.kmpcomposecontactsapp.ui.theme.DarkColorScheme
import com.example.kmpcomposecontactsapp.ui.theme.LightColorScheme
import com.example.kmpcomposecontactsapp.ui.theme.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}