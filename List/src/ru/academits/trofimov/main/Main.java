package ru.academits.trofimov.main;

import ru.academits.trofimov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> head1 = new SinglyLinkedList<>();
        head1.addElement(22);
        head1.addElement(32);
        head1.addElement(45);
        head1.addElement(57);
        head1.addElement(64);

        System.out.println(head1.getSize());

        head1.insertElementAtIndex(5, 100);
        head1.removeByElement(100);

        SinglyLinkedList<Integer> head2 = head1.listCopy();
        head1.setElement(1, 15);
        System.out.println(head2.getElement(4));

        head2.listRotation();
        System.out.println(head2.getElement(0));
    }
}