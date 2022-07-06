package com.yibao.staff.base

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.yibao.staff.R
import java.util.*

/**
 * @author  luoshipeng
 * createDate：2021/6/29 0029 15:55
 * className   BaseBindingActivity
 * Des：TODO
 */
abstract class BaseBindingListActivity<T : ViewBinding> : BaseBindingActivity<T>(){




    protected open fun initRecyclerView(recyclerView: RecyclerView, type: Int) {
        val manager = LinearLayoutManager(this)
        manager.orientation = type
        recyclerView.layoutManager = manager
        recyclerView.isVerticalScrollBarEnabled = true
        if (type == LinearLayoutManager.VERTICAL) {
            val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            Objects.requireNonNull(
                ContextCompat.getDrawable(this, R.drawable.shape_item_decoration)
            )?.let { divider.setDrawable(it) }
            recyclerView.addItemDecoration(divider)
        }
    }

    protected open fun initRecyclerViewNormal(recyclerView: RecyclerView, type: Int) {
        val manager = LinearLayoutManager(this)
        manager.orientation = type
        recyclerView.layoutManager = manager
        recyclerView.isVerticalScrollBarEnabled = true
    }


    protected open fun initRecyclerGrid(recyclerView: RecyclerView, column: Int) {
        val manager = GridLayoutManager(this, column)
        recyclerView.layoutManager = manager
        recyclerView.isHorizontalScrollBarEnabled = true
    }

    /**
     * 处理下拉刷新和recyclerView的冲突
     */
    protected open fun fixRvSwConflict(
        recyclerView: RecyclerView,
        swipeRefreshLayout: SwipeRefreshLayout
    ) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val topPosition =
                    if (recyclerView.childCount == 0) 0 else recyclerView.getChildAt(
                        0
                    ).top
                swipeRefreshLayout.isEnabled = topPosition >= 0
            }
        })
    }

}