package com.example.hotelmanagment;


import java.util.LinkedList;

public class CustomQueue<T> {
    private LinkedList<T> list;

    public CustomQueue() {
        list = new LinkedList<>();
    }

    public void enqueue(T item) {
        list.addLast(item);
    }

    public T dequeue() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    public T peek() {
        return list.isEmpty() ? null : list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() { return list.size(); }

    public java.util.List<T> toList() {
        return new java.util.ArrayList<>(list);
    }

    public void clear() { list.clear(); }
}
