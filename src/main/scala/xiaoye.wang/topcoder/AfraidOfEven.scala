package xiaoye.wang.topcoder

/**
 * Given an arithmetic progression T1 = a1, a2,...an (n >= 4, <= 1000), now divide every even value with 2,
 * until it's an odd, you'll get a T2, write a function that take a T2, return the minimal original T1
 *
 */
object AfraidOfEven extends App {

  def restoreProgression(arr: Array[Int]): Array[Int] = {
    val (pre, odd, pos) = (arr(0), arr(1), arr(2))
    val (len, max) = (arr.length, arr.max)
    val (rA, rB, rC) = ((0 to (max / pre)).toStream, (0 to (max / pos)).toStream, (0 to (max / odd)).toStream)

    def equation(a: Int, b: Int, c: Int) = odd * (BigInt(2).pow(c + 1)) == (pre * (BigInt(2).pow(a)) + pos * (BigInt(2).pow(b)))

    val (a, b, c) = (for (c <- rC; a <- rA; b <- rB) yield (a, b, c)).iterator.find(p => equation(p._1, p._2, p._3)).get

    val diff = (odd * (BigInt(2).pow(c)) - (pre * (BigInt(2).pow(a)))).toInt

    val head = arr(0) * BigInt(2).pow(a).toInt
    (0 until len).map(_ * diff + head).toArray
  }

  def test = {
    assert(restoreProgression(Array(7, 47, 5, 113, 73, 179, 53)).deep == Array(14, 47, 80, 113, 146, 179, 212).deep)
    assert(restoreProgression(Array(1, 1, 3, 1, 5, 3)).deep == Array(1, 2, 3, 4, 5, 6).deep)
    assert(restoreProgression(Array(999, 999, 999, 999)).deep == Array(999, 999, 999, 999).deep)
    assert(restoreProgression(Array(9, 7, 5, 3, 1)).deep == Array(9, 7, 5, 3, 1).deep)
    assert(restoreProgression(Array(3, 7, 25, 9)).deep == Array(3, 14, 25, 36).deep)
    assert(restoreProgression(Array(1, 43, 54, 65)).deep == Array(32, 43, 54, 65).deep)
  }

  test
}