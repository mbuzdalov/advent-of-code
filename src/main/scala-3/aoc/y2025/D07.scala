package aoc.y2025

import algo.Loops.*
import aoc.Runner

object D07 extends Runner:
  private def solve(input: IndexedSeq[String]): (Int, Long) =
    val row = input(0).map(ch => if ch == 'S' then 1L else 0L).toArray
    val next = Array.ofDim[Long](row.length)
    var splitCount = 0
    foreach(1, input.length): r =>
      val line = input(r)
      java.util.Arrays.fill(next, 0L)
      foreach(0, line.length): c =>
        if row(c) > 0 && line(c) == '^' then
          splitCount += 1
          next(c - 1) += row(c)
          next(c + 1) += row(c)
        else next(c) += row(c)
      System.arraycopy(next, 0, row, 0, next.length)
    (splitCount, row.sum)
  
  override def part1(input: IndexedSeq[String]): String = solve(input)._1.toString
  override def part2(input: IndexedSeq[String]): String = solve(input)._2.toString
end D07
