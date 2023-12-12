package aoc.y2023

import algo.SeqUtil
import aoc.Runner

object D12 extends Runner:
  def nArrangements(map: String, desc: String): Long =
    val description = SeqUtil.tokenMap(desc, ",", _.toInt)
    val dp = Array.fill(map.length + 1, description.size + 1)(0L)
    val maxStart = map.indexOf('#')
    for i <- 0 to (if maxStart == -1 then map.length else maxStart) do dp(i)(0) = 1
    for i <- 1 to map.length do
      for j <- 1 to description.size do
        val notEndingHere = if map(i - 1) != '#' then dp(i - 1)(j) else 0
        val dj = description(j - 1)
        val canEndHere = i >= dj &&
          (i == dj || map(i - dj - 1) != '#') &&
          map.substring(i - dj, i).forall(_ != '.')
        val endingHere = if canEndHere then dp(math.max(0, i - dj - 1))(j - 1) else 0
        dp(i)(j) = notEndingHere + endingHere
    dp(map.length)(description.size)

  private def unwrap(nTimes: Int)(line: String): Long = line match
    case s"$a $b" => nArrangements(Seq.fill(nTimes)(a).mkString("?"), Seq.fill(nTimes)(b).mkString(","))

  override def part1(input: IndexedSeq[String]): String = input.map(unwrap(1)).sum.toString
  override def part2(input: IndexedSeq[String]): String = input.map(unwrap(5)).sum.toString
end D12
