package aoc.y2022

import aoc.Runner

object D03 extends Runner:
  private def priority(char: Char): Int = if (char <= 'Z') 27 + char - 'A' else 1 + char - 'a'

  private def priority1(str: String): Int =
    val (first, second) = str.splitAt(str.length / 2)
    priority(first.intersect(second).charAt(0))

  private def priority2(elves: Seq[String]): Int = priority(elves.reduce(_ intersect _).charAt(0))

  override def part1(input: Seq[String]): String = input.map(priority1).sum.toString
  override def part2(input: Seq[String]): String = input.grouped(3).map(priority2).sum.toString
end D03
