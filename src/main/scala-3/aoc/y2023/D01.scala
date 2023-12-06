package aoc.y2023

import aoc.Runner

object D01 extends Runner:
  private val digits = (0 to 9).map(i => (s"$i", i))
  private val words = Seq("---", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine").zipWithIndex

  private def decode(digits: Seq[(String, Int)])(s: String): Int =
    val first = digits.map(d => (d._2, s.indexOf(d._1))).filter(_._2 >= 0).minBy(_._2)._1
    val last = digits.map(d => (d._2, s.lastIndexOf(d._1))).maxBy(_._2)._1
    first * 10 + last

  override def part1(input: IndexedSeq[String]): String = input.map(decode(digits)).sum.toString
  override def part2(input: IndexedSeq[String]): String = input.map(decode(digits ++ words)).sum.toString
end D01
