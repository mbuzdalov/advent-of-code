package aoc.y2023

import aoc.Runner

object D11 extends Runner:
  def solve(input: IndexedSeq[String], expansionRate: Int): String =
    val galaxies = for
      r <- input.indices
      c <- input(r).indices
      if input(r)(c) == '#'
    yield (r, c)
    val expRows = input.indices.toSet.removedAll(galaxies.map(_._1))
    val expCols = input(0).indices.toSet.removedAll(galaxies.map(_._2))
    val distances = for
      i1 <- galaxies.indices
      i2 <- 0 until i1
    yield
      val g1 = galaxies(i1)
      val g2 = galaxies(i2)
      val minR = math.min(g1._1, g2._1)
      val maxR = g1._1 + g2._1 - minR
      val minC = math.min(g1._2, g2._2)
      val maxC = g1._2 + g2._2 - minC
      val extraV = expRows.count(i => minR < i && i < maxR)
      val extraH = expCols.count(i => minC < i && i < maxC)
      (maxR - minR + maxC - minC) + (extraH + extraV) * (expansionRate - 1L)
    distances.sum.toString

  override def part1(input: IndexedSeq[String]): String = solve(input, 2)
  override def part2(input: IndexedSeq[String]): String = solve(input, 1000000)
end D11
