package xiaoye.wang.topcoder

import scala.math._

/**
 * Given an arithmetic progression T1 = a1, a2,...an (n >= 4, <= 1000), now divide every even value with 2,
 * until it's an odd, you'll get a T2, write a function that take a T2, return the minimal original T1
 * From TopCoder
 *
 * @author Zane.Wang
 *
 */
object AfraidOfEven extends App {

  def restoreProgression(arr: Array[Int]): Array[Int] = {
    val (pre, odd, pos) = (arr(0), arr(1), arr(2))
    val (len, max) = (arr.length, arr.max)
    val isAsc = arr.lastIndexOf(max) >= (len - 1).toFloat / 2

    def pow2(n: Int): Int = pow(2,n).toInt
    def ld(n:Int):Int = (log(n) / log(2)).ceil.toInt
    def diff(c: Int, a: Int) = odd * pow2(c) - pre * pow2(a)
    def equation(a: Int, b: Int, c: Int) = odd * pow2(c + 1) == (pre * pow2(a) + pos * pow2(b))
    def predicate(a: Int, b: Int, c: Int): Boolean = equation(a, b, c) && ((diff(c, a) >= 0) == isAsc)

    val (rA, rB, rC) = (0 to ld(max / pre) toStream, 0 to ld(max / pos) toStream, 0 to ld(max / odd) toStream)
    val (a, _, c) = (for (_c <- rC; _a <- rA; _b <- rB) yield (_a, _b, _c)).iterator.find(x => predicate(x._1, x._2, x._3)).get

    val head = arr(0) * pow2(a)
    (0 until len).map(_ * diff(c, a) + head).toArray
  }

  def test = {
    assert(restoreProgression(Array(7, 47, 5, 113, 73, 179, 53)).deep == Array(14, 47, 80, 113, 146, 179, 212).deep)
    assert(restoreProgression(Array(1, 1, 3, 1, 5, 3)).deep == Array(1, 2, 3, 4, 5, 6).deep)
    assert(restoreProgression(Array(999, 999, 999, 999)).deep == Array(999, 999, 999, 999).deep)
    assert(restoreProgression(Array(9, 7, 5, 3, 1)).deep == Array(9, 7, 5, 3, 1).deep)
    assert(restoreProgression(Array(3, 7, 25, 9)).deep == Array(3, 14, 25, 36).deep)
    assert(restoreProgression(Array(1, 43, 54, 65)).deep == Array(32, 43, 54, 65).deep)
    assert(restoreProgression(Array(1, 3, 1, 1)).deep == Array(4, 3, 2, 1).deep)
  }

  test
}