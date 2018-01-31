package  ru.academits.trofimov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    private static String getFinallyLine(char character) {
        if (character == '<') {
            return "&lt;";
        } else if (character == '>') {
            return "&gt;";
        } else if (character == '&') {
            return "&amp;";
        }

        return Character.toString(character);
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Аргумента должно быть два.");
            return;
        }

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]), "windows-1251");
             PrintWriter writer = new PrintWriter(args[1])) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>Пример таблицы</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table>");

            String line = scanner.nextLine();
            boolean isLineTransfer = false;
            boolean isInsideQuatation = false;

            while (scanner.hasNext()) {
                if (!isLineTransfer) {
                    writer.println("<tr>");
                    writer.println("<td>");
                }

                for (int i = 0; i < line.length(); i++) {
                    if (isInsideQuatation) {
                        if (line.charAt(i) == '\"') {
                            if (i < line.length() - 1 && line.charAt(i + 1) == '\"') {
                                writer.print('\"');
                                i++;
                            } else {
                                isInsideQuatation = false;
                            }

                            isLineTransfer = false;
                        } else {
                            writer.print(getFinallyLine(line.charAt(i)));
                        }
                    } else {
                        if (line.charAt(i) == ',') {
                            writer.println("</td>");
                            writer.println("<td>");
                        } else if (line.charAt(i) == '\"') {
                            isLineTransfer = true;
                            isInsideQuatation = true;
                        } else {
                            writer.write(getFinallyLine(line.charAt(i)));
                        }
                    }
                }

                if (isLineTransfer) {
                    writer.println("<br/>");
                } else {
                    writer.println("</td>");
                    writer.println("</tr>");
                }

                line = scanner.nextLine();
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}