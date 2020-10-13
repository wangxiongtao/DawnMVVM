package com.example.dawnmvvm.ui.hash;

/**
 * 单向列表
 *
 * @param <E>
 */
public class MyNodeList<E> {
    private MyNode<E> last;
    private MyNode<E> head;
    private MyNode<E> next;
    int count = 0;

    public void add(E d) {
        last = new MyNode<>(d, null, last);
        count++;
    }

    public void addNext(E d) {
        MyNode<E> myNode = new MyNode<>(d, null, null);
        if (last == null) {
            last = myNode;
            head = last;
        } else {
            last.next = myNode;
            last = myNode;
        }
        count++;
    }

    public E getNext(int index) {
        MyNode<E> myNode = head;
        for (int i = 0; i < index; i++) {
            myNode = myNode.next;
        }
        return myNode.data;
    }

    public void removeIndex(int index) {
        MyNode<E> pre = null;
        MyNode<E> current = head;
        for (int i = 0; i < index; i++) {
            pre = current;
            current = current.next;
        }
        if (pre == null) {
            head = null;
        } else {
            pre.next = current.next;
        }
        current.next=null;
        count--;

    }

    /**
     * 位置前插入
     * @param index
     * @param d
     */
    public void insertIndex(int index, E d) {
        MyNode<E> pre = null;
        MyNode<E> current = head;
        for (int i = 0; i < index; i++) {
            pre = current;
            current = current.next;
        }
        MyNode<E>myNode=new MyNode<>(d,null,null);
        if (pre == null) {
            head = myNode;
        } else {
            pre.next=myNode;
           myNode.next=current;
        }

    }


    public E get(int index) {
        return getNode(index).data;
    }

    public MyNode<E> getNode(int index) {
        MyNode<E> node = last;
        for (int i = count - 1; i > index; i--) {
            node = node.pre;
        }
        return node;
    }

    public void remove(int index) {
        MyNode<E> node = last;
        for (int i = count - 1; i > index; i--) {
            node = node.pre;
        }
    }
}
