/*This program uses four different sorting methods to indicate the efficiency of each of those algorithms. After sorting the array, 
 * the time it took the program to execute said algorithm is outputed to the console. 
 * 
 * Kylie Heiland
 * 
 * Project 1
 * 
 * CSCI251_VA
 * 
 * 8/25/2022
 * 
 */

public class MySorts extends CSCI251ProjOne
{
    
//Insertion sort
public static void insertSort(int[] arr)
{
    int i;
    for(i = 0; i < arr.length; i++) //Say the arr.length was 6: i < 4
    { //Loops through every element in the array.
      if((i != arr.length - 1) && (arr[i] > arr[i+1]))
      { //If the two elements are not in ascending order, they are swapped.
          int temp = arr[i];
          arr[i] = arr[i+1];
          arr[i+1] = temp;
          
          if(i != 0)
          { //If it is not the first element
              for(int j = i-1, a = i; j >= 0; j--)
              { //Loops through the previous elements 
                  if(arr[j] > arr[a])
                  { //If one of the previous elements is bigger than the current element, they are swapped.
                     temp = arr[a];
                     arr[a] = arr[j];
                     arr[j] = temp;
                  }
                  else
                      continue;
                a--;
              }
            }
      }
      else if(i == arr.length)//The last element in the array.
      {
          for(int j = i-1, a = i; j >= 0; j--)
              { //Loops through the previous elements 
                  if(arr[j] > arr[a])
                  { //If one of the previous elements is bigger than the current element, they are swapped.
                     int temp = arr[a];
                     arr[a] = arr[j];
                     arr[j] = temp;
                  }
                  else
                      continue;
                a--;
              }
      }
    }  
}




//Selection Sort
public    static    void    selectSort(int    []    arr)
{
    for(int i = 0; i < arr.length - 1; i++)
    { //Loops through every element in the array, except the last one.
        int currentMin = arr[i];
        int currentMinIndex = i;
        
        for(int j = i + 1; j < arr.length; j++)
        { //Loops through every element after the current element in the array.
            if(currentMin > arr[j])
            { //If one of the elements has a lesser value than the current element, the currentMin variable is now set to this element and the currentMinIndex is set to the index j.
                currentMin = arr[j];
                currentMinIndex = j;
            }
        }
        
        if(currentMinIndex != i)
        { //Executes if another element has a smaller value than arr[i].
            arr[currentMinIndex] = arr[i]; //The other element sets itself equal to the initial element with i.
            arr[i] = currentMin; //The element at index i is now set to the smallest value among the remaining elements. 
        }
    }
}

//Quick sort
public static void quickSort(int[] arr)
{
    if(arr.length > 0)
    { //If there is at least one element
        quickSortRecursive(arr, 0, arr.length - 1);    
    }
}


//Sort the portion of giving array arr, from begin to end
private static void quickSortRecursive(int [] arr, int begin, int end)
{
    if(begin >= end)
    {
        return;
    }
    
    int pIndex = pivot(arr, begin, end); //Pivot point is at end.
        
    //int pivot = arr[pIndex];
        
    int i = partition(arr, begin, pIndex); //Left pointer

    if(i > 0)
    {
        quickSortRecursive(arr, begin, i - 1);
    }
    
    if(i < end && i >= 0)
    {
        quickSortRecursive(arr, i + 1, end);
    }    
}

private static int partition(int[] arr, int begin, int pIndex)
{
    int i = begin; //Left pointer
    int finish = pIndex - 1; //Right pointer, which is to the left of the pivot.
    
    while(i < finish)
    {
        
        while(arr[i] <= arr[pIndex] && i < finish)
        { //Loops through until it finds an index that contains an element that is larger than the pivot point.
            //If there is no element that is larger, the loop ends.
            i++;
        }
        
        while(arr[finish] >= arr[pIndex] && i < finish)
        { //Loops through until it finds an index that contains an element that is smaller than the pivot point.
            //If there is no element that is smaller, the loop ends.
            finish--;
        }
    
        if(i != finish)
        { //If the two are at the same index, there is no swap.
            swap(arr, i, finish);
        }
    }
    
    if(arr[i] > arr[pIndex])
    { //
        swap(arr, i, pIndex);
    }
    else
    {
        i = pIndex;
    }
    return i;
}

//Swaps the elements at the specific indexes.
private static void swap(int[] arr, int ind1, int ind2)
{
    int temp = arr[ind1];
    arr[ind1] = arr[ind2];
    arr[ind2] = temp;
}

//Find the pivot point for quick sort.
private static int pivot(int[] arr, int begin, int end)
{
    return end;
}


public    static    void    mergeSort(int[] arr)
{
    mergeSortRecursive(arr, 0, arr.length - 1);
}

//Sort the portion of giving array arr, from begin to end. 
private static void mergeSortRecursive(int [] arr, int begin, int end)
{
    if(arr.length < 2)
    { //If there is only one element, it is sorted already.
        return;
    }
    int middle = arr.length/2; //Finds the middle (or close the middle) element of the list. 
    
    merge(arr, begin, middle, end);
    
    
}

//Merge two sorted portions of giving array arr, namely, from start to middle
//and from middle + 1 to end into one sorted portion, namely, from start to end
private static void merge(int [] arr, int start, int middle, int end)
{
    //Left array
    int left[] = new int [middle];
    
    //Right array
    int right[] = new int [arr.length - middle];
    
    //Fill left array
    for(int i = 0; i < left.length; i++)
    {
        left[i] = arr[i];
    }
    
    
    
    
    //Fill right array
    for(int i = middle, k = 0; i < arr.length; i++, k++)
    {
        right[k] = arr[i];
    }
    
    
    if(left.length > 1)
    {
        mergeSortRecursive(left, 0, left.length);
    }
    if(right.length > 1)
    {
        mergeSortRecursive(right, 0, right.length);
    }
    
    //Everything should be separated.
    int leftSize = left.length;
    int rightSize = right.length;
    
    int k = 0; //Index for original array.
    int j = 0; //Index for right
    int i = 0; 
    for(; i < left.length && j < right.length; i++)
    {//Merges in ascending order until left or right run out of elements.
        if(left[i] > right[j])
        {
            arr[k] = right[j];
            j++;
            i--;
            k++;
        }
        else
        {
            arr[k] = left[i];
            k++;
        }
    }
    
    //If there are still some elements in left array.
    for(; i < left.length; i++)
    {
        arr[k] = left[i];
        k++;
    }
    
    
    //If there are still some elements in right array.
    for(; j < right.length;  j++)
    {
        arr[k] = right[j];
        k++;
    }
}
}

/* OUTPUT
 * 
Enter you choice: 
4
Execution time for Quick Sort is: 14 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
4
Execution time for Quick Sort is: 15 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
4
Execution time for Quick Sort is: 13 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
5
Execution time for Merge Sort is: 118 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
5
Execution time for Merge Sort is: 24 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
5
Execution time for Merge Sort is: 23 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
5
Execution time for Merge Sort is: 23 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
5
Execution time for Merge Sort is: 23 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
1
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
5
Execution time for Merge Sort is: 23 milliseconds
***************************
*          MENU           *
* 1. Fill Array           *
* 2. Select Sort          *
* 3. Insert Sort          *
* 4. Quick Sort           *
* 5. Merge Sort           *
* 6. Quit                 *
***************************
Enter you choice: 
6
Make sure that you have good documentation!
 */