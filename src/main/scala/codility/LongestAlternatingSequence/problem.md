A non-empty zero-indexed array A consisting of N integers is given. A pair of
integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
A slice is called alternating if the signs of its consecutive elements
alternate. More precisely, slice (P, Q) is alternating if:

* A[P] ≥ 0, A[P+1] ≤ 0, A[P+2] ≥ 0, and so on, up to A[Q], or
* A[P] ≤ 0, A[P+1] ≥ 0, A[P+2] ≤ 0, and so on, up to A[Q].

Note that 0 is treated as both positive and negative.

Write a function:

    def solution(A)

that, given an array A consisting of N integers, returns the size of the largest alternating slice in A.

For example, given array A such that:

    A[0] =  5    A[1]  = 4    A[2]  = -3
    A[3] =  2    A[4]  = 0    A[5]  =  1
    A[6] = -1    A[7]  = 0    A[8]  =  2
    A[9] = -3    A[10] = 4    A[11] = -5

the function should return 7, because:

* (1, 7) is an alternating slice of length 7,
* (7, 11) is an alternating slice of length 5, and
* all other alternating slices in A are shorter.

Assume that:

* N is an integer within the range [0..100,000];
* each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].

Complexity:

* expected worst-case time complexity is O(N);
* expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
