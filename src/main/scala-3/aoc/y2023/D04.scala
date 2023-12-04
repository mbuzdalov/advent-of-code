package aoc.y2023

import java.util.StringTokenizer

import algo.SeqUtil
import aoc.Runner

object D04 extends Runner:
  private def evaluateCard1(line: String): Int = line match
    case s"Card $n: $need | $have" =>
      val needSet = SeqUtil.tokenMap(need, " ", _.toInt).toSet
      SeqUtil.tokenMap(have, " ", _.toInt).count(needSet.contains)

  override def part1(input: Seq[String]): String =
    input.map(l => (1L << evaluateCard1(l)) / 2).sum.toString

  override def part2(input: Seq[String]): String =
    val copies = Array.fill(input.size)(1L)
    var result = 0L
    for i <- input.indices do
      result += copies(i)
      val nFollowing = evaluateCard1(input(i))
      for t <- 0 until nFollowing do
        copies(i + t + 1) += copies(i)
    result.toString
end D04
