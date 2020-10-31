package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        List<Integer> list = new ArrayList<>();
        DateTimeFormatter citizen = DateTimeFormatter.ofPattern("hh:mm:ss a");
        DateTimeFormatter military = DateTimeFormatter.ISO_LOCAL_TIME;

        try (BufferedReader input = new BufferedReader(new FileReader(new File(inputName)));
             BufferedWriter output = new BufferedWriter(new FileWriter(new File(outputName)))) {
            while (input.ready()) {
                String s = input.readLine();
                String t = military.format(citizen.parse(s));
                String[] hms = t.split(":");
                int secs = Integer.parseInt(hms[0]) * 3600 + Integer.parseInt(hms[1]) * 60 + Integer.parseInt(hms[2]);
                list.add(secs);
            }
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                int time = list.get(i);
                int h = time / 3600;
                int m = (time - h * 3600) / 60;
                int s = time - h * 3600 - m * 60;
                String notAns = String.format("%02d:%02d:%02d", h, m, s);
                String ans = citizen.format(military.parse(notAns));
                output.write(ans);
                if (i != list.size() - 1) {
                    output.newLine();
                }
            }
        }
    } //трудоёмкость O(N * logN) ресурсоёмкость O(N)
    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */


    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        int[] array = new int[(273 + 500 + 1) * 10 + 1];
        int delta = 2730;
        int count = 0;
        File inputFile = new File(inputName);
        File outputFile = new File(outputName);

        try (BufferedReader input = new BufferedReader(new FileReader(inputFile));
             BufferedWriter output = new BufferedWriter(new FileWriter(outputFile))) {
            while (input.ready()) {
                count++;
                String s = input.readLine();
                int t = (int) Math.floor((Float.parseFloat(s) * 10));
                array[t + delta]++;

            }
            int cur = 0;
            for (int i = 0; i < array.length; i++) {
                int t = array[i];
                if (t == 0) continue;
                for (int j = 0; j < t; j++) {
                    output.write((i - delta) / 10.0 + "\n");
                    cur++;
                }
            }
        }
    }
    // Трудоёмкость O(кол-во элементов в массиве + кол-во наибольших повторений элемента в массиве)
    // Ресурсоёмкость O(N)

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        int num = 0; //min number
        int reps = 0; //max reps
        List<Integer> list = new ArrayList<>(); //initial list
        Map<Integer, Integer> map = new HashMap<>();

        try (BufferedReader input = new BufferedReader(new FileReader(new File(inputName)));
             BufferedWriter output = new BufferedWriter(new FileWriter(new File(outputName)))) {
            while (input.ready()) {
                Integer cur = Integer.parseInt(input.readLine());
                list.add(cur);
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> i : map.entrySet()) {
                if (i.getValue() > reps) {
                    num = i.getKey();
                    reps = i.getValue();
                }
                if (i.getValue() == reps && i.getKey() < num) {
                    num = i.getKey();
                }
            }
            for (int i : list) {
                if (i != num) {
                    output.write(i + "\n");
                }
            }
            for (int i = 0; i < reps; i++) {
                output.write(num + "\n");
            }
        }
    } //трудоёмкость O(N*logN) ресурсоёмкость O(N)

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
