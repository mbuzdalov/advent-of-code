package aoc.y2025

import algo.Loops
import algo.SeqUtil.*
import aoc.Runner

object D09 extends Runner:
  private class Point(val x: Long, val y: Long)
  private def parse(s: String): Point =
    val t = tokens(s, ",")
    Point(t(0).toLong, t(1).toLong)

  override def part1(input: IndexedSeq[String]): String =
    val parsed = input.map(parse)
    var answer = 0L
    for a <- parsed; b <- parsed do
      answer = math.max(answer, (math.abs(a.x - b.x) + 1) * (math.abs(a.y - b.y) + 1))
    answer.toString

  private def isOnSide(x: Long, y: Long, pts: IndexedSeq[Point]): Boolean =
    Loops.any(0, pts.length): i =>
      val p1 = pts(i)
      val p2 = pts((i + 1) % pts.length)
      p1.x == p2.x && x == p1.x && (p1.y <= y && y <= p2.y || p2.y <= y && y <= p1.y) ||
        p1.y == p2.y && y == p1.y && (p1.x <= x && x <= p2.x || p2.x <= x && x <= p1.x)

  private def isInside(x: Long, y: Long, pts: IndexedSeq[Point]): Boolean =
    var nIntersections = 0
    Loops.foreach(0, pts.length): i =>
      val p1 = pts(i)
      val p2 = pts((i + 1) % pts.length)
      if p1.x < x && p2.x < x then
        val cmp1 = java.lang.Long.compare(p1.y, y)
        val cmp2 = java.lang.Long.compare(p2.y, y)
        nIntersections += cmp2 - cmp1
    nIntersections != 0

  override def part2(input: IndexedSeq[String]): String =
    val parsed = input.map(parse)
    Loops.foreach(0, parsed.size): i =>
      val p = parsed(i)
      val q = parsed((i + 1) % parsed.size)
      assert(p.x == q.x || p.y == q.y)

    val allX = parsed.flatMap(p => IndexedSeq(p.x - 1, p.x, p.x + 1)).distinct.sorted
    val allY = parsed.flatMap(p => IndexedSeq(p.y - 1, p.y, p.y + 1)).distinct.sorted
    val canUse = IArray.tabulate(allX.size, allY.size): (xi, yi) =>
      val x = allX(xi)
      val y = allY(yi)
      isOnSide(x, y, parsed) || isInside(x, y, parsed)

    var answer = 0L
    for a <- parsed; b <- parsed do
      val ax = allX.indexOf(a.x)
      val ay = allY.indexOf(a.y)
      val bx = allX.indexOf(b.x)
      val by = allY.indexOf(b.y)
      val area = (math.abs(a.x - b.x) + 1) * (math.abs(a.y - b.y) + 1)
      if area > answer && Loops.all(math.min(ax, bx), math.max(ax, bx) + 1): x =>
         Loops.all(math.min(ay, by), math.max(ay, by) + 1): y =>
           canUse(x)(y)
      then
        answer = area

    answer.toString
end D09
