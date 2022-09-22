package com.aej.efisheryandroidassessment.presentation.item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aej.efisheryandroidassessment.databinding.FishItemBinding
import com.aej.efisheryandroidassessment.presentation.FishUiModel
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class FishItem(private val fishUiModel: FishUiModel? = null) : AbstractBindingItem<FishItemBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): FishItemBinding {
        return FishItemBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: FishItemBinding, payloads: List<Any>) {
        binding.run {
            commodityTextView.text = "Komoditas ${fishUiModel?.commodity}"
            provinceTextView.text = "${fishUiModel?.provinceArea}"
            cityTextView.text = "${fishUiModel?.cityArea}"
            sizeTextView.text = "Jumlah: ${fishUiModel?.size}"
            priceTextView.text = "Rp${fishUiModel?.price}"
        }
    }

}
