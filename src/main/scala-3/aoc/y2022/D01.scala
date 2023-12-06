package aoc.y2022

import algo.SeqUtil
import aoc.Runner

object D01 extends Runner:
  private def elves(input: Seq[String]): Seq[Int] = SeqUtil.splitBySeparator(input, _.isEmpty).map(_.map(_.toInt).sum)
  override def part1(input: IndexedSeq[String]): String = elves(input).max.toString
  override def part2(input: IndexedSeq[String]): String = elves(input).sorted.takeRight(3).sum.toString
end D01
