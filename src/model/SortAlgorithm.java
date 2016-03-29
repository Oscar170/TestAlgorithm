/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author oscar
 */
public class SortAlgorithm {

    public static int c = 0;
    public int[] aDatos;
    private int[] aRangos = new int[6];

    public SortAlgorithm() {
        aRangos[0] = 10;
        aRangos[1] = 100;
        aRangos[2] = 1000;
        aRangos[3] = 10000;
        aRangos[4] = 100000;
        aRangos[5] = 1000000;
    }

    public void fillArrays(int i) {
        if(i == 1){
            aDatos = new int[aRangos[c]];
            aOrdenado(aDatos);
        }
        if(i == 2){
            aDatos = new int[aRangos[c]];
            aInvertida(aDatos);
        }
        if(i == 3){
            aDatos = new int[aRangos[c]];
            aSemiOrdenada(aDatos);
        }
        if(i == 4){
            aDatos = new int[aRangos[c]];
            aRandom(aDatos);
        }
        
        c++;
    }

    public void BubbleSort(int[] list) {
        int iAux;
        for (int c1 = 0, l = list.length - 1; c1 < l; c1++) {
            for (int c2 = 0; c2 < l; c2++) {
                if (list[c2] > list[c2 + 1]) {
                    iAux = list[c2];
                    list[c2] = list[c2 + 1];
                    list[c2 + 1] = iAux;
                }
            }
        }
    }
    
    public void BubbleSortOptim(int[] list) {
        int iAux;
        for (int c1 = 0, l = list.length - 1; c1 < l; c1++) {
            for (int c2 = 1, l2 = l - c1; c2 < l2; c2++) {
                if (list[c2] > list[c2 + 1]) {
                    iAux = list[c2];
                    list[c2] = list[c2 + 1];
                    list[c2 + 1] = iAux;
                }
            }
        }
    }

    public void SelectionSort(int[] list) {
        int index;
        int smallerNumber;
        for (int i = 0, l = list.length - 1; i < l; i++) {
            index = i;
            for (int j = i + 1; j < l; j++) {
                if (list[j] < list[index]) {
                    index = j;
                }
            }
            smallerNumber = list[index];
            list[index] = list[i];
            list[i] = smallerNumber;
        }
    }

    public void InsertionSort(int[] list) {
      int j, newValue;
      for (int i = 1, l = list.length; i < l; i++) {
            newValue = list[i];
            j = i;
            while (j > 0 && list[j - 1] > newValue) {
                  list[j] = list[j - 1];
                  j--;
            }
            list[j] = newValue;
      }
    }

    public void ShellSort(int[] list) {

    }

    public void QuickSort(int[] list, int iIzq, int iDer) {
        int i = iIzq;
        int j = iDer;
        int iAux = 0;
        int pivot = list[(i + j) / 2];
        do {
            while (list[i] < pivot) i++;
            while (list[j] > pivot) j--;
            if (i <= j) {
                iAux = list[i];
                list[i++] = list[j];
                list[j--] = iAux;
            }
        } while (i <= j);

        if (iIzq < j) QuickSort(list, iIzq, j);
        if (iDer > i) QuickSort(list, i, iDer);
    }

    public void MergeSort(int[] list, int[] auxList, int iIzq, int iDer) {
        if (iIzq < iDer) {
            int center = (iIzq + iDer) / 2;
            MergeSort(list, auxList, iIzq, center);
            MergeSort(list, auxList, center + 1, iDer);
            Merge(list, auxList, iIzq, center + 1, iDer);
        }
    }

    public void Merge(int[] list, int[] auxList, int iIzq, int iDer, int rightEnd) {
        int leftEnd = iDer - 1;
        int k = iIzq;
        int num = rightEnd - iIzq + 1;

        while (iIzq <= leftEnd && iDer <= rightEnd) {
            if (list[iIzq] <= list[iDer])
                auxList[k++] = list[iIzq++];
            else
                auxList[k++] = list[iDer++];
        }

        while (iIzq <= leftEnd) auxList[k++] = list[iIzq++];
        while (iDer <= rightEnd) auxList[k++] = list[iDer++];
        for (int i = 0; i < num; i++, rightEnd--)
            list[rightEnd] = auxList[rightEnd];
    }
    
    public void HeapSort(int[] list) {

    }
    
    //================ Cargar Datos ===============
    public void aOrdenado(int[] list) {
        for(int i = 0, l = list.length; i < l; i++)
            list[i] = i;
    }
    
    public void aInvertida(int[] list) {
        for(int i = 0, l = list.length; i < l; i++)
            list[i] = list.length - i - 1;
    }
    
    public void aSemiOrdenada(int[] list) {
        Random rnd = new Random();
        for (int i = 0, l = list.length; i < l; i++)
            list[i] = rnd.nextBoolean()?i:rnd.nextInt(500);
    }
    
    public void aRandom(int[] list) {
        Random rnd = new Random();
        for(int i = 0, l = list.length; i < l; i++)
            list[i] = rnd.nextInt(500);
    }
}
