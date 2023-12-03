
package aoc.y2022

import aoc.Runner

object D06 extends Runner:
  private def solve(nSeek: Int)(line: String): Int =
    line.sliding(nSeek).zipWithIndex.find(p => p._1.take(nSeek).distinct.length == nSeek).get._2 + nSeek

  override def part1(input: Seq[String]): String = input.map(solve(4)).mkString(" ")
  override def part2(input: Seq[String]): String = input.map(solve(14)).mkString(" ")
end D06
