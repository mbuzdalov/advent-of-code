package aoc.y2023

import algo.SeqUtil
import aoc.Runner

object D13 extends Runner:
  private def vMismatches(input: IndexedSeq[String], axis: Int): Int =
    val range = math.max(0, 2 * axis - input(0).length) until axis
    input.map(line => range count (c => line(c) != line(2 * axis - 1 - c))).sum

  private def hMismatches(input: IndexedSeq[String], axis: Int): Int =
    val range = math.max(0, 2 * axis - input.size) until axis
    range.map(r => input(r).indices.count(c => input(r)(c) != input(2 * axis - 1 - r)(c))).sum

  private def solve(mismatches: Int)(input: IndexedSeq[String]): Int =
    val horizontal = (1 until input.size).filter(i => hMismatches(input, i) == mismatches) :+ 0
    val vertical = (1 until input(0).length).filter(i => vMismatches(input, i) == mismatches) :+ 0
    vertical(0) + 100 * horizontal(0)

  override def part1(input: IndexedSeq[String]): String =
    SeqUtil.splitBySeparator(input, _.isEmpty).map(solve(0)).sum.toString
  override def part2(input: IndexedSeq[String]): String =
    SeqUtil.splitBySeparator(input, _.isEmpty).map(solve(1)).sum.toString
end D13
