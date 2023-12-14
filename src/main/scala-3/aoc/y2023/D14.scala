package aoc.y2023

import aoc.Runner

object D14 extends Runner:
  private def rollNorth(field: IndexedSeq[Array[Char]]): Unit =
    for c <- field(0).indices do
      var r, rMin = 0
      while r < field.size do
        if field(r)(c) == '#' then
          rMin = r + 1
        else if field(r)(c) == 'O' then
          field(r)(c) = '.'
          field(rMin)(c) = 'O'
          rMin += 1
        r += 1

  private def rollSouth(field: IndexedSeq[Array[Char]]): Unit =
    for c <- field(0).indices do
      var r, rMin = field.size - 1
      while r >= 0 do
        if field(r)(c) == '#' then
          rMin = r - 1
        else if field(r)(c) == 'O' then
          field(r)(c) = '.'
          field(rMin)(c) = 'O'
          rMin -= 1
        r -= 1

  private def rollEast(field: IndexedSeq[Array[Char]]): Unit =
    for r <- field.indices do
      var c, cMin = field(r).length - 1
      while c >= 0 do
        if field(r)(c) == '#' then
          cMin = c - 1
        else if field(r)(c) == 'O' then
          field(r)(c) = '.'
          field(r)(cMin) = 'O'
          cMin -= 1
        c -= 1

  private def rollWest(field: IndexedSeq[Array[Char]]): Unit =
    for r <- field.indices do
      var c, cMin = 0
      while c < field(r).length do
        if field(r)(c) == '#' then
          cMin = c + 1
        else if field(r)(c) == 'O' then
          field(r)(c) = '.'
          field(r)(cMin) = 'O'
          cMin += 1
        c += 1

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
