package com.example.dawnmvvm.ui.hash;

public class MyNode<T> {
    T data;
    MyNode<T>next;
    MyNode<T>pre;

    public MyNode(T data, MyNode<T> next, MyNode<T> pre) {
        this.data = data;
        this.next = next;
        this.pre = pre;
    }
}
