package aoc.y2023

import scala.collection.mutable

import algo.Loops
import aoc.Runner

object D22 extends Runner:
  private case class Point(x: Int, y: Int, z: Int)
  private case class Brick(min: Point, max: Point):
    def down: Brick = Brick(min.copy(z = min.z - 1), max.copy(z = max.z - 1))
    def intersects(set: mutable.HashSet[Point]): Boolean = min.z <= 0 ||
      Loops.any(min.x, max.x + 1): x =>
        Loops.any(min.y, max.y + 1): y =>
          set.contains(Point(x, y, min.z))

    def insert(set: mutable.HashSet[Point]): Unit =
      Loops.foreach(min.x, max.x + 1): x =>
        Loops.foreach(min.y, max.y + 1): y =>
          Loops.foreach(min.z, max.z + 1): z =>
            set.add(Point(x, y, z))

  private def parseBrick(line: String): Brick = line match
    case s"$x1,$y1,$z1~$x2,$y2,$z2" => Brick(Point(x1.toInt, y1.toInt, z1.toInt), Point(x2.toInt, y2.toInt, z2.toInt))

  private def settle(bricks: IndexedSeq[Brick]): (IndexedSeq[Brick], Int) =
    val sorted = bricks.sortBy(_.min.z)
    val result = IndexedSeq.newBuilder[Brick]
    val busy = mutable.HashSet[Point]()
    var moved = 0
    for p <- sorted do
      var pp = p
      var pd = pp.down
      var movedAdd = 0
      while !pd.intersects(busy) do
        movedAdd = 1
        pp = pd
        pd = pd.down
      moved += movedAdd
      pp.insert(busy)
      result += pp
    (result.result(), moved)

  override def part1(input: IndexedSeq[String]): String =
    val settled = settle(input.map(parseBrick))._1
    settled.count(e => settle(settled.filterNot(_ == e))._2 == 0).toString

  override def part2(input: IndexedSeq[String]): String =
    val settled = settle(input.map(parseBrick))._1
    settled.map(e => settle(settled.filterNot(_ == e))._2).sum.toString

end D22
