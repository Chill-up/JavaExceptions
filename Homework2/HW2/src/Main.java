import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        task01();
        //task02();
        //task03();
        //task04();
    }

    /**
     * Задача 1.
     * Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение.
     * Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных.
     */
    public static void task01() {
        float fl = getFloatNum();
        System.out.printf("Получили вещественное число %.2f\n", fl);
    }

    /**
     * Задача 2.
     * Исправьте код из файла.
     */
    public static void task02(){
        int [] intArray = genIntArray(10); //генерируем валидный массив
        int d = 0; //вынес инициализацию переменной за пределы блока try
        try {
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e.getMessage()); //получаем сообщение о делении на ноль
        }
    }

    /**
     * Задача 3.
     * Дан следующий код, исправьте его там, где требуется (задание 3)
     */
    public static void task03() throws Exception {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = { 1, 2 };
            abc[3] = 9;
        } catch (NullPointerException ex) {
            System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Throwable ex) {
            //это исключение перемещено ниже, т.к. в случае неправильного порядка оно блокирует все остальные, что идут после него
            System.out.println("Что-то пошло не так...");
        }
    }

    public static void printSum(Integer a, Integer b) { //FileNotFoundException здесь не нужен, работа с файлами не ведется
        System.out.println(a + b);
    }

    /**
     * Задача 4.
     * Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
     * Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
     */

    public static void task04() throws MyEmptyStringException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:\n");
        String userInput = scanner.nextLine();
        if (userInput.isBlank()){
            throw new MyEmptyStringException("Вводить пустые строки нельзя!\n");
        }
        else {
            System.out.printf("Введена строка:\n%s", userInput);
        }
    }

    /**
     * Метод сичтывания дробного числа.
     * @return - возвращает число типа float.
     */
    public static float getFloatNum() {
        Scanner sc = new Scanner(System.in);
        float n = 0;
        boolean valid = false;
        while (!valid){
            System.out.println("Введите вещественное число (разделитель - запятая):\n");
            try {
                n = sc.nextFloat();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введены не верные данные! Вместо вещественного числа получили: " + e.getMessage() +
                        "\nПопробуйте еще раз.");
                sc.next(); //для очистки некорректного ввода из буфера
            }
        }
        sc.close(); //в конце закрыли сканнер
        return n;
    }

    /**
     * Метод генерации одномерного целочисленного массива значений от 0 до 10.
     * @param num - длина массива.
     * @return - целочисленный одномерный массив.
     */
    static int[] genIntArray(int num) {
        Random random = new Random();
        int[] res = new int[num];
        for(int i = 0; i < res.length; i++) {
            res[i] = random.nextInt(0, 11);
        }
        return res;
    }
}

class MyEmptyStringException extends IOException {
    public MyEmptyStringException(String message) {
        super(message);
    }
}