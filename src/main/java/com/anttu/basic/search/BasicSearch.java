/**
 * 创建时间:2019年2月26日
 */

package com.anttu.basic.search;

/**
 * [简要描述]:网上的查找算法学习<br/>
 * [详细描述]:<br/>
 * http://www.cnblogs.com/maybe2030/p/4715035.html
 * https://xiaojun-it.iteye.com/blog/2291852
 * https://blog.csdn.net/weixin_39241397/article/details/79344179
 * https://github.com/hnyer/Search-algorithm
 * https://blog.csdn.net/wqc_CSDN/article/details/52691019
 *
 * @author hk
 * @version 1.0, 2019年2月26日 上午10:16:55
 * @since JDK1.8
 */
public class BasicSearch implements ISearch
{

    /**
     * [简要描述]:顺序查找<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param searchKey
     * @param array
     * @return
     * @exception
     *            @see
     *                com.anttu.basic.search.ISearch#sequenceSearch(int, int[])
     */
    @Override
    public int sequenceSearch(int searchKey, int[] array)
    {
        if (array == null || array.length < 1)
        {
            return -1;
        }
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == searchKey)
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * [简要描述]:二分查找/折半查找<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param searchKey
     * @param array
     * @return
     * @exception
     *            @see
     *                com.anttu.basic.search.ISearch#binarysearch(int, int[])
     */
    @Override
    public int binarysearch(int searchKey, int[] array)
    {
        int low = 0;
        int high = array.length - 1;
        while (low <= high)
        {
            int middle = (low + high) / 2;
            if (searchKey == array[middle])
            {
                return middle;
            }
            else if (searchKey < array[middle])
            {
                high = middle - 1;
            }
            else
            {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * [简要描述]:插值查找<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param a
     * @param value
     * @param low
     * @param high
     * @return
     * @exception
     *            @see
     *                com.anttu.basic.search.ISearch#insertionSearch(int[], int, int, int)
     */
    @Override
    public int insertionSearch(int[] a, int value, int low, int high)
    {
        int mid = low + (value - a[low]) / (a[high] - a[low]) * (high - low);
        if (a[mid] == value)
        {
            return mid;
        }
        if (a[mid] > value)
        {
            return insertionSearch(a, value, low, mid - 1);
        }
        if (a[mid] < value)
        {
            return insertionSearch(a, value, mid + 1, high);
        }
        return -1;
    }

    /**
     * [简要描述]:斐波那契查找<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param table
     * @param keyWord
     * @return
     * @exception
     *            @see
     *                com.anttu.basic.search.ISearch#fibonacciSearch(int[], int)
     */
    @Override
    public boolean fibonacciSearch(int[] table, int keyWord)
    {
        // 确定需要的斐波那契数
        int i = 0;
        while (getFibonacci(i) - 1 == table.length)
        {
            i++;
        }
        // 开始查找
        int low = 0;
        int height = table.length - 1;
        while (low <= height)
        {
            int mid = low + getFibonacci(i - 1);
            if (table[mid] == keyWord)
            {
                return true;
            }
            else if (table[mid] > keyWord)
            {
                height = mid - 1;
                i--;
            }
            else if (table[mid] < keyWord)
            {
                low = mid + 1;
                i -= 2;
            }
        }
        return false;
    }

    /**
     * 得到第n个斐波那契数
     *
     * @return
     */
    public static int getFibonacci(int n)
    {
        int res = 0;
        if (n == 0)
        {
            res = 0;
        }
        else if (n == 1)
        {
            res = 1;
        }
        else
        {
            int first = 0;
            int second = 1;
            for (int i = 2; i <= n; i++)
            {
                res = first + second;
                first = second;
                second = res;
            }
        }
        return res;
    }

    /**
     * [简要描述]:块查找<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param index
     * @param st
     * @param key
     * @param m
     * @return
     * @exception
     *            @see
     *                com.anttu.basic.search.ISearch#blockSearch(int[], int[], int, int)
     */
    @Override
    public int blockSearch(int[] index, int[] st, int key, int m)
    {
        // 在序列st数组中，用分块查找方法查找关键字为key的记录
        // 1.在index[ ] 中折半查找，确定要查找的key属于哪个块中
        int i = binarysearch(key, index);
        if (i >= 0)
        {
            int j = i > 0 ? i * m : i;
            int len = (i + 1) * m;
            // 在确定的块中用顺序查找方法查找key
            for (int k = j; k < len; k++)
            {
                if (key == st[k])
                {
                    System.out.println("查询成功");
                    return k;
                }
            }
        }
        System.out.println("查找失败");
        return -1;
    }

    /**
     * [简要描述]:哈希查找<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param hash
     * @param hashLength
     * @param key
     * @return
     * @exception
     *            @see
     *                com.anttu.basic.search.ISearch#hashSearch(int[], int, int)
     */
    @Override
    public int hashSearch(int[] hash, int hashLength, int key)
    {
        // 哈希函数
        int hashAddress = key % hashLength;

        // 指定hashAdrress对应值存在但不是关键值，则用开放寻址法解决
        while (hash[hashAddress] != 0 && hash[hashAddress] != key)
        {
            hashAddress = (++hashAddress) % hashLength;
        }

        // 查找到了开放单元，表示查找失败
        if (hash[hashAddress] == 0)
        {
            return -1;
        }
        return hashAddress;
    }

    /***
     * 数据插入Hash表
     *
     * @param hash
     *            哈希表
     * @param hashLength
     * @param data
     */
    public static void insertHash(int[] hash, int hashLength, int data)
    {
        // 哈希函数
        int hashAddress = data % hashLength;

        // 如果key存在，则说明已经被别人占用，此时必须解决冲突
        while (hash[hashAddress] != 0)
        {
            // 用开放寻址法找到
            hashAddress = (++hashAddress) % hashLength;
        }

        // 将data存入字典中
        hash[hashAddress] = data;
    }

    /**
     * [简要描述]:二叉树查找<br/>
     * [详细描述]:<br/>
     *
     * @author hk
     * @param sz
     * @param key
     * @return
     * @exception
     *            @see
     *                com.anttu.basic.search.ISearch#binaryTreeSearch(int[], int)
     */
    @Override
    public int binaryTreeSearch(int[] sz, int key)
    {
        BinaryTree bt = CreateBinaryTree(sz);
        return find(bt, key);
    }

    public static BinaryTree CreateBinaryTree(int[] sz)
    {
        BinaryTree bt = new BinaryTree(sz[0], 0);
        for (int i = 1; i < sz.length; i++)
        {
            InsertNode(bt, sz[i], i);
        }
        return bt;
    }

    private static void InsertNode(BinaryTree bt, int key, int index)
    {
        BinaryTree parent = null;
        while (bt != null)
        {
            parent = bt;
            if (bt.data > key)
            {
                bt = bt.left;
            }
            else
            {
                bt = bt.right;
            }
        }

        bt = new BinaryTree(key, index);
        bt.setParent(parent);
    }

    public static int find(BinaryTree bt, int key)
    {
        while (bt != null)
        {
            if (bt.data == key)
            {
                return bt.index;
            }
            else if (bt.data > key)
            {
                bt = bt.left;
            }
            else
            {
                bt = bt.right;
            }
        }
        return -1;

    }

    public static void LDR_BT(BinaryTree bt)
    {
        if (bt != null)
        {
            LDR_BT(bt.left);
            System.out.print(bt.data + " ");
            LDR_BT(bt.right);
        }
    }

    static class BinaryTree
    {
        int data;
        int index;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int data, int index, BinaryTree left, BinaryTree right)
        {
            this.data = data;
            this.index = index;
            this.left = left;
            this.right = right;
        }

        public BinaryTree(int data, int index)
        {
            this.data = data;
            this.index = index;
        }

        public void setParent(BinaryTree parent)
        {
            if (this.data < parent.data)
            {
                parent.left = this;
            }
            else
            {
                parent.right = this;
            }
        }
    }
}
