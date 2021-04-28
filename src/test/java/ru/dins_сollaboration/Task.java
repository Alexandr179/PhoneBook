package ru.dins_сollaboration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Функция сортирующая массив элементов A:
 * Sort(A,p,r)
 * 1 if p < r
 * 2    then           q := round_half_down((p+r)/2)
 * 3                       Sort(A,p,q)
 * 4                       Sort(A,q+1,r)
 * 5                       Merge(A,p,q,r)
 *
 * Пример массива:
 * A = (5,2,4,6,1,3,2,6)
 *
 * Примера запуска:
 * Sort(A,1,length[A])
 *
 * Необходимо:
 * Разработать алгоритм функции Merge(A,p,q,r) на любом удобном вам языке, с использованием дополнительной памяти или без нее, как вам будет быстрее или удобнее в реализации.
 * Если у вас получится - с радостью ждем вас для прохождения дополнительного тестирования.
 */

public class Task {
    public static int[] A = {5,2,4,6,1,3,2,6};

    public static void main(String[] args) {
        sorting(A,1, A.length);
    }

    public static void sorting(int[] arr, int p, int r){
        if(p < r){
            int q = (p+r)/2;// ..round_half_down
            sort(A,p,q);
            sort(A,q+1,r);
            merge(A,p,q,r);
        }
    }


    public static void sort (int[] arr, int p, int q){
        int[] array = Arrays.stream(arr).sorted().toArray();
    }



    public static void merge (int[] arr, int p, int q, int r){

    }


    // WTF??.......  .. .
}
