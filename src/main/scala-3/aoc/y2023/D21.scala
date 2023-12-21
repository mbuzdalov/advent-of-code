package aoc.y2023

import scala.collection.mutable

import algo.Loops
import aoc.Runner

object D21 extends Runner:
  private val dRow = Array(+1, 0, -1, 0)
  private val dCol = Array(0, +1, 0, -1)

  private def part1(input: IndexedSeq[String], startRow: Int, startCol: Int, nSteps: Int): Array[Int] =
    var curr, next = mutable.HashSet[(Int, Int)]()
    next.add((startRow, startCol))
    val sizes = Array.fill(nSteps + 1)(0)
    sizes(0) = 1
    Loops.foreach(0, nSteps): step =>
      val tmp = curr
      curr = next
      next = tmp
      next.clear()
      for
        (row, col) <- curr
        d <- 0 until 4
        nr = row + dRow(d) if nr >= 0 && nr < input.size
        nc = col + dCol(d) if nc >= 0 && nc < input(nr).length
        if input(nr)(nc) != '#'
      do
        next.add((nr, nc))
      sizes(step + 1) = next.size

    sizes

  def part1(input: IndexedSeq[String], nSteps: Int): Int =
    val startRow = input.indexWhere(_.contains('S'))
    val startCol = input(startRow).indexOf('S')
    part1(input, startRow, startCol, nSteps).last

  private def getPeriodic(array: Array[Int], period: Int, index: Int): Int =
    if index < array.length then array(index) else
      var idx = array.length - 1
      while idx % period != index % period do idx -= 1
      array(idx)

  def part2naive(input: IndexedSeq[String], nSteps: Int): Int =
    val startRow = input.indexWhere(_.contains('S'))
    val startCol = input(startRow).indexOf('S')
    var curr, next = mutable.HashSet[(Int, Int)]()
    val nRows = input.size
    val nCols = input(0).length
    next.add((startRow, startCol))
    Loops.foreach(0, nSteps): step =>
      val tmp = curr
      curr = next
      next = tmp
      next.clear()
      for
        (row, col) <- curr
        d <- 0 until 4
        nr = row + dRow(d)
        nc = col + dCol(d)
        if input((nr % nRows + nRows) % nRows)((nc % nCols + nCols) % nCols) != '#'
      do
        next.add((nr, nc))
    next.size

  def part2(input: IndexedSeq[String], nSteps: Int): Long =
    val startRow = input.indexWhere(_.contains('S'))
    val startCol = input(startRow).indexOf('S')
    val nRows = input.size
    val nCols = input(0).length
    assert(nRows == nCols)
    assert(nRows / 2 == startRow)
    assert(nCols / 2 == startCol)

    val maxSubSteps = 2 * nRows + 10
    val central = part1(input, startRow, startCol, maxSubSteps)
    val above = part1(input, nRows - 1, startCol, maxSubSteps)
    val below = part1(input, 0, startCol, maxSubSteps)
    val onLeft = part1(input, startRow, nCols - 1, maxSubSteps)
    val onRight = part1(input, startRow, 0, maxSubSteps)
    val aboveLeft = part1(input, nRows - 1, nCols - 1, maxSubSteps)
    val aboveRight = part1(input, nRows - 1, 0, maxSubSteps)
    val belowLeft = part1(input, 0, nCols - 1, maxSubSteps)
    val belowRight = part1(input, 0, 0, maxSubSteps)
    // periods everywhere are 2

    var nCentral = 0
    var sum = 0L
    sum += getPeriodic(central, 2, nSteps)
    var t = nSteps - startRow - 1
    while t >= 0 do
      sum += getPeriodic(above, 2, t) + getPeriodic(below, 2, t) + getPeriodic(onLeft, 2, t) + getPeriodic(onRight, 2, t)
      t -= nRows
    t = nSteps - nRows - 1
    var prod = 1L
    while t >= 0 do
      sum += prod * (getPeriodic(aboveLeft, 2, t) + getPeriodic(aboveRight, 2, t) + getPeriodic(belowLeft, 2, t) + getPeriodic(belowRight, 2, t))
      prod += 1
      t -= nRows

    sum

  override def part1(input: IndexedSeq[String]): String = part1(input, 64).toString
  override def part2(input: IndexedSeq[String]): String = part2(input, 26501365).toString
end D21
