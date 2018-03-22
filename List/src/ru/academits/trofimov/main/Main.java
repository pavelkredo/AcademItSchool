package ru.academits.trofimov.main;

import ru.academits.trofimov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> head1 = new SinglyLinkedList<>();
        head1.addElementAtEnd(22);
        head1.addElementAtEnd(32);
        head1.addElementAtEnd(45);
        head1.addElementAtEnd(57);
        head1.addElementAtEnd(64);

        SinglyLinkedList<Integer> head2 = head1.copy();
        head1.setElement(4, 15);
        System.out.println(head2.getElement(4));

        /* SinglyLinkedList<Integer> head2 = head1.copy();
        head1.setElement(1, 15);
        System.out.println(head2.getElement(1));
        System.out.println(head2.getSize());

        head2.rotate();
        System.out.println(head2.getElement(0));

        head2.insertElementAtStart(7);

        head1.insertElementAtIndex(1, 20);
        System.out.println(head1.getElement(4)); */
    }
}