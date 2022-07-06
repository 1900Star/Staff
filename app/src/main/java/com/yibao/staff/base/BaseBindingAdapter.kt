package com.yibao.staff.base

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yibao.staff.GridItemDecoration
import com.yibao.staff.R

/**
 * @author  luoshipeng
 * createDate：2021/6/22 0022 10:14
 * className   FixRecordAdapter
 * Des：TODO
 */
abstract class BaseBindingAdapter<T>(var dataList: MutableList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected val mTAG = " ==== " + this::class.java.simpleName + "  "
    private var mListener: OnItemListener<T>? = null
    private var mLongListener: OnItemLongListener<T>? = null

    private lateinit var mDeleteListener: OnItemDeleteListener<T>
    protected val TYPE_ITEM = 0
    protected val TYPE_FOOTER = 1

    //记录当前选中的条目索引
    protected var selectedIndex = 0

    fun setSelectedPosition(position: Int) {
        selectedIndex = position
        notifyDataSetChanged()
    }

    // 接触器背景色
    fun getBgColor(position: Int): Int {
        return if (selectedIndex == position) Color.rgb(
            255,
            222,
            206
        ) else Color.rgb(242, 242, 242)

    }

    // 报警颜色
    fun getAlarmColor(isAlarm: Boolean): Int {
        return if (isAlarm) Color.rgb(
            230,
            147,
            147
        ) else Color.rgb(255, 255, 255)

    }

    //    0、等待发送，1、等待回复，2、指令超时，3、执行失败，4、执行中，5、失败重试，9、执行成功
    // 报警颜色
    fun getResultColor(status: String): Int {
        return when (status) {
            "指令超时", "执行失败", "设备已离线", "设备未连接", "未找到执行设备" -> Color.rgb(
                240,
                80,
                80
            )
            "执行成功" -> Color.rgb(0, 0, 255)
            else -> Color.rgb(0, 0, 0)
        }
    }

    // 控制输出背景色
    fun getBgColors(position: Int): Int {

        return if (selectedIndex == position) Color.rgb(
            220,
            233,
            255
        ) else Color.rgb(255, 255, 255)

    }

    // 接触器选中颜色
    fun getDeviceSelectColors(position: Int): Int {


        return if (selectedIndex == position) R.drawable.shape_device_selecte_bg else R.drawable.shape_device_type_bg

    }

    // 控制输出背景色
    fun getClickFlag(position: Int): Boolean {
        return selectedIndex == position
    }

    // 控制输出背景色
    fun getSwitchState(state: Int): String {
        return if (state == 1) "闭合" else "断开"
    }

    override fun getItemCount() =
        if (dataList.isNotEmpty()) dataList.size else 0


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (dataList.size > 1) {

            bindView(holder, dataList[position])
        } else {
            bindView(holder, dataList[0])

        }
    }

    abstract fun bindView(holder: RecyclerView.ViewHolder, bean: T)


    open fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }


    open fun setData(list: List<T>?) {
        dataList.addAll(list!!)

        notifyDataSetChanged()
    }

    open fun addData(list: List<T>?) {
        if (list != null) {
            for (t in list) {
                if (!dataList.contains(t)) {
                    dataList.add(t)
                }
            }
            notifyDataSetChanged()
        }
    }

    open fun setNewData(data: List<T>) {
        this.dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    open fun addData(position: Int, data: List<T>) {
        this.dataList.addAll(position, data)
        notifyItemRangeInserted(position, data.size)
    }

    protected open fun initRecyclerView(recyclerView: RecyclerView, type: Int) {
        val manager = LinearLayoutManager(recyclerView.context)
        manager.orientation = type
        recyclerView.layoutManager = manager
        recyclerView.isVerticalScrollBarEnabled = true
        if (type == LinearLayoutManager.VERTICAL) {
            val divider =
                GridItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(
                recyclerView.context,
                R.drawable.shape_item_decoration
            )?.let { divider.setDrawable(it) }
            recyclerView.addItemDecoration(divider)
        }
    }


    protected open fun openDetails(t: T, position: Int) {
        mListener?.showDetailsView(t, position)
    }

    protected open fun longClickDetails(t: T, position: Int) {
        mLongListener?.longClick(t, position)
    }

    protected open fun deleteItem(t: T, position: Int) {

        mDeleteListener.delete(t, position)
    }

    interface OnItemListener<T> {
        /**
         * @param bean     子类的数据类型
         * @param position 当前位置
         */
        fun showDetailsView(bean: T, position: Int)

    }

    open fun setItemListener(listener: OnItemListener<T>) {
        this.mListener = listener
    }

    interface OnItemLongListener<T> {
        /**
         * @param bean     子类的数据类型
         * @param position 当前位置
         */
        fun longClick(bean: T, position: Int)

    }


    open fun setItemLongListener(listener: OnItemLongListener<T>) {
        this.mLongListener = listener
    }


    /*==============================删除监听===================================*/


    open fun setOnItemDelete(listener: OnItemDeleteListener<T>) {
        this.mDeleteListener = listener
    }


    interface OnItemDeleteListener<T> {
        /**
         * @param bean     子类的数据类型
         * @param position 当前位置
         */
        fun delete(bean: T, position: Int)

    }


    protected var mListenerAtt: OnAttributeListener<T>? = null
    fun setAttributeListener(listener: OnAttributeListener<T>) {
        mListenerAtt = listener
    }

    interface OnAttributeListener<T> {
        fun delete(attributeId: Int, stationId: Int)
        fun edit(bean: T)
    }


}