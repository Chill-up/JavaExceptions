import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static Random random = new Random();

    public Main() {
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            System.out.printf("\n*** Итерация %d ***\n\n", i + 1);
            int firstArrayLength = random.nextInt(2, 5);
            int secondArrayLength = random.nextInt(2, 5);
            int[] firstArray = genArray(firstArrayLength);
            int[] secondArray = genArray(secondArrayLength);
            //Task01(firstArray, secondArray);
            Task02(firstArray, secondArray);
        }
    }

    static int[] Task01(int[] arr1, int[] arr2) {
        int[] result_arr = new int[arr1.length];
        try {
            result_arr = subArray(arr1, arr2);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
            System.out.printf("Длина первого массива: %d\nДлина второго массива: %d\n", e.getLength1(), e.getLength2());
        }
        return result_arr;
    }

    static float[] Task02(int[] arr1, int[] arr2) {
        float[] res_arr = new float[arr1.length];
        try {
            res_arr = divArray(arr1, arr2);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
            System.out.printf("Длина первого массива: %d\nДлина второго массива: %d\n", e.getLength1(), e.getLength2());
        } catch (MyDivisionByZeroException e) {
            System.out.println(e.getMessage());
            System.out.printf("По индексу %d второго массива находится 0!\n", e.getIndex());
        }
        return res_arr;
    }

    static int[] subArray(int[] arr1, int[] arr2) {
        if (arr1 != null && arr2 != null) {
            if (arr1.length != arr2.length) {
                throw new MyArraySizeException("Error! Кол-во элементов массива должно быть одинаково!", arr1.length, arr2.length);
            } else {
                int[] result_arr = new int[arr1.length];
                System.out.printf("Первый массив: %s\nВторой массив: %s\n", Arrays.toString(arr1), Arrays.toString(arr2));

                for(int i = 0; i < arr1.length; i++) {
                    result_arr[i] = arr1[i] - arr2[i];
                }

                System.out.printf("\nМассив, каждый элемент которого это разность элементов первого и второго массива:\n%s\n", Arrays.toString(result_arr));
                return result_arr;
            }
        } else {
            throw new NullPointerException("Error! Оба массива должны существовать.");
        }
    }

    static int[] genArray(int num) {
        int[] res = new int[num];
        for(int i = 0; i < res.length; i++) {
            res[i] = random.nextInt(0, 11);
        }
        return res;
    }

    static float[] divArray(int[] arr1, int[] arr2) {
        if (arr1 != null && arr2 != null) {
            if (arr1.length != arr2.length) {
                throw new MyArraySizeException("Error! Кол-во элементов массива должно быть одинаково!", arr1.length, arr2.length);
            } else {
                float[] result_arr = new float[arr1.length];
                System.out.printf("Первый массив: %s\nВторой массив: %s\n", Arrays.toString(arr1), Arrays.toString(arr2));

                for(int i = 0; i < arr1.length; i++) {
                    if (arr2[i] == 0) {
                        throw new MyDivisionByZeroException("Ошибка! Деление на ноль!", i);
                    }

                    result_arr[i] = (float)arr1[i] / (float)arr2[i];
                }

                System.out.println("\nМассив, каждый элемент которого это частное от деления соответствующих элементов первого и второго массива:");
                printFloatArray(result_arr);
                return result_arr;
            }
        } else {
            throw new NullPointerException("Error! Оба массива должны существовать.");
        }
    }

    static void printFloatArray(float[] array) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        System.out.print('[');
        for(int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                System.out.print(decimalFormat.format(array[i]) + "; ");
            } else {
                System.out.print(decimalFormat.format(array[i]) + ']');
            }
        }
    }
}

class MyArraySizeException extends RuntimeException {
    int length1;
    int length2;

    public int getLength1() {
        return this.length1;
    }

    public int getLength2() {
        return this.length2;
    }

    public MyArraySizeException(String message, int length1, int length2) {
        super(message);
        this.length1 = length1;
        this.length2 = length2;
    }
}

class MyDivisionByZeroException extends RuntimeException {
    int index;

    public int getIndex() {
        return this.index;
    }

    public MyDivisionByZeroException(String message, int index) {
        super(message);
        this.index = index;
    }
}