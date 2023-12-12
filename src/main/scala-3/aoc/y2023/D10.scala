package aoc.y2023

import aoc.Runner

object D10 extends Runner:
  private final val Inf = 1000000000
  private val dR = IndexedSeq(+1, 0, -1, 0)
  private val dC = IndexedSeq(0, +1, 0, -1)
  private val perm = IndexedSeq("|JL", "-J7", "|7F", "-FL")

  private def shortestPaths(input: IndexedSeq[String]): IndexedSeq[Array[Array[Int]]] =
    val nRows = input.size
    val nCols = input(0).length
    val result = IndexedSeq.fill(4)(Array.fill(nRows, nCols)(Inf))
    val startRow = input.indexWhere(_.contains('S'))
    val startCol = input(startRow).indexOf('S')
    result.foreach(_(startRow)(startCol) = 0)
    for dir <- 0 until 4 do
      var pr = startRow
      var pc = startCol
      var r = pr + dR(dir)
      var c = pc + dC(dir)
      if r >= 0 && r < nRows && c >= 0 && c < nCols && perm(dir).contains(input(r)(c)) then
        while r >= 0 && r < nRows && c >= 0 && c < nCols && input(r)(c) != '.' && input(r)(c) != 'S' do
          result(dir)(r)(c) = result(dir)(pr)(pc) + 1
          val rr = r
          val cc = c
          input(r)(c) match
            case '|' =>
              r = 2 * r - pr
              assert(c == pc)
            case '-' =>
              c = 2 * c - pc
              assert(r == pr)
            case 'L' =>
              r = 2 * r - pr - 1
              c = 2 * c - pc + 1
            case 'J' =>
              r = 2 * r - pr - 1
              c = 2 * c - pc - 1
            case '7' =>
              r = 2 * r - pr + 1
              c = 2 * c - pc - 1
            case 'F' =>
              r = 2 * r - pr + 1
              c = 2 * c - pc + 1
          pr = rr
          pc = cc
    result

  override def part1(input: IndexedSeq[String]): String =
    val paths = shortestPaths(input)
    val nRows = input.size
    val nCols = input(0).length
    val results = for
      r <- 0 until nRows
      c <- 0 until nCols
    yield
      val distances = paths.map(p => p(r)(c)).filter(_ < Inf)
      if input(r)(c) != 'S' && distances.size >= 2 then
        assert(distances.size == 2)
        distances.sum
      else -1
    (results.max / 2).toString

  override def part2(input: IndexedSeq[String]): String =
    val paths = shortestPaths(input)
    val nRows = input.size
    val nCols = input(0).length
    val results: Seq[Option[Seq[Int]]] = for
      r <- 0 until nRows
      c <- 0 until nCols
    yield
      val distances = paths.map(p => p(r)(c))
      if input(r)(c) != 'S' && distances.count(_ < Inf) >= 2 then
        Some(distances.zipWithIndex.filter(_._1 < Inf).map(_._2))
      else None
    val directions = results.flatten.head.sorted
    val sChar = directions match
      case Seq(0, 1) => 'F'
      case Seq(0, 2) => '|'
      case Seq(0, 3) => '7'
      case Seq(1, 2) => 'L'
      case Seq(1, 3) => '-'
      case Seq(2, 3) => 'J'

    val busy = Array.fill(nRows, nCols)(0)
    for
      d <- directions
      r <- 0 until nRows
      c <- 0 until nCols
    do
      if paths(d)(r)(c) < Inf then busy(r)(c) = 1

    var result = 0
    for row <- 0 until nRows do
      var cnt1 = 0
      var prev = ' '
      for col <- 0 until nCols do
        if busy(row)(col) == 1 then
          val ch = if input(row)(col) == 'S' then sChar else input(row)(col)
          ch match
            case '|' => cnt1 += 1
            case '-' =>
            case 'F' => prev = 'F'
            case 'J' => if prev == 'F' then cnt1 += 1
            case 'L' => prev = 'L'
            case '7' => if prev == 'L' then cnt1 += 1
        if busy(row)(col) == 0 && cnt1 % 2 == 1 then
          result += 1

    result.toString
end D10
