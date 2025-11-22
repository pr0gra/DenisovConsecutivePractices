package com.oolexander.rickandmorty.presentation.screen.profile

import android.app.DownloadManager
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.oolexander.rickandmorty.R
import com.oolexander.rickandmorty.domain.model.CharacterStatus
import com.oolexander.rickandmorty.presentation.screen.common.showToast
import com.oolexander.rickandmorty.presentation.ui_kit.view.Avatar
import com.oolexander.rickandmorty.presentation.ui_kit.view.ButtonView
import androidx.core.net.toUri

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: UserInfoViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Avatar(
            model = state.photoUri.ifEmpty { R.drawable.default_avatar },
            characterStatus = CharacterStatus.ALIVE,
        )

        TextColumn(state.username, state.post)

        ButtonView(
            text = "Скачать резюме",
            onClick = {
                val url = state.resumeUrl.trim()

                if (url.isBlank()) {
                    context.showToast("Ссылка на резюме не указана")
                    return@ButtonView
                }

                try {
                    val uri = url.toUri()
                    val request = DownloadManager.Request(uri)
                        .setTitle("Скачивание резюме")
                        .setDescription(uri.lastPathSegment ?: "Файл")
                        .setNotificationVisibility(
                            DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
                        )
                        .setAllowedOverMetered(true)
                        .setAllowedOverRoaming(true)

                    val manager = context.getSystemService(DownloadManager::class.java)
                    manager.enqueue(request)

                    context.showToast("Загрузка началась…")
                } catch (_: Exception) {
                    context.showToast("Что-то пошло не так")
                }
            },
        )
    }
}

@Composable
private fun TextColumn(username: String, post: String) {
    Text(
        text = username.ifEmpty { "Имя не указано" },
        fontSize = 22.sp,
        color = Color.White,
        modifier = Modifier.padding(top = 8.dp)
    )

    Text(
        text = post.ifEmpty { "Должность не указана" },
        fontSize = 18.sp,
        color = Color.Gray,
        modifier = Modifier.padding(top = 8.dp)
    )
}