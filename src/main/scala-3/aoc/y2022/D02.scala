package aoc.y2022

import aoc.Runner

object D02 extends Runner:
  private final val Rock = 0
  private final val Paper = 1
  private final val Scissors = 2

  private def play(m1: Int, m2: Int): Int =
    val diff = m1 - m2
    if diff > 1 then diff - 3 else if diff < -1 then diff + 3 else diff

  private def cost1(line: String): Int =
    val m2 = line(0) match
      case 'A' => Rock
      case 'B' => Paper
      case 'C' => Scissors
    val m1 = line(2) match
      case 'X' => Rock
      case 'Y' => Paper
      case 'Z' => Scissors
    (m1 + 1) + 3 * play(m1, m2) + 3

  private def cost2(line: String): Int =
    val m2 = line(0) match
      case 'A' => Rock
      case 'B' => Paper
      case 'C' => Scissors
    val expCost = line(2) match
      case 'X' => -1
      case 'Y' => 0
      case 'Z' => +1
    val m1 = (0 to 2).find(v => play(v, m2) == expCost).get
    (m1 + 1) + 3 * play(m1, m2) + 3

  override def part1(input: Seq[String]): String = input.map(cost1).sum.toString
  override def part2(input: Seq[String]): String = input.map(cost2).sum.toString
end D02
