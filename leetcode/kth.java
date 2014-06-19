package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * given two sorted arrays, A, B of size m and n repectively. Find the k-th
 * smallest elements in the union of A and B. You can assume that there are no
 * duplicate elements.
 * 
 * solution1 O(k) solution2 O(logn + logm) - using binary search to perform
 * search on both array
 * 
 * @author xyu
 * 
 */
public class kth {

	static List<Integer> a = new ArrayList<Integer>(
			Arrays.asList(1, 2, 6, 7, 9));
	static List<Integer> b = new ArrayList<Integer>(Arrays.asList(3, 8, 11));

	// something similar to merge sort, O(k) solution
	static int solution1(List<Integer> a, List<Integer> b, int k) {
		int aIdx = 0;
		int bIdx = 0;

		if (k == 1)
			return a.get(aIdx) < b.get(bIdx) ? a.get(aIdx) : b.get(bIdx);
		int step = 0;

		while (aIdx < a.size() && bIdx < b.size()) {
			if (step == k - 1) {
				return a.get(aIdx) < b.get(bIdx) ? a.get(aIdx) : b.get(bIdx);
			}
			if (a.get(aIdx).compareTo(b.get(bIdx)) >= 0) {
				// move b
				bIdx++;
			} else {
				aIdx++;
			}
			step++;
		}

		// either iteration goes to end of a or b
		int newIdx = aIdx != a.size() - 1 ? aIdx : bIdx;
		List<Integer> leftOver = aIdx == a.size() - 1 ? b : a;
		while (newIdx < leftOver.size()) {
			if (step == k - 1) {
				return leftOver.get(newIdx);
			}
			step++;
			newIdx++;
		}

		return -1;
	}

	/**
	 * binary search solution: get the mid point of a and b, then position can
	 * be a +b +1. if position == k, then we are done. if position < k, means k
	 * is in the larger half.
	 * 
	 * @param a
	 * @param b
	 * @param k
	 * @return
	 */
	static int solution2(List<Integer> a, List<Integer> b, int k) {
		int position = 0;
		int aStart = 0;
		int aEnd = a.size();

		int bStart = 0;
		int bEnd = b.size();
		int result = -1;

		while (position != k && aStart <= aEnd && bStart <= bEnd
				&& aStart < a.size() && bStart < b.size()) {
			int a_mid = (aStart + aEnd) / 2;
			int b_mid = (bStart + bEnd) / 2;

			result = a.get(a_mid) > b.get(b_mid) ? b.get(b_mid) : a.get(a_mid);
			position = a_mid + b_mid + 1;
			if (k < position) {
				if (a.get(a_mid) > b.get(b_mid)) {
					aEnd = a_mid - 1;
					bEnd = b_mid;
				} else {
					aEnd = a_mid;
					bEnd = b_mid - 1;

				}

			} else if (k > position) {
				if (a.get(a_mid) > b.get(b_mid)) {
					aStart = a_mid;
					bStart = b_mid + 1;
				} else {
					aStart = a_mid + 1;
					bStart = b_mid;
				}

			}
		}

		return result;
	}

	public static void main(String[] arg) {

		for (int i = 1; i < a.size() + b.size() + 1; i++) {
			System.out.println("k=" + i + ", answer=" + solution1(a, b, i));
		}

		// System.out.println(solution1(a, b, 2));
	}
}
