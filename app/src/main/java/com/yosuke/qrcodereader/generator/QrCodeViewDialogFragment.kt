package com.yosuke.qrcodereader.generator

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yosuke.qrcodereader.databinding.DialogFragmentQrcodeViewBinding
import dagger.android.support.DaggerAppCompatDialogFragment
import javax.inject.Inject

class QrCodeViewDialogFragment : DaggerAppCompatDialogFragment() {

    lateinit var binding: DialogFragmentQrcodeViewBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogFragmentQrcodeViewBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(GeneratorViewModel::class.java)
        binding.viewModel = viewModel

        isCancelable = true

        return binding.root
    }

    companion object {
        val FRAGMENT_TAG = QrCodeViewDialogFragment::class.java.simpleName

        fun newInstance() = QrCodeViewDialogFragment()
    }

}