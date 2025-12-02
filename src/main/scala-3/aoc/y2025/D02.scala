package aoc.y2025

import algo.SeqUtil
import aoc.Runner

object D02 extends Runner:
  private def solve(input: IndexedSeq[String])(isOK: Long => Boolean): String =
    val answer = input.mkString.split(',').foldLeft(0L):
      case (acc, str) =>
        val Seq(l, r) = SeqUtil.tokens(str, "-").map(_.toLong)
        acc + (l to r).filter(isOK).sum
    answer.toString
  
  private def maxDivisor(div: Int)(in: Long): Boolean =
    val str = in.toString
    (2 to math.min(div, str.length)).exists: l =>
      val subLen = str.length / l
      str.length % l == 0 && str.dropRight(subLen) == str.drop(subLen)
  
  override def part1(input: IndexedSeq[String]): String = solve(input)(maxDivisor(2))
  override def part2(input: IndexedSeq[String]): String = solve(input)(maxDivisor(Int.MaxValue))
end D02
