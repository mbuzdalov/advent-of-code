package aoc.y2023

import aoc.Runner

object D03 extends Runner:
  private class GearStat:
    private var nNumbers = 0
    private var product = 1L
    def add(number: Int): Unit =
      nNumbers += 1
      product *= number
    def collect: Long =
      if nNumbers == 2 then product else 0

  private class GearAcceptor:
    private val map = scala.collection.mutable.HashMap[(Int, Int), GearStat]()
    def accept(row: Int, col: Int, number: Int): Unit = map.getOrElseUpdate(row -> col, new GearStat).add(number)
    def result: Long = map.values.map(_.collect).sum

  private def checkSurroundings(input: Seq[String], row: Int, minCol: Int, maxCol: Int, value: Int, acc: GearAcceptor): Boolean =
    def process(row: Int, col: Int): Boolean =
      if row >= 0 && row < input.size && col >= 0 && col < input(row).length then
        val ch = input(row)(col)
        if ch == '*' then acc.accept(row, col, value)
        ch != '.'
      else false

    // single pipes are used because process has size effects, and we want all cells to be tested
    var result = process(row, minCol - 1) | process(row, maxCol)
    for col <- minCol - 1 to maxCol do
      result |= process(row - 1, col) | process(row + 1, col)

    result

  private def solve(input: Seq[String]): (Int, Long) =
    var result = 0
    val nr = input.size
    val nc = input.head.length
    val acceptor = new GearAcceptor
    for row <- 0 until nr do
      var lastCol = -1
      var number = 0
      for col <- 0 to nc do
        val ch = if col == nc then '.' else input(row)(col)
        if ch >= '0' && ch <= '9' then
          number = 10 * number + ch - '0'
        else
          if lastCol + 1 < col && checkSurroundings(input, row, lastCol + 1, col, number, acceptor) then
            result += number
          lastCol = col
          number = 0
    (result, acceptor.result)

  override def part1(input: Seq[String]): String = solve(input)._1.toString
  override def part2(input: Seq[String]): String = solve(input)._2.toString
end D03
