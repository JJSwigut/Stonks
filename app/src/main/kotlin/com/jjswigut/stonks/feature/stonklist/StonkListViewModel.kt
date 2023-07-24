package com.jjswigut.stonks.feature.stonklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjswigut.stonks.data.models.Stonk
import com.jjswigut.stonks.data.network.ktor.StonkServiceError
import com.jjswigut.stonks.data.network.utils.process
import com.jjswigut.stonks.dispatchers.Dispatcher
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel.StonkListAction.RefreshStonks
import com.jjswigut.stonks.repository.StonkRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * A ViewModel class that provides UI data to the StonkList screen.
 *
 * @param stonkRepository An instance of the StonkRepository for fetching stonks.
 * @param dispatcher An instance of the Dispatcher for managing coroutines.
 */
class StonkListViewModel(
    private val stonkRepository: StonkRepository,
    private val dispatcher: Dispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(StonkListViewState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<StonkListEffect>()
    val effect: SharedFlow<StonkListEffect> get() = _effect

    private fun handleAction(action: StonkListAction) {
        when (action) {
            is RefreshStonks -> getStonks()
        }
    }

    private fun getStonks() {
        viewModelScope.launch(dispatcher.io) {
            showLoader(true)

            stonkRepository.getStonks().process(
                onSuccess = { stonks ->
                    showLoader(false)
                    updateStonks(stonks)
                },
                onError = { error ->
                    setEffect(StonkListEffect.ShowError(error))
                    showLoader(false)
                }
            )
        }
    }

    private fun showLoader(show: Boolean) {
        _state.update { it.copy(isLoading = show) }
    }

    private fun updateStonks(stonks: List<Stonk>) {
        _state.update { it.copy(stonks = stonks) }
    }

    fun setAction(action: StonkListAction) {
        handleAction(action)
    }

    private fun setEffect(effect: StonkListEffect) {
        viewModelScope.launch(dispatcher.io) {
            _effect.emit(effect)
        }
    }

    data class StonkListViewState(
        val isLoading: Boolean = true,
        val stonks: List<Stonk> = emptyList()
    )

    sealed interface StonkListAction {
        object RefreshStonks : StonkListAction
    }

    sealed interface StonkListEffect {
        data class ShowError(val error: StonkServiceError) : StonkListEffect
    }
}
