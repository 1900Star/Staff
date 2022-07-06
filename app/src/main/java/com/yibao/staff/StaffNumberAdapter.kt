package com.yibao.staff

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yibao.staff.base.BaseBindingAdapter
import com.yibao.staff.databinding.ItemStaffNumberBinding

/**
 * @author  luoshipeng
 * createDate：2021/12/16 0016 14:25
 * className   LuxAdapter
 * Des：TODO
 */
class StaffNumberAdapter(
    val type: Int,
    planList: List<StaffBean>
) : BaseBindingAdapter<StaffBean>(planList as MutableList<StaffBean>) {
    override fun bindView(
        holder: RecyclerView.ViewHolder,
        bean: StaffBean
    ) {
        if (holder is LuxHolder) {
            when (type) {
                1 -> {
                    holder.viewBinding.tvStaffNumber.text = bean.number

                }
                2 -> {
                    holder.viewBinding.tvStaffNumber.text = bean.name

                }
                3 -> {
                    holder.viewBinding.tvStaffNumber.text = bean.letter

                }
            }
            holder.viewBinding.root.setOnClickListener { openDetails(bean, holder.adapterPosition) }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LuxHolder {
        val binding =
            ItemStaffNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LuxHolder(binding)
    }

    inner class LuxHolder(var viewBinding: ItemStaffNumberBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

}