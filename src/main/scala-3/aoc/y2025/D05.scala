package aoc.y2025

import algo.SeqUtil
import aoc.Runner

object D05 extends Runner:
  private def parseRange(s: String) = s match
    case s"$l-$r" => (l.toLong, r.toLong)
  
  override def part1(input: IndexedSeq[String]): String =
    val IndexedSeq(rangesRaw, instancesRaw) = SeqUtil.splitBySeparator[String](input, _.isEmpty)
    val ranges = rangesRaw.map(parseRange)
    val queries = instancesRaw.map(_.toLong)
    queries.count(q => ranges.exists(r => r._1 <= q && q <= r._2)).toString
    
  override def part2(input: IndexedSeq[String]): String =
    val IndexedSeq(rangesRaw, instancesRaw) = SeqUtil.splitBySeparator[String](input, _.isEmpty)
    val ranges = rangesRaw.map(parseRange).sorted
    var answer = 0L
    var (lastL, lastR) = ranges(0)
    for (l, r) <- ranges do
      if l <= r then
        if l > lastR + 1 then
          answer += lastR - lastL + 1
          lastL = l
        lastR = Math.max(lastR, r)
    answer += lastR - lastL + 1
    answer.toString
end D05
