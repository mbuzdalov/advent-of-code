package aoc.y2022

import aoc.Runner

object D04 extends Runner:
  private def decide(line: String, predicate: (Range, Range) => Boolean): Boolean = line match
    case s"$r1s-$r1e,$r2s-$r2e" => predicate(r1s.toInt to r1e.toInt, r2s.toInt to r2e.toInt)

  private def overlap1(r1: Range, r2: Range): Boolean = r1.intersect(r2).size == math.min(r1.size, r2.size)
  private def overlap2(r1: Range, r2: Range): Boolean = r1.intersect(r2).nonEmpty

  override def part1(input: Seq[String]): String = input.count(line => decide(line, overlap1)).toString
  override def part2(input: Seq[String]): String = input.count(line => decide(line, overlap2)).toString
end D04
