package ru.academits.trofimov.list;

import java.util.List;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    private SinglyLinkedList(ListItem<T> head, int size) {
        this.head = head;
        this.size = size;
    }

    public void addElementAtEnd(T element) {
        if (head == null) {
            head = new ListItem<>(element);
            size++;
        } else {
            ListItem<T> unit = getUnit(size - 1);
            unit.setNext(new ListItem<>(element));
            size++;
        }
    }

    public int getSize() {
        return size;
    }

    public T getFirstElement() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Невозможно получить первый элемент. Список пустой.");
        }
        return head.getData();
    }

    private ListItem<T> getUnit(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Невозможно получить узел. Индекс вне границ списка.");
        }
        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                return p;
            }
            i++;
        }
        throw new NullPointerException("Невозможно получить элемент. Индекс вне границ списка.");
    }

    public T getElement(int index) {
        return getUnit(index).getData();
    }

    public T setElement(int index, T element) {
        ListItem<T> unit = getUnit(index);
        T oldValue = unit.getData();
        unit.setData(element);
        return oldValue;
    }

    public void insertElementAtStart(T element) {
        head = new ListItem<>(element, head);
        size++;
    }

    public void insertElementAtIndex(int index, T element) {
        if (index == 0) {
            insertElementAtStart(element);
            return;
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Невозможно вставить элемент. Индекс вне границ списка.");
        }

        ListItem<T> unit = getUnit(index - 1);
        ListItem<T> newElement = new ListItem<>(element, unit.getNext());
        unit.setNext(newElement);
        size++;
    }

    public T removeElement(int index) {
        if (index == 0) {
            return removeFirstElement();
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Невозможно удалить элемент. Индекс вне границ списка.");
        }

        ListItem<T> temp = getUnit(index - 1);
        ListItem<T> unit = temp.getNext();

        T removedElement = unit.getData();
        temp.setNext(unit.getNext());
        unit.setData(null);
        unit.setNext(null);
        size--;

        return removedElement;
    }

    public boolean removeByElement(T element) {
        ListItem<T> temp = head;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (Objects.equals(p.getData(), element)) {
                temp.setNext(p.getNext());
                p.setData(null);
                p.setNext(null);
                size--;
                return true;
            }

            temp = p;
        }
        return false;
    }

    public T removeFirstElement() {
        if (size == 0) {
            throw new NullPointerException("Невозможно удалить элемент. Список пустой.");
        }

        T removedElement = head.getData();
        ListItem<T> temp = head.getNext();
        head.setData(null);
        head.setNext(null);
        head = temp;
        size--;

        return removedElement;
    }

    public void rotate() {
        ListItem<T> temp1 = null;
        ListItem<T> temp2;

        for (ListItem<T> p = head; p != null; p = temp2) {
            temp2 = p.getNext();

            if (p == head) {
                p.setNext(null);
                temp1 = p;
            } else {
                p.setNext(temp1);
                temp1 = p;
            }

            head = temp1;
        }
    }

    public SinglyLinkedList<T> copy() {
        ListItem<T> tempHead = null;
        boolean isHead = true;

        for (ListItem<T> p = head; p.getNext() != null; p = p.getNext()) {
            p = new ListItem<>(p.getData(), new ListItem<>(p.getNext().getData(), p.getNext().getNext()));

            if (isHead) {
                tempHead = p;
                isHead = false;
            }
        }
        return new SinglyLinkedList<>(tempHead, size);
    }
}