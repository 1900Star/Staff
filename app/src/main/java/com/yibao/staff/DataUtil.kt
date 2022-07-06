package com.yibao.staff

import java.util.*
import kotlin.collections.ArrayList


/**
 * @author  luoshipeng
 * createDate：2022/7/6 0006 10:52
 * className   DataUtil
 * Des：TODO
 */
class DataUtil {

    fun getContent(): String {
        val random = Random()

        val nextInt = random.nextInt(staffArray.size)

        return staffArray[nextInt]
    }

    fun staffList(): ArrayList<StaffBean> {
        val number = ArrayList<StaffBean>()
        number.add(StaffBean("1", "do", "C"))
        number.add(StaffBean("2", "re", "D"))
        number.add(StaffBean("3", "mi", "E"))
        number.add(StaffBean("4", "fa", "F"))
        number.add(StaffBean("5", "so", "G"))
        number.add(StaffBean("6", "la", "A"))
        number.add(StaffBean("7", "si", "B"))


        return number
    }

    fun getLetter(): ArrayList<String> {
        val number = ArrayList<String>()
        number.add("C")
        number.add("D")
        number.add("E")
        number.add("F")
        number.add("G")
        number.add("A")
        number.add("B")
        return number
    }

    fun getName(): ArrayList<String> {
        val number = ArrayList<String>()
        number.add("do")
        number.add("re")
        number.add("mi")
        number.add("fa")
        number.add("so")
        number.add("la")
        number.add("si")
        return number
    }

    val staffArray = arrayOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "C",
        "D",
        "E",
        "F",
        "G",
        "A",
        "B",
        "do",
        "re",
        "mi",
        "fa",
        "so",
        "la",
        "si",
    )

}