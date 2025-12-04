package aoc.y2025

import algo.Loops
import aoc.Runner

object D04 extends Runner:
  private def mark(input: IndexedSeq[Array[Char]]): Unit =
    Loops.foreach(0, input.length): r =>
      Loops.foreach(0, input(r).length): c =>
        if input(r)(c) == '@' then
          val count = Loops.count(0, 9): i =>
            val nr = r + i / 3 - 1
            val nc = c + i % 3 - 1
            nr >= 0 && nr < input.length && nc >= 0 && nc < input(r).length && input(nr)(nc) != '.'
          if count < 5 then input(r)(c) = 'X'
  
  private def count(input: IndexedSeq[Array[Char]]): Int =
    input.map(_.count(_ == 'X')).sum

  private def remove(input: IndexedSeq[Array[Char]]): Unit =
    Loops.foreach(0, input.length): r =>
      Loops.foreach(0, input(r).length): c =>
        if input(r)(c) == 'X' then input(r)(c) = '.'
  
  override def part1(input: IndexedSeq[String]): String =
    val i2 = input.map(_.toCharArray)
    mark(i2)
    count(i2).toString
    
  override def part2(input: IndexedSeq[String]): String =
    val i2 = input.map(_.toCharArray)
    var answer = 0
    var currCount = 0
    while
      mark(i2)
      currCount = count(i2)
      currCount > 0
    do
      answer += currCount
      remove(i2)
    answer.toString
end D04
