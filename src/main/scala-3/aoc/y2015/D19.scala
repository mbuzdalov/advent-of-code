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
        if str(i) == 'e' then
          builder += "e"
          i += 1
        else if 'a' <= str(i) && str(i) <= 'z' then
          assert(false)
        else if i + 1 < str.length && 'a' <= str(i + 1) && str(i + 1) <= 'z' then
          builder += str.substring(i, i + 2)
          i += 2
        else
          builder += str.substring(i, i + 1)
          i += 1
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
        val raw = if init(from) == rhs.head then tryMatch(rhs.tail, from + 1, until) else Inf
        val call = if from + 1 <= until - rhs.tail.size && nonTerminals.contains(rhs.head) then
          (from + 1 to until - rhs.tail.size).map(end => compute(rhs.head, from, end) + tryMatch(rhs.tail, end, until)).min
        else Inf
        math.min(raw, call)
      })

    private def compute(lhs: String, from: Int, until: Int): Int =
      if from == until then Inf
      else if from + 1 == until && nonTerminals.contains(init(from)) then 1
      else cache1.getOrElseUpdate((lhs, from, until), 1 + nonTerminals(lhs).map(rhs => tryMatch(rhs, from, until)).min)

  override def part2(input: IndexedSeq[String]): String =
    val Seq(replacements, init) = SeqUtil.splitBySeparator(input, _.isEmpty)
    Context(replacements, init(0)).compute("e").toString
end D19
