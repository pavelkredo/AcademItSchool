package ru.academits.trofimov.arraylist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ArrayList<T>{
    private T[] elements;
    private int size;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new ArithmeticException("Размерность массива не может быть меньше 0.");
        }

        elements = (T[]) new Object[capacity];
    }

    public ArrayList(T[] elements) {
        if (elements.length == 0) {
            this.elements = (T[]) new Object[]{};
        } else {
            this.elements = Arrays.copyOf(elements, elements.length);
        }
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = (size == 0) ? (T[]) new Object[]{} : Arrays.copyOf(elements, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    public boolean add(T element) {
        if (element != null) {
            elements[size] = element;
            size++;
            return true;
        }
        return false;
    }

    public void add(int index, T element) {
        if (index >= 0 && index <= size && element != null) {
            T[] temp = (T[]) new Object[size + 1];

            for (int i = 0; i < temp.length; i++) {
                if (i == index) {
                    temp[i] = element;
                }

                temp[i] = element;
            }

            elements = temp;
        }
    }

    public boolean addAll(Collection<? extends T> c) {
        Iterator<?> iterator = c.iterator();

        while (iterator.hasNext()) {
            elements[size] = (T) iterator.next();
            size++;
        }
    }
}
