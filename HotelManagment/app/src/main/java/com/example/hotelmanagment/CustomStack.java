package com.example.hotelmanagment;

import java.util.ArrayList;

public class CustomStack<T> {
    private ArrayList<T> stack;

    public CustomStack() {
        stack = new ArrayList<>();
    }

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        if (stack.isEmpty()) return null;
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if (stack.isEmpty()) return null;
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public java.util.List<T> toList() {
        // return copy with newest first for display convenience
        ArrayList<T> copy = new ArrayList<>(stack);
        java.util.Collections.reverse(copy);
        return copy;
    }

    public void clear() { stack.clear(); }
}
