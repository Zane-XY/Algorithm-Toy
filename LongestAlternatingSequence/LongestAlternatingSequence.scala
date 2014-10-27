package codility.LongestAlternatingSequence

object LongestAlternatingSequence {

  def checkAlt(l: Array[Int]): Boolean = l match {
    case Array(a, b, c) if (a >= 0 && b <= 0 && c >= 0) || (a <= 0 && b >= 0 && c <= 0) => true
    case _ => false
  }

  def pickPair(seq: scala.collection.Seq[Boolean]): (Int, Int) = {
    var i = 0
    val buff = scala.collection.mutable.ListBuffer[(Int, Int)]()
    while (i < seq.length) {
      val n = seq.segmentLength(p => p, i)
      buff.append(n -> i)
      i = i + n + 1
    }
    buff.maxBy(_._1)
  }

  def solution(arr: Array[Int]): Int = {
    val r = arr.sliding(3).map(checkAlt).toSeq
    val (len, index) = pickPair(r)
    len + 2
  }

  def test() = {
    val arr = Array(5, 4, -3, 2, 0, 1, -1, 0, 2, -3, 4)
    val r = solution(arr)
    println("max length : " + r)
  }

  test()
}

