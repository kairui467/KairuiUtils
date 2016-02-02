package utils.排序;

/**
 *  * 希尔排序  * @author  *  
 */
public class 希尔排序{
    public void sort(Comparable[] a) {
        // 将a[]按升序排列
        int N = a.length;
        int h = 1;
        // 1,4,13,40,121,364,1093,...
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        // 将数组变为h有序
        while (h >= 1) {
            // 将a[i]插入到a[i-h],a[i-2*h],a[i-3*h]...之中
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }

    private void exch(Comparable[] a, int j, int i) {
        // TODO Auto-generated method stub

    }

    private boolean less(Comparable comparable, Comparable comparable2) {
        // TODO Auto-generated method stub
        return false;
    }
}