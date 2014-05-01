/**
 * Created by znw on 4/9/14.
 */

/**
 * Given a string consisting only lowercase alphabets, tell if it's possible to form a palindrome, require O(n) complexity.
 *
 */
object PossiblePalindrome extends App {

  /**
   * group letters by identity, and a possible palinedrome only consists at most 1 odd group size
   * @param s
   * @return
   */
  def isPalindrome(s: String): Boolean = s.groupBy(identity).values.filter(_.size % 2 == 1).size <= 1

  /**
   * if a string is palindrome, then XOR all the letters should be 0 or 1, because
   * the total number of the alphabet is 26 < 32, which is a hint for using the bit masking trick.
   *
   * the 0 - 31 integer can represent 32 kinds of bit mask, 1 << x is to set a bit at x.
   *
      1 <<  0 00000000000000000000000000000001
      1 <<  1 00000000000000000000000000000010
      1 <<  2 00000000000000000000000000000100
      1 <<  3 00000000000000000000000000001000
      1 <<  4 00000000000000000000000000010000
      1 <<  5 00000000000000000000000000100000
      1 <<  6 00000000000000000000000001000000
      1 <<  7 00000000000000000000000010000000
      1 <<  8 00000000000000000000000100000000
      1 <<  9 00000000000000000000001000000000
      1 << 10 00000000000000000000010000000000
      1 << 11 00000000000000000000100000000000
      1 << 12 00000000000000000001000000000000
      1 << 13 00000000000000000010000000000000
      1 << 14 00000000000000000100000000000000
      1 << 15 00000000000000001000000000000000
      1 << 16 00000000000000010000000000000000
      1 << 17 00000000000000100000000000000000
      1 << 18 00000000000001000000000000000000
      1 << 19 00000000000010000000000000000000
      1 << 20 00000000000100000000000000000000
      1 << 21 00000000001000000000000000000000
      1 << 22 00000000010000000000000000000000
      1 << 23 00000000100000000000000000000000
      1 << 24 00000001000000000000000000000000
      1 << 25 00000010000000000000000000000000
      1 << 26 00000100000000000000000000000000
      1 << 27 00001000000000000000000000000000
      1 << 28 00010000000000000000000000000000
      1 << 29 00100000000000000000000000000000
      1 << 30 01000000000000000000000000000000
      1 << 31 10000000000000000000000000000000
      1 << 32 00000000000000000000000000000001

   * because x ^ x = 0, so even times of XOR operations on the same bit mask should return 0
   * so if the literal is a palindrome, it should contain 0 bit or 1 bit count after XOR operation.
   * because two consecutive integers will only be different in last or or two bits:
      001
      010
      011
      100
   *  the last set bit of x is always corresponding to a 0 bit to it's previous number, therefore:
   *  x & (x - 1) can remove 1 set bit from left of x.
   *  this is used to count the set bit.
   *

   * @param s
   * @return
   */
  def isPalindrome2(s: String): Boolean = {
    var x: Int = s.map(c => 1 << (c - 'a')).reduceLeft(_ ^ _)
    if (x == 0)
      true
    else {
      var c = 0
      while (x > 0) {
        // count bit
        x = x & (x - 1)
        c = c + 1
      }
      if (c <= 1) true else false
    }
  }

  /**
   * print the shift tables
   */
  def leftShift1Table = {
    val arr = Array.tabulate(33)(1 << _).map(_.toBinaryString).map(x => Array.fill(32 - x.length)("0").mkString + x)
    for((v,i) <- arr.zipWithIndex) {
      println("1 << " + (if (i < 10) " " + i else i ) + " " + v)
    }
  }

  println(isPalindrome("abb"))
  println(isPalindrome("superbrepus"))

  println(isPalindrome2("abb"))
  println(isPalindrome2("superbrepus"))
  println(isPalindrome2("uperbrepus"))
  println(isPalindrome2("333"))


}
