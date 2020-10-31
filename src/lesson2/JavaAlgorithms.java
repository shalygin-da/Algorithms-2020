package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого. //второй должен быть позже* первого
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputName), StandardCharsets.UTF_8)
        );
        String s = input.readLine();
        ArrayList<Integer> list = new ArrayList<>();
        while (s != null) {
            Integer value = Integer.valueOf(s);
            if (value < 0) throw new IllegalArgumentException();
            list.add(value);
            s = input.readLine();
        }
        if (list.isEmpty()) throw new IllegalArgumentException();

        int[] dif = new int[list.size() - 1];
        for (int i = 0; i < list.size() - 1; i++) dif[i] = list.get(i + 1) - list.get(i);

        int max = 0;
        int sum = 0;
        int buyPos = 0;
        int maxBuyPos = 0;
        int sellPos = 1;
        for (int i = 0; i < dif.length; i++) {
            sum += dif[i];
            if (sum > max) {
                max = sum;
                maxBuyPos = buyPos + 1;
                sellPos = i + 2;
            }
            if (sum < 0) {
                sum = 0;
                buyPos = i + 1;
            }
        }

        return new Pair<>(maxBuyPos, sellPos);
    } //ресурсоемкость O(n); трудоемкость O(n)

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) { //в исх коде опечатка: написано "firs"
        if (first == null || second == null || first.length() == 0 || second.length() == 0) return "";
        if (first.equals(second)) return first;

        int index = 0;
        int max = 0;
        String sub = "";
        for (int i = 0; i < first.length() - 1; i++) {
            int next = i + 1;
            while (second.contains(first.substring(i, next))) {
                if (next < second.length() - 1 && next < first.length() - 1) { next++; } else break;
            }
            String curSub = first.substring(i, next - 1);
            if (curSub.length() > max) {
                max = curSub.length();
                sub = first.substring(i, next - 1);
            }
        }
        return sub;
    } //трудоёмкость, ресурскоёмкость: O(n*n)

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }
}
