package com.dawn.lib2

class MyBean {
    var age: String = "111"

        get() = field.toUpperCase()

    var name: String = "zhang"
        // 这里使用field而不是使用lastName, 是因为如果使用lastName会造成递归调用从而造成内存溢出, 因为使用lastName也会涉及到调用set/get的问题
        get() = field.toUpperCase()


}