package codility.MaxDiffWithinSequence

object MaxDiffInSeqence extends App {

  def amplitude(arr: Array[Int]): Int = {
    val (min, max): (Int, Int) = minAndMax(arr)
    max - min
  }

  def minAndMax[T <% Ordered[T]](arr: Array[T]): (T, T) = arr match {
    case Array(a) => (a, a)
    case Array(a, b, sub@_*) =>
      val (x, y) = if (a > b) (b, a) else (a, b)
      ((x, y) /: sub) {
        case ((x, y), c) => if (c >= y) (x, c) else if (c <= x) (c, y) else (x, y)
      }
    case _ => throw new IllegalArgumentException("array shouldn't be empty")
  }

  println(amplitude(Array(1, -999, 2, -100, 999)))
}
