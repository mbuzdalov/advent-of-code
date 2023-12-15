package aoc.y2022

import aoc.Runner

object D15 extends Runner:
  private case class Def(sx: Int, sy: Int, bx: Int, by: Int):
    private val dist = math.abs(sx - bx) + math.abs(sy - by)
    def coveredRange(y: Int, minX: Int, maxX: Int): Range =
      val remDist = dist - math.abs(y - sy)
      math.max(minX, sx - remDist) to math.min(maxX, sx + remDist)

  private def joinRange(ranges: IndexedSeq[Range], range: Range): IndexedSeq[Range] =
    if range.isEmpty then ranges else
    if ranges.isEmpty then IndexedSeq(range) else
    if ranges.last.last >= range.last then ranges else
    if ranges.last.last + 1 < range.head then ranges :+ range else
    ranges.updated(ranges.size - 1, ranges.last.head to range.last)

  private def getRanges(records: IndexedSeq[Def], y: Int, minX: Int = Int.MinValue, maxX: Int = Int.MaxValue): IndexedSeq[Range] =
    records.map(_.coveredRange(y, minX, maxX)).filter(_.nonEmpty).sortBy(_.head).foldLeft(IndexedSeq[Range]())(joinRange)
  
  private def parseRecord(line: String): Def = line match
    case s"Sensor at x=$sx, y=$sy: closest beacon is at x=$bx, y=$by" => Def(sx.toInt, sy.toInt, bx.toInt, by.toInt)
  
  def part1(input: IndexedSeq[String], y: Int): Int =
    val records = input.map(parseRecord)
    val covered = getRanges(records, y)
    val beaconsHere = records.filter(_.by == y).map(_.bx)
    covered.map(_.toSet).reduce(_ ++ _).removedAll(beaconsHere).size

  def part2(input: IndexedSeq[String], maxXY: Int): Long =
    val records = input.map(parseRecord)
    val locations = for
      y <- 0 to maxXY
      ranges = getRanges(records, y, 0, maxXY)
      if ranges.size > 1
    yield (ranges(0).last + 1, y)
    assert(locations.size == 1, locations.toString())
    val (bx, by) = locations(0)
    bx * 4000000L + by

  override def part1(input: IndexedSeq[String]): String = part1(input, 2000000).toString
  override def part2(input: IndexedSeq[String]): String = part2(input, 4000000).toString
end D15
