package aoc.y2023

import algo.Loops
import aoc.Runner

object D16 extends Runner:
  private val dRow = IndexedSeq(+1, 0, -1, 0)
  private val dCol = IndexedSeq(0, +1, 0, -1)

  private class Filler(input: IndexedSeq[String]):
    private val nRows = input.size
    private val nCols = input(0).length
    private val visited = Array.fill(nRows, nCols)(0)

    def evaluate(row: Int, col: Int, dir: Int): Int =
      visited.foreach(r => java.util.Arrays.fill(r, 0))
      go(row, col, dir)
      visited.map(_.count(_ != 0)).sum

    private def go(row: Int, col: Int, dir: Int): Unit =
      if 0 <= row && row < nRows && 0 <= col && col < nCols && (visited(row)(col) & (1 << dir)) == 0 then
        visited(row)(col) |= 1 << dir
        val nextDirs = input(row)(col) match
          case '.' => 1 << dir
          case '|' => 5
          case '-' => 10
          case '/' => 1 << (3 - dir)
          case '\\' => 1 << (dir ^ 1)
        Loops.forBits(nextDirs)(d => go(row + dRow(d), col + dCol(d), d))

  override def part1(input: IndexedSeq[String]): String = Filler(input).evaluate(0, 0, 1).toString
  override def part2(input: IndexedSeq[String]): String =
    val filler = Filler(input)
    val nRows = input.size
    val nCols = input(0).length
    val td = Loops.mapFold[Int](0, nCols)(math.max)(c => filler.evaluate(0, c, 0))
    val dt = Loops.mapFold[Int](0, nCols)(math.max)(c => filler.evaluate(nRows - 1, c, 2))
    val lr = Loops.mapFold[Int](0, nRows)(math.max)(r => filler.evaluate(r, 0, 1))
    val rl = Loops.mapFold[Int](0, nRows)(math.max)(r => filler.evaluate(r, nCols - 1, 3))
    Seq(td, dt, lr, rl).max.toString
end D16
