package aoc.y2022

import scala.collection.mutable

import aoc.Runner

object D12 extends Runner:
  private val directions = Seq((+1, 0), (0, +1), (-1, 0), (0, -1))

  private def find(input: IndexedSeq[String], char: Char): (Int, Int) =
    val row = input.indexWhere(_.contains(char))
    (row, input(row).indexOf(char))

  private def shortestPaths(field: IndexedSeq[String], fromR: Int, fromC: Int): Array[Array[Int]] =
    val nRows = field.size
    val nCols = field(0).length
    val dist = Array.fill(nRows, nCols)(nRows * nCols + 1)
    val qr, qc = mutable.ArrayDeque[Int]()
    qr.addOne(fromR)
    qc.addOne(fromC)
    dist(fromR)(fromC) = 0
    while qr.nonEmpty do
      val r = qr.removeHead()
      val c = qc.removeHead()
      for (dr, dc) <- directions do
        val nr = r + dr
        val nc = c + dc
        if nr >= 0 && nr < nRows && nc >= 0 && nc < nCols && field(nr)(nc) >= field(r)(c) - 1 && dist(nr)(nc) > dist(r)(c) + 1 then
          dist(nr)(nc) = dist(r)(c) + 1
          qr.addOne(nr)
          qc.addOne(nc)
    dist

  override def part1(input: IndexedSeq[String]): String =
    val (endR, endC) = find(input, 'E')
    val (startR, startC) = find(input, 'S')
    val map = input.map(_.replace('E', 'z').replace('S', 'a'))
    val dist = shortestPaths(map, endR, endC)
    dist(startR)(startC).toString

  override def part2(input: IndexedSeq[String]): String =
    val (endR, endC) = find(input, 'E')
    val map = input.map(_.replace('E', 'z').replace('S', 'a'))
    val dist = shortestPaths(map, endR, endC)
    val distA = for
      row <- input.indices
      col <- input(row).indices
      if input(row)(col) == 'a'
    yield dist(row)(col)
    distA.min.toString
end D12
