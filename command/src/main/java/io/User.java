package io;

import java.util.Scanner;

/**
 * Класс для обработки ввода пользователем
 */
public class User {
    /**
     * Чтение данных
     */
    Scanner scanner;

    /**
     * При вызове конструктора производится чтение из стандартного потока ввода
     */
    public User() {
        scanner = new Scanner(System.in, "UTF-8");
    }

    /**
     * При вызове конструктора производится чтение из стандартного потока ввода
     * @param scanner - объект типа Scanner
     */
    public User(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод читает данные на которые он ссылается
     * @return прочитанная строка
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Метод выводит текст команды в стандартный поток вывода
     * @param str выводится в стандартный поток вывода
     */
    public void printCommand(String str) {
        System.out.println(str);
    }

    /**
     * Метод выводит текст ошибки в стандартный поток вывода
     * @param str выводится в стандартный поток вывода
     */
    public void printError(String str) {
        System.out.println(str);
    }

    /**
     * Вступление в новой строке
     */
    public void PrintPreamble() {
        System.out.print("<3 ");
    }
}