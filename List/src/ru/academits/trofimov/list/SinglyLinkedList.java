package ru.academits.trofimov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    private SinglyLinkedList(ListItem<T> head) {
        this.head = head;
    }

    public void addElementAtEnd(T element) {
        if (head == null) {
            head = new ListItem<>(element);
            size++;
        } else {
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (p.getNext() == null) {
                    p.setNext(new ListItem<>(element));
                    size++;
                    break;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public T getElement(int index) {
        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                return p.getData();
            }
            i++;
        }
        return null;
    }

    public T setElement(int index, T element) {
        int i = 0;
        T oldValue;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                oldValue = p.getData();
                p.setData(element);
                return oldValue;
            }
            i++;
        }
        return null;
    }

    public T removeElement(int index) {
        int i = 0;
        T removedElement;
        ListItem<T> temp = null;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i + 1) {
                temp = p;
            }

            if (index == i) {
                removedElement = p.getData();
                temp.setNext(p.getNext());
                p.setData(null);
                p.setNext(null);
                size--;
                return removedElement;
            }
            i++;
        }
        return null;
    }

    public void insertElementAtStart(T element) {
        head = new ListItem<>(element, head);
    }

    public void insertElementAtIndex(int index, T element) {
        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i + 1) {
                ListItem<T> newElement = new ListItem<>(element, p.getNext());
                p.setNext(newElement);
            }
            i++;
        }
    }

    public boolean removeByElement(T element) {
        if (element == null) {
            throw new NullPointerException("Передаваемый элемент не должен быть пустой ссылкой.");
        }

        ListItem<T> temp = head;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData().equals(element)) {
                temp.setNext(p.getNext());
                p.setData(null);
                p.setNext(null);
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
            } else{
                p.setNext(temp1);
                temp1 = p;
            }

            head = temp1;
        }
    }

    public SinglyLinkedList<T> copy() {
        if(size == 0) {
            throw new NullPointerException("Список является пустым. Копирование невозможно.");
        }

        int i = 0;
        ListItem<T> tempHead = null;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            ListItem<T> next = null;

            if(p.getNext() != null) {
                next = new ListItem<>(p.getNext().getData(), p.getNext().getNext());
            }
            p = new ListItem<>(p.getData(), next);

            if (i < 1) {
                tempHead = p;
                i++;
            }
        }
        return new SinglyLinkedList<>(tempHead);
    }
}