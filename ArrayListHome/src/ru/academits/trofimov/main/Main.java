package ru.academits.trofimov.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Прочитать в список все строки из файла
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner (new FileInputStream("test.txt"), "windows-1251")) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }

        System.out.println(lines);

        // Удалить из списка все четные числа
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 7, 9, 11));

        for (int i = 0; i < numbers1.size(); i++) {
            int number = numbers1.get(i);

            if(number % 2 == 0) {
                numbers1.remove(i);
            }
        }

        System.out.println(numbers1);

        // Создать новый список с элементами из первого списка без повторений
        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 3, 5, 1, 3, 7, 9, 3, 4, 3, 3, 5, 8));
        ArrayList<Integer> newNumbers = new ArrayList<>();

        for(int i = 0; i < numbers2.size(); i++) {
            int number = numbers2.get(i);

            if(numbers2.indexOf(number) == i) {
                newNumbers.add(number);
            }
        }

        System.out.println(numbers2);
        System.out.println(newNumbers);
    }
}