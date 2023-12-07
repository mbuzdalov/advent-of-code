package aoc.y2022

import aoc.Runner

object D09 extends Runner:
  private val dirR = Seq(1, 0, -1, 0)
  private val dirC = Seq(0, 1, 0, -1)
  private def getDir(dir: Char): Int = dir match
    case 'R' => 1
    case 'L' => 3
    case 'U' => 0
    case 'D' => 2

  private class Position(length: Int):
    private val rows, cols = Array.fill(length)(0)
    def move(dir: Char, times: Int, acceptor: ((Int, Int)) => Unit): Unit =
      val di = getDir(dir)
      for t <- 0 until times do
        rows(0) += dirR(di)
        cols(0) += dirC(di)
        for i <- 1 until length do
          val diffR = rows(i - 1) - rows(i)
          val diffC = cols(i - 1) - cols(i)
          if math.abs(diffR) > 1 || math.abs(diffC) > 1 then
            rows(i) += math.signum(diffR)
            cols(i) += math.signum(diffC)
        acceptor(rows.last -> cols.last)

  private def solve(input: IndexedSeq[String], length: Int): Int =
    val pos = Position(length)
    val set = scala.collection.mutable.HashSet[(Int, Int)]()
    for line <- input do pos.move(line(0), line.substring(2).toInt, set += _)
    set.size

  override def part1(input: IndexedSeq[String]): String = solve(input, 2).toString
  override def part2(input: IndexedSeq[String]): String = solve(input, 10).toString
end D09
