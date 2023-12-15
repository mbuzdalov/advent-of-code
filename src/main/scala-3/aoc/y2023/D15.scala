package aoc.y2023

import scala.collection.mutable.{LinkedHashMap => MuHashMap}

import algo.SeqUtil
import aoc.Runner

object D15 extends Runner:
  private def hash(str: String): Int = str.foldLeft(0)((h, c) => ((h + c) * 17) & 255)
  private class Entry(val label: String, var focus: Int)

  override def part1(input: IndexedSeq[String]): String =
    SeqUtil.tokenMap(input.mkString(""), ",", hash).sum.toString

  override def part2(input: IndexedSeq[String]): String =
    val map = MuHashMap[String, Entry]()
    SeqUtil.tokens(input(0), ",").foreach:
      case s"$label-" => map.remove(label)
      case s"$label=$focus" => map.getOrElseUpdate(label, new Entry(label, -1)).focus = focus.toInt

    val count = Array.fill(256)(0)
    var result = 0L
    for e <- map.values do
      val h = hash(e.label)
      count(h) += 1
      result += (h + 1L) * count(h) * e.focus
    result.toString
end D15
