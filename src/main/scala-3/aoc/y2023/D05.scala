package aoc.y2023

import scala.collection.immutable.NumericRange

import algo.SeqUtil
import aoc.Runner

object D05 extends Runner:
  private type LongRange = NumericRange[Long]
  private case class FunRange(src: Long, dst: Long, len: Long) extends Ordered[FunRange]:
    def srcEnd: Long = src + len
    def map(key: Long): Long = dst - src + key
    override def compare(that: FunRange): Int = src compare that.src

  private class FunMap(triples0: Seq[FunRange]):
    private val triples = locally:
      val sorted = triples0.sorted
      val builder = IndexedSeq.newBuilder[FunRange]
      for i <- sorted.indices do
        if i > 0 then
          val prevEnd = sorted(i - 1).srcEnd
          if prevEnd < sorted(i).src then
            builder += FunRange(prevEnd, prevEnd, sorted(i).src - prevEnd)
        builder += sorted(i)
      builder.result()

    def get(key: Long): Long = get(key to key).head.start

    def get(range: LongRange): Seq[LongRange] =
      val result = IndexedSeq.newBuilder[LongRange]
      var first = range.start
      val last = range.last
      if first < triples.head.src then
        result += first until triples.head.src
        first = triples.head.src
      var idx = 0
      while idx < triples.size && triples(idx).srcEnd <= first do
        idx += 1
      while first <= last && idx < triples.size do
        val thisEnd = triples(idx).srcEnd min (last + 1)
        result += triples(idx).map(first) until triples(idx).map(thisEnd)
        first = thisEnd
        idx += 1
      if idx == triples.size && first <= last then
        result += first to last
      result.result()

  private def parseMap(input: Seq[String]): FunMap =
    val ranges = input.tail.map:
      case s"$trg $src $len" => FunRange(src.toLong, trg.toLong, len.toLong)
    FunMap(ranges)

  private def applyChain1(value: Seq[FunMap])(key: Long) = value.foldLeft(key)((v, m) => m.get(v))
  private def applyChain2(value: Seq[FunMap])(key: LongRange) = value.foldLeft(Seq(key))((v, m) => v.flatMap(m.get))

  private def parseInput(input: Seq[String]): (Seq[Long], Seq[FunMap]) =
    val paragraphs = SeqUtil.splitBySeparator(input, _.isEmpty)
    val seeds = paragraphs.head.head match
      case s"seeds: $seedSeq" => SeqUtil.tokenMap(seedSeq, " ", _.toLong)
    val maps = paragraphs.tail.map(parseMap)
    (seeds, maps)

  override def part1(input: IndexedSeq[String]): String =
    val (seeds, maps) = parseInput(input)
    val mapped = seeds.map(applyChain1(maps))
    mapped.min.toString

  override def part2(input: IndexedSeq[String]): String =
    val (seeds, maps) = parseInput(input)
    val ranges = seeds.grouped(2).map(g => g.head until (g.head + g.last)).toIndexedSeq
    val resultRanges = ranges.flatMap(applyChain2(maps))
    resultRanges.map(_.start).min.toString
end D05
