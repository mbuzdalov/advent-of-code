package aoc.y2025

import algo.SeqUtil.*
import aoc.Runner

object D06 extends Runner:
  override def part1(input: IndexedSeq[String]): String =
    val last = tokens(input.last, " ")
    val parsed = input.init.map(line => tokenMap(line, " ", _.toLong)).transpose
    val partial = parsed.lazyZip(last).map:
      case (seq, "*") => seq.product
      case (seq, "+") => seq.sum
    partial.sum.toString
    
  override def part2(input: IndexedSeq[String]): String =
    val maxLen = input.map(_.length).max
    val inputOK = input.map(_.padTo(maxLen, ' ').toCharArray)
    val transform = splitBySeparator[String](inputOK.transpose.map(_.mkString), _.forall(_ == ' '))
    val partial = transform.map:
      case h +: t =>
        val numbers = (h.init +: t).map(_.trim.toLong)
        h.last match
          case '*' => numbers.product
          case '+' => numbers.sum
    partial.sum.toString
end D06
