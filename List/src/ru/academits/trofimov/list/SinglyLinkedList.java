package ru.academits.trofimov.list;

import java.util.List;

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
        if(size == 0) {
            throw new IndexOutOfBoundsException("Невозможно получить первый элемент. Список пустой.");
        }
        return head.getData();
    }

    private ListItem<T> getUnit(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка.");
        }

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                return p;
            }
            i++;
        }
        return null;
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

    public T removeElement(int index) {
        ListItem<T> temp = getUnit(index - 1);
        ListItem<T> unit = getUnit(index);

        T removedElement = unit.getData();
        temp.setNext(unit.getNext());
        unit.setData(null);
        unit.setNext(null);
        size--;

        return removedElement;
    }

    public void insertElementAtStart(T element) {
        head = new ListItem<>(element, head);
        size++;
    }

    public void insertElementAtIndex(int index, T element) {
        ListItem<T> unit = getUnit(index - 1);
        ListItem<T> newElement = new ListItem<>(element, unit.getNext());
        unit.setNext(newElement);
        size++;
    }

    public boolean removeByElement(T element) {
        ListItem<T> temp = head;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData() == element || p.getData().equals(element)) {
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

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext() != null) {
                p = new ListItem<>(p.getData(), new ListItem<>(p.getNext().getData(), p.getNext().getNext()));
            }

            if (p.getData() == head.getData()) {
                tempHead = p;
            }
        }
        return new SinglyLinkedList<>(tempHead, size);
    }
}