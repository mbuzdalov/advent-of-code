package aoc.y2023

import aoc.Runner

object D14 extends Runner:
  private def rollNS(field: IndexedSeq[Array[Char]], init: Int, delta: Int): Unit =
    for c <- field(0).indices do
      var r, rMin = init
      while r >= 0 && r < field.size do
        if field(r)(c) == '#' then
          rMin = r + delta
        else if field(r)(c) == 'O' then
          field(r)(c) = '.'
          field(rMin)(c) = 'O'
          rMin += delta
        r += delta

  private def rollNorth(field: IndexedSeq[Array[Char]]): Unit = rollNS(field, 0, +1)
  private def rollSouth(field: IndexedSeq[Array[Char]]): Unit = rollNS(field, field.size - 1, -1)

  private def rollEW(field: IndexedSeq[Array[Char]], init: Int, delta: Int): Unit =
    for r <- field.indices do
      var c, cMin = init
      while c >= 0 && c < field(r).length do
        if field(r)(c) == '#' then
          cMin = c + delta
        else if field(r)(c) == 'O' then
          field(r)(c) = '.'
          field(r)(cMin) = 'O'
          cMin += delta
        c += delta

  private def rollEast(field: IndexedSeq[Array[Char]]): Unit = rollEW(field, field(0).length - 1, -1)
  private def rollWest(field: IndexedSeq[Array[Char]]): Unit = rollEW(field, 0, +1)

  private def rollTurn(field: IndexedSeq[Array[Char]]): Unit =
    rollNorth(field)
    rollWest(field)
    rollSouth(field)
    rollEast(field)

  private def computeLoad(field: IndexedSeq[Array[Char]]): String =
    field.map(_.count(_ == 'O')).zipWithIndex.map(p => p._1 * (field.size - p._2).toLong).sum.toString

  override def part1(input: IndexedSeq[String]): String =
    val field = input.map(_.toCharArray)
    rollNorth(field)
    computeLoad(field)

  override def part2(input: IndexedSeq[String]): String =
    val field = input.map(_.toCharArray)
    val hash = scala.collection.mutable.HashMap[IndexedSeq[String], Int]()
    hash += input -> 0
    var iteration = 0
    var answer = -1
    var modulo = -1
    while modulo == -1 || iteration % modulo != 1000000000 % modulo do
      rollTurn(field)
      iteration += 1
      if modulo == -1 then
        val fixed = field.map(_.mkString(""))
        if hash.contains(fixed) then
          modulo = iteration - hash(fixed)
        else
          hash += fixed -> iteration
    computeLoad(field)
end D14
