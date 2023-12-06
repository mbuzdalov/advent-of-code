package aoc.y2023

import algo.SeqUtil
import aoc.Runner

object D06 extends Runner:
  private def maximize(time: Int, distance: Long): Int = (0 to time).count(t => t.toLong * (time - t) > distance)

  override def part1(input: IndexedSeq[String]): String =
    val times = SeqUtil.tokenMap(input(0), "Time: ", _.toInt)
    val distances = SeqUtil.tokenMap(input(1), "Distance: ", _.toInt)
    val solutions = for i <- times.indices yield maximize(times(i), distances(i))
    solutions.product.toString

  override def part2(input: IndexedSeq[String]): String =
    val time = input(0).split(" ").tail.mkString("").toInt
    val distance = input(1).split(" ").tail.mkString("").toLong
    maximize(time, distance).toString
end D06
