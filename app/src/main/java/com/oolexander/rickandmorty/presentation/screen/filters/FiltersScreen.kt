package com.oolexander.rickandmorty.presentation.screen.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.oolexander.rickandmorty.domain.model.CharacterGender
import com.oolexander.rickandmorty.domain.model.CharacterStatus
import com.oolexander.rickandmorty.presentation.screen.filters.view.EnumDropdownField

@Composable
fun FiltersScreen(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FiltersViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1C29),
                        Color(0xFF0F1123),
                    )
                )
            )
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when {
            state.showLoading -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator(color = Color(0xFF97CE4C))
                }
            }

            state.showError -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Ошибка: ${state.error}",
                        color = Color(0xFFE84C5C),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            state.showContent -> {
                EnumDropdownField(
                    label = "Статус",
                    value = state.status,
                    allItems = CharacterStatus.entries.toList(),
                    itemLabel = { it.rusName },
                    onSelected = { viewModel.onStatusSelected(it) },
                    modifier = Modifier.fillMaxWidth(),
                )

                EnumDropdownField(
                    label = "Пол",
                    value = state.gender,
                    allItems = CharacterGender.entries.toList(),
                    itemLabel = { it.rusName },
                    onSelected = { viewModel.onGenderSelected(it) },
                    modifier = Modifier.fillMaxWidth(),
                )

                OutlinedTextField(
                    value = state.species,
                    onValueChange = { viewModel.onSpeciesChanged(it) },
                    label = { Text("Вид (species)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Button(
                        onClick = { viewModel.onReset() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2D3047),
                            contentColor = Color.White,
                        ),
                    ) {
                        Text("Сбросить фильтры")
                    }

                    Button(
                        onClick = {
                            viewModel.saveAndReturnFilters()
                            onDone()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF97CE4C),
                            contentColor = Color.Black,
                        ),
                    ) {
                        Text("Сохранить")
                    }
                }
            }
        }
    }
}