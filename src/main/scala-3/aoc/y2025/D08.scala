package aoc.y2025

import algo.DisjointSet
import algo.Loops.*
import algo.SeqUtil.*
import aoc.Runner

object D08 extends Runner:
  private class Point(val x: Long, val y: Long, val z: Long):
    def dist2(that: Point): Long =
      (x - that.x) * (x - that.x) + (y - that.y) * (y - that.y) + (z - that.z) * (z - that.z)

  private def parsePoint(s: String): Point =
    val t = tokens(s, ",").map(_.toLong)
    Point(t(0), t(1), t(2))
  
  private def solve(input: IndexedSeq[String]): (Long, Long) =
    val n = input.length
    val m = n match
      case 20 => 10
      case 1000 => 1000

    val points = input.map(parsePoint)
    val edges = for
      i <- points.indices
      j <- 0 until i
    yield (i, j)

    val (firstSet, lastSet) = edges.sortBy:
      case (a, b) => points(a).dist2(points(b))
    .splitAt(m)

    val ds = DisjointSet(n)
    for (a, b) <- firstSet do ds.unite(a, b)

    val cnt = Array.fill(n)(0L)
    foreach(0, n)(i => cnt(ds.get(i)) += 1)
    val a1 = cnt.sorted.takeRight(3).product

    var a2 = -1L
    for (a, b) <- lastSet do
      if ds.get(a) != ds.get(b) then
        ds.unite(a, b)
        if ds.nComponents == 1 then
          a2 = points(a).x * points(b).x

    (a1, a2)
  
  override def part1(input: IndexedSeq[String]): String = solve(input)._1.toString
  override def part2(input: IndexedSeq[String]): String = solve(input)._2.toString
end D08
