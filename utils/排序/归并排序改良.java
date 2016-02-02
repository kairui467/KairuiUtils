package utils.排序;

/**
 * 
 * @Description: 算法—归并排序改良后亿级秒排及排序算法的极致
 * 
 * @author <a href="kairuili@ingenic.com">kairuili</a>
 * 
 * @date 2015年11月23日 上午9:40:34
 * 
 */
@SuppressWarnings("unused")
public class 归并排序改良 {
	private final int CUTOFF = 7;

	private void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
		// if (hi <= lo) return;

		if (hi <= lo + CUTOFF) {
			insertionSort(dst, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(dst, src, lo, mid);
		sort(dst, src, mid + 1, hi);

		// if (!less(src[mid+1], src[mid])) {
		//     for (int i = lo; i <= hi; i++) dst[i] = src[i];
		//     return;
		// }

		// using System.arraycopy() is a bit faster than the above loop
		if (!less(src[mid + 1], src[mid])) {
			System.arraycopy(src, lo, dst, lo, hi - lo + 1);
			return;
		}

		merge(src, dst, lo, mid, hi);
	}

	private void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
		
	}

	private boolean less(Comparable comparable, Comparable comparable2) {
		return false;
	}

	private void insertionSort(Comparable[] a, int lo, int hi) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
	}

	private void exch(Comparable[] a, int j, int i) {
		
	}
}
