package aoc.y2023

import algo.SeqUtil
import aoc.Runner

object D09 extends Runner:
  private def predict(seq: IndexedSeq[Int]): Int =
    if seq.count(_ == 0) == seq.size then 0 else
      val derivative = seq.sliding(2).map(p => p(1) - p(0)).toIndexedSeq
      seq.last + predict(derivative)

  private def solve(input: IndexedSeq[String], conv: IndexedSeq[Int] => IndexedSeq[Int]): String =
    input.map(line => predict(conv(SeqUtil.tokenMap(line, " ", _.toInt)))).sum.toString

  override def part1(input: IndexedSeq[String]): String = solve(input, identity)
  override def part2(input: IndexedSeq[String]): String = solve(input, _.reverse)
end D09
