package aoc.y2023

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
        input(row)(col) match
          case '.' =>
            go(row + dRow(dir), col + dCol(dir), dir)
          case '|' =>
            go(row + dRow(0), col + dCol(0), 0)
            go(row + dRow(2), col + dCol(2), 2)
          case '-' =>
            go(row + dRow(1), col + dCol(1), 1)
            go(row + dRow(3), col + dCol(3), 3)
          case '/' =>
            go(row + dRow(3 - dir), col + dCol(3 - dir), 3 - dir)
          case '\\' =>
            go(row + dRow(dir ^ 1), col + dCol(dir ^ 1), dir ^ 1)

  override def part1(input: IndexedSeq[String]): String = Filler(input).evaluate(0, 0, 1).toString
  override def part2(input: IndexedSeq[String]): String =
    val filler = Filler(input)
    val td = input(0).indices.map(c => filler.evaluate(0, c, 0)).max
    val dt = input(0).indices.map(c => filler.evaluate(input.size - 1, c, 2)).max
    val lr = input.indices.map(r => filler.evaluate(r, 0, 1)).max
    val rl = input.indices.map(r => filler.evaluate(r, input(0).length - 1, 3)).max
    Seq(td, dt, lr, rl).max.toString
end D16
