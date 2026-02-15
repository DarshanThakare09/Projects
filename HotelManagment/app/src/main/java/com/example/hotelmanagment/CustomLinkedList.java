package com.example.hotelmanagment;


import java.util.ArrayList;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        head = null;
        size = 0;
    }

    public void add(T item) {
        Node<T> node = new Node<>(item);
        if (head == null) {
            head = node;
        } else {
            Node<T> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = node;
        }
        size++;
    }

    public boolean removeIf(java.util.function.Predicate<T> predicate) {
        if (head == null) return false;
        while (head != null && predicate.test(head.data)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> cur = head;
        while (cur != null && cur.next != null) {
            if (predicate.test(cur.next.data)) {
                cur.next = cur.next.next;
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public T find(java.util.function.Predicate<T> predicate) {
        Node<T> cur = head;
        while (cur != null) {
            if (predicate.test(cur.data)) return cur.data;
            cur = cur.next;
        }
        return null;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        Node<T> cur = head;
        while (cur != null) {
            list.add(cur.data);
            cur = cur.next;
        }
        return list;
    }

    public int size() { return size; }

    public void clear() {
        head = null;
        size = 0;
    }
}
