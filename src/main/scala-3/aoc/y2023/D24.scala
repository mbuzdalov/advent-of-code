package aoc.y2023

import algo.Loops
import aoc.Runner

object D24 extends Runner:
  private case class Hail(x0: Long, y0: Long, z0: Long, dx: Long, dy: Long, dz: Long):
    def passesThrough(x: Long, y: Long, z: Long): Boolean =
      val tx = (x - x0).toDouble / dx
      val ty = (y - y0).toDouble / dy
      val tz = (z - z0).toDouble / dz
      (!tx.isFinite || !ty.isFinite || (tx >= 0 && ty >= 0 && math.abs(tx - ty) < math.max(tx, ty) * 1e-9)) &&
        (!tx.isFinite || !tz.isFinite || (tx >= 0 && tz >= 0 && math.abs(tx - tz) < math.max(tx, tz) * 1e-9)) &&
        (!tz.isFinite || !ty.isFinite || (tz >= 0 && ty >= 0 && math.abs(tz - ty) < math.max(tz, ty) * 1e-9))

  private def parseHail(line: String) = line match
    case s"$x0, $y0, $z0 @ $dx, $dy, $dz" => Hail(x0.toLong, y0.toLong, z0.toLong, dx.toLong, dy.toLong, dz.toLong)

  private def intersect1(h1: Hail, h2: Hail): Option[(Double, Double)] =
    val d = -1.0 * h1.dx * h2.dy + 1.0 * h1.dy * h2.dx
    if d == 0 then None else
      val d1 = -1.0 * (h2.x0 - h1.x0) * h2.dy + 1.0 * (h2.y0 - h1.y0) * h2.dx
      val d2 = +1.0 * h1.dx * (h2.y0 - h1.y0) - 1.0 * h1.dy * (h2.x0 - h1.x0)
      val t1 = d1 / d
      val t2 = d2 / d
      val x1 = h1.x0 + t1 * h1.dx
      val y1 = h1.y0 + t1 * h1.dy
      val x2 = h2.x0 + t2 * h2.dx
      val y2 = h2.y0 + t2 * h2.dy
      if t1 > 0 && t2 > 0 then Some((x1, y1)) else None

  private def nearlyRound(v: Double): Boolean =
    val r = math.round(v)
    math.abs(r - v) < 1e-9 * math.max(1, math.abs(v))

  private def intersect2(h1: Hail, h2: Hail): Option[(Long, Long, Long, Long)] =
    val d = -1.0 * h1.dx * h2.dy + 1.0 * h1.dy * h2.dx
    if d == 0 then None /* technically no, shall also check others, but still works */ else
      val d1 = -1.0 * (h2.x0 - h1.x0) * h2.dy + 1.0 * (h2.y0 - h1.y0) * h2.dx
      val d2 = +1.0 * h1.dx * (h2.y0 - h1.y0) - 1.0 * h1.dy * (h2.x0 - h1.x0)
      val t1 = d1 / d
      val t2 = d2 / d
      if t1 >= 0 && t2 >= 0 then
        val x1 = h1.x0 + t1 * h1.dx
        val y1 = h1.y0 + t1 * h1.dy
        val x2 = h2.x0 + t2 * h2.dx
        val y2 = h2.y0 + t2 * h2.dy
        if nearlyRound(x1) && nearlyRound(x2) && math.round(x1) == math.round(x2) &&
          nearlyRound(y1) && nearlyRound(y2) && math.round(y1) == math.round(y2) then
          val dz = (h2.z0 + t2 * h2.dz - h1.z0 - t1 * h1.dz) / (t2 - t1)
          if nearlyRound(dz) then
            val z1 = h1.z0 + t1 * (h1.dz - dz)
            val z2 = h2.z0 + t2 * (h2.dz - dz)
            if nearlyRound(z1) && nearlyRound(z2) && math.round(z1) == math.round(z2) then
              Some((math.round(x1), math.round(y1), math.round(z1), math.round(dz)))
            else None
          else None
        else None
      else None

  def part1(input: IndexedSeq[String], minXY: Long, maxXY: Long): Int =
    val hails = input.map(parseHail)
    Loops.mapFold[Int](0, hails.size)(_ + _): h1 =>
      Loops.count(h1 + 1, hails.size): h2 =>
        intersect1(hails(h1), hails(h2)) match
          case Some((x, y)) => minXY <= x && x <= maxXY && minXY <= y && y <= maxXY
          case None => false

  override def part1(input: IndexedSeq[String]): String = part1(input, 200000000000000L, 400000000000000L).toString

  override def part2(input: IndexedSeq[String]): String =
    val hails = input.map(parseHail)
    val firstTwo = hails.take(2)
    val fun = for
      dx <- -100 to 100
      dy <- -100 to 100
      h2i = firstTwo.map(h => h.copy(dx = h.dx - dx, dy = h.dy - dy))
      (x, y, z, dz) <- intersect2(h2i(0), h2i(1))
      h2 = hails.map(h => h.copy(dx = h.dx - dx, dy = h.dy - dy, dz = h.dz - dz))
      if h2.forall(_.passesThrough(x, y, z))
    yield (x, y, z)
    val (x, y, z) = fun.head
    (x + y + z).toString
end D24
