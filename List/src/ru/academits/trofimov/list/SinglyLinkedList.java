package ru.academits.trofimov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    private SinglyLinkedList(ListItem<T> head) {
        this.head = head;
    }

    public void addElement(T element) {
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

    public T getData() {
        return head.getData();
    }

    public T getFirstElement() {
        return head.getData();
    }

    public T getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен соответствовать размеру списка.");
        }

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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен соответствовать размеру списка.");
        }

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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен соответствовать размеру списка.");
        }

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
        size++;
    }

    public void insertElementAtIndex(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс должен соответствовать размеру списка.");
        }

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i + 1) {
                ListItem<T> newElement = new ListItem<>(element, p.getNext());
                p.setNext(newElement);
                size++;
            }
            i++;
        }
    }

    public boolean removeByElement(T element) {
        ListItem<T> temp = head;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData() == element) {
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
            throw new NullPointerException("В списке нет элементов. Удаление первого элемента невозможно.");
        }

        T removedElement = head.getData();
        ListItem<T> temp = head.getNext();
        head.setData(null);
        head.setNext(null);
        head = temp;
        size--;

        return removedElement;
    }

    public void listRotation() {
        ListItem<T> temp = new ListItem<>(head.getData(), null);

        for (ListItem<T> p = head; p.getNext() != null; p = p.getNext()) {
            temp = new ListItem<>(p.getNext().getData(), temp);
        }

        head = temp;
    }

    public SinglyLinkedList<T> listCopy() {
        int i = 0;
        SinglyLinkedList<T> tempHead = null;

        for (ListItem<T> p = head; p.getNext() != null; p = p.getNext()) {
            ListItem<T> next = new ListItem<>(p.getNext().getData(), p.getNext().getNext());
            p = new ListItem<>(p.getData(), next);

            if (i < 1) {
                tempHead = new SinglyLinkedList<>(p);
                tempHead.size = size;
                i++;
            }
        }

        return tempHead;
    }
}