/**
 * 创建时间:2019年2月26日
 */

package com.anttu.basic.sort;

import java.util.Arrays;

/**
 * [简要描述]:来源网上的基础排序算法学习<br/>
 * [详细描述]:<br/>
 * https://blog.csdn.net/dQCFKyQDXYm3F8rB0/article/details/87910210
 *
 * @author hk
 * @version 1.0, 2019年2月26日 上午9:07:03
 * @since JDK1.8
 */
public class BasicSort implements IArraySort
{
    /**
     * [简要描述]:冒泡排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#bubbleSort(int[])
     */
    @Override
    public int[] bubbleSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++)
        {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                    flag = false;
                }
            }

            if (flag)
            {
                break;
            }
        }
        return arr;
    }

    /**
     * [简要描述]:选择排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#selectionSort(int[])
     */
    @Override
    public int[] selectionSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++)
        {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[j] < arr[min])
                {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min)
            {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
        return arr;
    }

    /**
     * [简要描述]:插入排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#insertSort(int[])
     */
    @Override
    public int[] insertSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++)
        {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1])
            {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i)
            {
                arr[j] = tmp;
            }
        }
        return arr;
    }

    /**
     * [简要描述]:希尔排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#shellSort(int[])
     */
    @Override
    public int[] shellSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int gap = 1;
        while (gap < arr.length)
        {
            gap = gap * 3 + 1;
        }

        while (gap > 0)
        {
            for (int i = gap; i < arr.length; i++)
            {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp)
                {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }

        return arr;
    }

    /**
     * [简要描述]:归并排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#mergeSort(int[])
     */
    @Override
    public int[] mergeSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2)
        {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(left, right);
    }

    protected int[] merge(int[] left, int[] right)
    {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0)
        {
            if (left[0] <= right[0])
            {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            }
            else
            {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0)
        {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0)
        {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    /**
     * [简要描述]:快速排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#quickSort(int[])
     */
    @Override
    public int[] quickSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right)
    {
        if (left < right)
        {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right)
    {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++)
        {
            if (arr[i] < arr[pivot])
            {
                quickSwap(arr, i, index);
                index++;
            }
        }
        quickSwap(arr, pivot, index - 1);
        return index - 1;
    }

    private void quickSwap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * [简要描述]:堆排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#heapSort(int[])
     */
    @Override
    public int[] heapSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;

        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--)
        {
            quickSwap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private void buildMaxHeap(int[] arr, int len)
    {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--)
        {
            heapify(arr, i, len);
        }
    }

    private void heapify(int[] arr, int i, int len)
    {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest])
        {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest])
        {
            largest = right;
        }

        if (largest != i)
        {
            heapSwap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    private void heapSwap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * [简要描述]:计数排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#countingSort(int[])
     */
    @Override
    public int[] countingSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxValue = getMaxValue(arr);

        return countingSort(arr, maxValue);
    }

    private int[] countingSort(int[] arr, int maxValue)
    {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];

        for (int value : arr)
        {
            bucket[value]++;
        }

        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++)
        {
            while (bucket[j] > 0)
            {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }

    private int getMaxValue(int[] arr)
    {
        int maxValue = arr[0];
        for (int value : arr)
        {
            if (maxValue < value)
            {
                maxValue = value;
            }
        }
        return maxValue;
    }

    /**
     * [简要描述]:桶排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#bucketSort(int[])
     */
    @Override
    public int[] bucketSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return bucketSort(arr, 5);
    }

    private int[] bucketSort(int[] arr, int bucketSize) throws Exception
    {
        if (arr.length == 0)
        {
            return arr;
        }

        int minValue = arr[0];
        int maxValue = arr[0];
        for (int value : arr)
        {
            if (value < minValue)
            {
                minValue = value;
            }
            else if (value > maxValue)
            {
                maxValue = value;
            }
        }

        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;
        int[][] buckets = new int[bucketCount][0];

        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++)
        {
            int index = (int) Math.floor((arr[i] - minValue) / bucketSize);
            buckets[index] = arrAppend(buckets[index], arr[i]);
        }

        int arrIndex = 0;
        for (int[] bucket : buckets)
        {
            if (bucket.length <= 0)
            {
                continue;
            }
            // 对每个桶进行排序，这里使用了插入排序
            bucket = insertSort(bucket);
            for (int value : bucket)
            {
                arr[arrIndex++] = value;
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrAppend(int[] arr, int value)
    {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     * [简要描述]:基数排序<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sourceArray
     * @return
     * @throws Exception
     * @exception
     *            @see
     *                com.anttu.basic.sort.IArraySort#radixSort(int[])
     */
    @Override
    public int[] radixSort(int[] sourceArray) throws Exception
    {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxDigit = getMaxDigit(arr);
        return radixSort(arr, maxDigit);
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr)
    {
        int maxValue = getRadixMaxValue(arr);
        return getNumLenght(maxValue);
    }

    private int getRadixMaxValue(int[] arr)
    {
        int maxValue = arr[0];
        for (int value : arr)
        {
            if (maxValue < value)
            {
                maxValue = value;
            }
        }
        return maxValue;
    }

    protected int getNumLenght(long num)
    {
        if (num == 0)
        {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10)
        {
            lenght++;
        }
        return lenght;
    }

    private int[] radixSort(int[] arr, int maxDigit)
    {
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10)
        {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++)
            {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter)
            {
                for (int value : bucket)
                {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    private int[] arrayAppend(int[] arr, int value)
    {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
}
