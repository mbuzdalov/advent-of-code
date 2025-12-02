package aoc.y2025

import algo.{Loops, SeqUtil}
import aoc.Runner

/**
 * This was the first dumb idea to just apply filter to the range. It worked for the data though.
 *
 * Can it be faster?
 *
 * At least sqrt(range) is possible, because we would only need to iterate over the non-repeated part,
 * generate the rest and check for the boundaries. Trickier for Part 2 because it still needs to check all lengths
 * but also needs to deduplicate divisible cases (like not counting AAAA again).
 *
 * It should also be possible to do in O(poly(number length)), but the code would be enormous already for Part 1.
 */
object D02 extends Runner:
  private def solve(input: IndexedSeq[String])(isOK: Long => Boolean): String =
    val answer = input.mkString.split(',').foldLeft(0L):
      case (acc, str) =>
        val Seq(l, r) = SeqUtil.tokens(str, "-").map(_.toLong)
        acc + (l to r).filter(isOK).sum
    answer.toString
  
  private def maxDivisor(div: Int)(in: Long): Boolean =
    val str = in.toString
    Loops.any(2, 1 + math.min(div, str.length)): l =>
      val subLen = str.length / l
      str.length % l == 0 && str.dropRight(subLen) == str.drop(subLen)
  
  override def part1(input: IndexedSeq[String]): String = solve(input)(maxDivisor(2))
  override def part2(input: IndexedSeq[String]): String = solve(input)(maxDivisor(Int.MaxValue))
end D02
