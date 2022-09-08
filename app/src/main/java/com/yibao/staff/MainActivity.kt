package com.yibao.staff

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.yibao.staff.base.BaseBindingAdapter
import com.yibao.staff.base.BaseBindingListActivity
import com.yibao.staff.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : BaseBindingListActivity<ActivityMainBinding>() {


    override fun initView() {
        initRecyclerGrid(mBinding.recyclerName, 7)
        initRecyclerGrid(mBinding.recyclerNumber, 7)
        initRecyclerGrid(mBinding.recyclerLetter, 7)

    }

    override fun initData() {
        mBinding.tvContent.text = DataUtil().getContent()
        val staffList = DataUtil().staffList()
        val numberAdapter = StaffNumberAdapter(1, staffList)
        val nameAdapter = StaffNumberAdapter(2, staffList)
        val letterAdapter = StaffNumberAdapter(3, staffList)
        mBinding.recyclerNumber.adapter = numberAdapter
        mBinding.recyclerLetter.adapter = letterAdapter
        mBinding.recyclerName.adapter = nameAdapter

        numberAdapter.setItemListener(object : BaseBindingAdapter.OnItemListener<StaffBean> {
            override fun showDetailsView(bean: StaffBean, position: Int) {
                val content = mBinding.tvContent.text
                if (content.equals(bean.number) || content.equals(bean.name) || content.equals(bean.letter)) {
                    Snackbar.make(mBinding.btnNext, "回答正确", 500).show()
                } else {
                    Snackbar.make(mBinding.btnNext, "回答错误", 500).show()

                }
            }
        })
        nameAdapter.setItemListener(object : BaseBindingAdapter.OnItemListener<StaffBean> {
            override fun showDetailsView(bean: StaffBean, position: Int) {
                val content = mBinding.tvContent.text
                if (content.equals(bean.number) || content.equals(bean.name) || content.equals(bean.letter)) {
                    Snackbar.make(mBinding.btnNext, "回答正确", 600).show()
                } else {
                    Snackbar.make(mBinding.btnNext, "回答错误", 600).show()

                }
            }
        })
        letterAdapter.setItemListener(object : BaseBindingAdapter.OnItemListener<StaffBean> {
            override fun showDetailsView(bean: StaffBean, position: Int) {
                val content = mBinding.tvContent.text
                if (content.equals(bean.number) || content.equals(bean.name) || content.equals(bean.letter)) {
                    Snackbar.make(mBinding.btnNext, "回答正确", 500).show()
                } else {
                    Snackbar.make(mBinding.btnNext, "回答错误", 500).show()

                }
            }
        })

    }

    override fun initListener() {
        mBinding.btnNext.setOnClickListener {
            mBinding.tvContent.text = DataUtil().getContent()
        }
    }

}