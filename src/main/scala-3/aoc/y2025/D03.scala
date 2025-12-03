package aoc.y2025

import algo.Loops
import aoc.Runner

object D03 extends Runner:
  private def solveLine(length: Int)(s: String): Long =
    val n = s.length
    val arr = Array.ofDim[Long](n + 1, 2)
    Loops.foreach(0, length): j =>
      Loops.foreach(0, n): i =>
        arr(i + 1)(1 - (j & 1)) = math.max(arr(i)(1 - (j & 1)), arr(i)(j & 1) * 10 + s(i) - '0')
    arr(n)(length & 1)

  override def part1(input: IndexedSeq[String]): String = input.map(solveLine(2)).sum.toString
  override def part2(input: IndexedSeq[String]): String = input.map(solveLine(12)).sum.toString
end D03
