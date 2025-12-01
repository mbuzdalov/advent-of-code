package aoc.y2025

import algo.Loops
import aoc.Runner

object D01 extends Runner:
  override def part1(input: IndexedSeq[String]): String =
    var curr = 50
    var pw = 0
    for line <- input do
      val delta = (if line(0) == 'L' then -1 else +1) * line.drop(1).toInt
      curr = (curr + 100 - delta) % 100
      if curr == 0 then pw += 1
    pw.toString
  
  override def part2(input: IndexedSeq[String]): String =
    var curr = 50
    var pw = 0
    for line <- input do
      val delta = if line(0) == 'L' then -1 else +1
      Loops.repeat(line.drop(1).toInt):
        curr = (curr + 100 - delta) % 100
        if curr == 0 then pw += 1
    pw.toString
end D01
