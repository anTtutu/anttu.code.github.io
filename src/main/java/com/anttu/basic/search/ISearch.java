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
 *
 * @author hk
 * @version 1.0, 2019年2月26日 上午9:52:39
 * @since JDK1.8
 */
public interface ISearch
{
    /**
     * [简要描述]:顺序查找<br/>
     * [详细描述]:<br/>
     * 算法描述：
     * 从表中的第一个或者是最后一个记录开始，逐个的将表中记录的关键字和给定值进行比较。若某个记录的关键字和给定值相等，则查找成功；若表中所有记录的关键字和给定值都不相等，则查找失败。
     *
     * @author hk
     * @param searchKey
     * @param array
     * @return
     */
    public int sequenceSearch(int searchKey, int[] array);

    /**
     * [简要描述]:二分查找<br/>
     * [详细描述]:<br/>
     * 算法描述：
     * 折半查找的前提条件是在一个有序的序列中。首先确定待查记录所在的区间，然后逐步的缩小范围区间直到找到或者找不到该记录为止。与数学中的二分法一样。
     *
     * @author hk
     * @param searchKey
     * @param array
     * @return
     */
    public int binarysearch(int searchKey, int[] array);

    /**
     * [简要描述]:插值查找<br/>
     * [详细描述]:<br/>
     * 基本思想：基于二分查找算法，将查找点的选择改进为自适应选择，可以提高查找效率。当然，差值查找也属于有序查找。
     * 注：对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择。
     *
     * @author hk
     * @param a
     * @param value
     * @param low
     * @param high
     * @return
     */
    public int insertionSearch(int[] a, int value, int low, int high);

    /**
     * [简要描述]:斐波那契查找<br/>
     * [详细描述]:<br/>
     * 算法描述：
     * 斐波那契查找是根据斐波那契序列的特点对表进行分割。假设表中记录的个数比某个斐波那契数小1，即n=Fn−1n=Fn−1，然后将给定值和表中第Fn−1Fn−1个记录的关键字进行比较。若相等，则查找成功；若给定关键字<<表中第Fn−1Fn−1个记录的关键字，则继续在表中从第一个记录到第Fn−1−1Fn−1−1个记录之间查找；若给定关键字>>表中第Fn−1Fn−1个记录的关键字，则继续在自Fn−1+1Fn−1+1至Fn−1Fn−1的子表中查找。
     *
     * @author hk
     * @param table
     * @param keyWord
     * @return
     */
    public boolean fibonacciSearch(int[] table, int keyWord);

    /**
     * [简要描述]:块查找<br/>
     * [详细描述]:<br/>
     * 首先将查找表分成若干块，在每一块中数据元素的存放是任意的，但块与块之间必须是有序的（假设这种排序是按关键字值递增的，也就是说在第一块中任意一个数据元素的关键字都小于第二块中所有数据元素的关键字，第二块中任意一个数据元素的关键字都小于第三块中所有数据元素的关键字，依次类推）；
     * 建立一个索引表，把每块中最大的关键字值按块的顺序存放在一个辅助数组中，这个索引表也按升序排列；
     * 查找时先用给定的关键字值在索引表中查找，确定满足条件的数据元素存放在哪个块中，查找方法既可以是折半方法，也可以是顺序查找。
     * 再到相应的块中顺序查找，便可以得到查找的结果。
     *
     * @author hk
     * @param index
     * @param st
     * @param key
     * @param m
     * @return
     */
    public int blockSearch(int[] index, int[] st, int key, int m);

    /**
     * [简要描述]:哈希查找<br/>
     * [详细描述]:<br/>
     * 算法思想：
     * 哈希的思路很简单，如果所有的键都是整数，那么就可以使用一个简单的无序数组来实现：将键作为索引，值即为其对应的值，这样就可以快速访问任意键的值。这是对于简单的键的情况，我们将其扩展到可以处理更加复杂的类型的键。
     * 算法流程：
     * 用给定的哈希函数构造哈希表；
     * 根据选择的冲突处理方法解决地址冲突；
     * 常见的解决冲突的方法：拉链法和线性探测法。
     * 在哈希表的基础上执行哈希查找。
     *
     * @author hk
     * @param hash
     * @param hashLength
     * @param key
     * @return
     */
    public int hashSearch(int[] hash, int hashLength, int key);

    /**
     * [简要描述]:动态查找：二叉树查找<br/>
     * [详细描述]:<br/>
     * 定义：二叉排序树或者是空树，或者是具有下列性质的一颗树：
     * 若他的左子树不为空，则左子树上所有结点的值均小于其根节点的值
     * 若他的右子树不为空，则右子树上所有结点的值均大于其根节点的值
     * 他的左右子树也均是二叉排序树
     *
     * @author hk
     * @param sz
     * @param key
     * @return
     */
    public int binaryTreeSearch(int[] sz, int key);
}
