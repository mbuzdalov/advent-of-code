package aoc.y2025

import algo.{Loops, SeqUtil}
import aoc.Runner

import java.io.ByteArrayInputStream

object D10 extends Runner:
  private def parseMask(s: String): Int =
    var result = 0
    for c <- s.drop(1).dropRight(1).reverse do
      result *= 2
      if c == '#' then result += 1
    result
  
  private def parseSet(s: String): Int =
    var result = 0
    for v <- SeqUtil.tokens(s, ",()") do
      result ^= 1 << v.toInt
    result
    
  private def parseSeq(s: String): IArray[Int] = IArray(SeqUtil.tokens(s, ",(){}").map(_.toInt) *)
    
  private def solve1(line: String): Int =
    val ts = SeqUtil.tokens(line, " ")
    val mask = parseMask(ts.head)
    val sets = ts.drop(1).dropRight(1).map(parseSet)
    var best = Int.MaxValue
    Loops.foreach(0, 1 << sets.size): choice =>
      var result = 0
      Loops.forBits(choice)(b => result ^= sets(b))
      if result == mask && Integer.bitCount(choice) < Integer.bitCount(best) then
        best = choice
    assert(best != Int.MaxValue)
    Integer.bitCount(best)

  private def solve2(line: String): Int =
    val ts = SeqUtil.tokens(line, " ")
    val mask = parseSeq(ts.last)
    val sets = ts.drop(1).dropRight(1).map(parseSeq)
    
    val inputBuilder = new StringBuilder
    inputBuilder.append(s"${mask.length} ${sets.size}")
    inputBuilder.append(mask.mkString(" ", " ", " "))
    for set <- sets do inputBuilder.append(s"${set.length} ${set.mkString(" ")} ")
    val str = inputBuilder.result().getBytes

    import scala.sys.process.*
    ("./aoc.exe" #< new ByteArrayInputStream(str)).!!.trim.toInt
  
  override def part1(input: IndexedSeq[String]): String = input.map(solve1).sum.toString
  override def part2(input: IndexedSeq[String]): String =
    import scala.sys.process.*
    "gcc -O2 -o aoc.exe inputs/2025/10/aoc.c -lglpk".!
    val result = input.map(solve2).sum.toString
    "rm aoc.exe".!
    result
end D10
