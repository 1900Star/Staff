package com.yibao.staff.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author  luoshipeng
 * createDate：2021/6/29 0029 15:55
 * className   BaseBindingActivity
 * Des：TODO
 */


abstract class BaseBindingActivity<T : ViewBinding> : AppCompatActivity() {

    protected lateinit var mBinding: T
    protected val mTag = " ==== " + javaClass.simpleName + "  "


    @SuppressLint("MissingSuperCall")
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val clazz = type.actualTypeArguments[0] as Class<T>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            mBinding = method.invoke(null, layoutInflater) as T
            setContentView(mBinding.root)
            initView()
            initData()
            initListener()

        }
    }


    /**
     * 设置Activity为横屏
     */
    protected fun horizontalScreen() {
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    fun <T : ViewModel?> gets(modelClass: Class<T>): T {

        return ViewModelProvider(this).get(modelClass)
    }


    abstract fun initView()

    abstract fun initData()


    abstract fun initListener()

}