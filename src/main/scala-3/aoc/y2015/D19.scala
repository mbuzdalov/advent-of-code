package aoc.y2015

import scala.annotation.tailrec

import algo.SeqUtil
import aoc.Runner

object D19 extends Runner:
  @tailrec
  private def tryReplacement(str: String, from: String, to: String, index: Int, target: String => Unit): Unit =
    val nextIdx = str.indexOf(from, index)
    if nextIdx != -1 then
      target(str.substring(0, nextIdx) + to + str.substring(nextIdx + from.length))
      tryReplacement(str, from, to, index + 1, target)

  override def part1(input: IndexedSeq[String]): String =
    val Seq(replacements, init) = SeqUtil.splitBySeparator(input, _.isEmpty)
    val hash = new scala.collection.mutable.HashSet[String]
    replacements foreach:
      case s"$from => $to" => tryReplacement(init(0), from, to, 0, hash += _)
    hash.size.toString

  private class Context(replacements: IndexedSeq[String], str: String):
    private def partition(str: String): IndexedSeq[String] =
      val builder = IndexedSeq.newBuilder[String]
      var i = 0
      while i < str.length do
        val size = if i + 1 < str.length && 'a' <= str(i + 1) && str(i + 1) <= 'z' then 2 else 1
        builder += str.substring(i, i + size)
        i += size
      builder.result()

    private val nonTerminals = replacements.map:
      case s"$from => $to" => (from, partition(to))
    .groupMap(_._1)(_._2)

    private val cache1 = scala.collection.mutable.HashMap[(String, Int, Int), Int]()
    private val cache2 = scala.collection.mutable.HashMap[(IndexedSeq[String], Int, Int), Int]()
    private val init = partition(str)
    private final val Inf = 1000000000

    def compute(lhs: String): Int = compute(lhs, 0, init.size)

    private def tryMatch(rhs: IndexedSeq[String], from: Int, until: Int): Int =
      if from == until || rhs.isEmpty then
        if (from == until) == rhs.isEmpty then 0 else Inf
      else cache2.getOrElseUpdate((rhs, from, until), {
        var result = Inf
        val rh = rhs.head
        val rt = rhs.tail
        if init(from) == rh then
          result = tryMatch(rt, from + 1, until)
        if nonTerminals.contains(rh) then
          var callEnd = from + 1
          while callEnd <= until - rt.size do
            val call = compute(rh, from, callEnd)
            if call < result then
              val tail = tryMatch(rt, callEnd, until)
              result = math.min(result, tail + call)
            callEnd += 1
        result
      })

    private def compute(lhs: String, from: Int, until: Int): Int = if from == until then Inf else
      cache1.getOrElseUpdate((lhs, from, until), 1 + nonTerminals(lhs).map(rhs => tryMatch(rhs, from, until)).min)

  override def part2(input: IndexedSeq[String]): String =
    val Seq(replacements, init) = SeqUtil.splitBySeparator(input, _.isEmpty)
    Context(replacements, init(0)).compute("e").toString
end D19
