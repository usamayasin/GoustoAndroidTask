package com.app.goustotask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.app.goustotask.R
import com.app.goustotask.data.remote.DataState
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _bi: VB? = null
    protected val binding: VB get() = _bi!!
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bi = bindingInflater(inflater, container, false)
        return _bi!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bi = null
    }

    fun showSnackbar(message: DataState.CustomMessages, binding: View) {
        val error = when (message) {
            is DataState.CustomMessages.EmptyData -> {
                getString(R.string.no_data_found)
            }
            is DataState.CustomMessages.Timeout -> {
                getString(R.string.timeout)
            }
            is DataState.CustomMessages.ServerBusy -> {
                getString(R.string.server_is_busy)
            }
            is DataState.CustomMessages.HttpException -> {
                getString(R.string.no_internet_connection)
            }
            is DataState.CustomMessages.SocketTimeOutException -> {
                getString(R.string.no_internet_connection)
            }
            is DataState.CustomMessages.NoInternet -> {
                getString(R.string.no_internet_connection)
            }
            is DataState.CustomMessages.Unauthorized -> {
                getString(R.string.unauthorized)
            }
            is DataState.CustomMessages.InternalServerError -> {
                getString(R.string.internal_server_error)
            }
            is DataState.CustomMessages.BadRequest -> {
                getString(R.string.bad_request)
            }
            is DataState.CustomMessages.Conflict -> {
                getString(R.string.confirm)
            }
            is DataState.CustomMessages.NotFound -> {
                getString(R.string.not_found)
            }
            is DataState.CustomMessages.NotAcceptable -> {
                getString(R.string.not_acceptable)
            }
            is DataState.CustomMessages.ServiceUnavailable -> {
                getString(R.string.service_unavailable)
            }
            is DataState.CustomMessages.Forbidden -> {
                getString(R.string.forbidden)
            }
            else -> {
                getString(R.string.something_went_wrong)
            }
        }

        Snackbar.make(binding.rootView, error, Snackbar.LENGTH_LONG)
            .setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white)).also {
                it.setAction(
                    "OK"
                ) { v ->
                    it.dismiss()
                }
            }
            .show()
    }
}
