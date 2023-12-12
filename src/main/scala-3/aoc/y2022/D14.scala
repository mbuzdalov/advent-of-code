package aoc.y2022

import scala.annotation.tailrec

import algo.SeqUtil
import aoc.Runner

object D14 extends Runner:
  private case class Point(row: Int, col: Int)
  private def parsePoint(str: String): Point = str match
    case s"$c,$r" => Point(r.toInt, c.toInt)

  private def initField(input: IndexedSeq[String], bottom: Boolean): Array[Array[Boolean]] =
    val tokens = input.map(line => SeqUtil.tokenMap(line, " ->", parsePoint))
    val maxRow = tokens.map(_.map(_.row).max).max
    val result = Array.fill(maxRow + 3, 1001)(false)

    for row <- tokens do
      for Seq(Point(r1, c1), Point(r2, c2)) <- row.sliding(2) do
        for r <- math.min(r1, r2) to math.max(r1, r2); c <- math.min(c1, c2) to math.max(c1, c2) do
          result(r)(c) = true

    if bottom then java.util.Arrays.fill(result.last, true)
    result

  private def simulate(field: Array[Array[Boolean]]): Boolean =
    @tailrec
    def advance(r: Int, c: Int): Boolean =
      if r + 1 == field.length then false
      else if !field(r + 1)(c) then advance(r + 1, c)
      else if !field(r + 1)(c - 1) then advance(r + 1, c - 1)
      else if !field(r + 1)(c + 1) then advance(r + 1, c + 1)
      else
        field(r)(c) = true
        true

    if field(0)(500) then false else advance(0, 500)

  private def solve(input: IndexedSeq[String], bottom: Boolean): String =
    val field = initField(input, bottom)
    var count = 0
    while simulate(field) do
      count += 1
    count.toString

  override def part1(input: IndexedSeq[String]): String = solve(input, false)
  override def part2(input: IndexedSeq[String]): String = solve(input, true)
end D14
