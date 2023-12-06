package aoc.y2022

import scala.annotation.tailrec

import aoc.Runner

object D08 extends Runner:
  private val dirs = Seq((+1, 0), (-1, 0), (0, +1), (0, -1))
  private case class ScanResult(out: Int, len: Int):
    def aggregate(that: ScanResult): ScanResult = ScanResult(out max that.out, len * that.len)

  private def scan(input: Seq[String], r0: Int, c0: Int, d: Int): ScanResult =
    val ref = input(r0)(c0)
    @tailrec
    def go(r: Int, c: Int, i: Int): ScanResult =
      val rn = r + dirs(d)._1
      val cn = c + dirs(d)._2
      if rn >= 0 && cn >= 0 && rn < input.size && cn < input(rn).length then
        if input(rn)(cn) < ref then go(rn, cn, i + 1) else ScanResult(0, i + 1)
      else ScanResult(1, i)
    go(r0, c0, 0)

  private def solve(input: Seq[String], extract: ScanResult => Int, compose: (Int, Int) => Int): Int =
    val cells = for
      r <- input.indices
      c <- input(r).indices
    yield extract(dirs.indices.map(d => scan(input, r, c, d)).reduce(_ aggregate _))
    cells.reduce(compose)

  override def part1(input: IndexedSeq[String]): String = solve(input, _.out, _ + _).toString
  override def part2(input: IndexedSeq[String]): String = solve(input, _.len, _ max _).toString
end D08
