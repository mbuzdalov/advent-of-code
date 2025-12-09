package aoc.y2023

import scala.annotation.tailrec

import algo.SeqUtil
import aoc.Runner

object D19 extends Runner:
  private def parsePart(line: String): Map[String, Int] = line match
    case s"{x=$x,m=$m,a=$a,s=$s}" => Map("x" -> x.toInt, "m" -> m.toInt, "a" -> a.toInt, "s" -> s.toInt)

  private def parseMap[T](input: IndexedSeq[String], parseRule: String => T) =
    input.map:
      case s"$name{$chain}" => (name, SeqUtil.tokenMap(chain, ",", parseRule))
    .toMap

  private def parseRule1(line: String)(p: Map[String, Int]): Option[String] = line match
    case s"$k>$a:$t" => if p(k) > a.toInt then Some(t) else None
    case s"$k<$a:$t" => if p(k) < a.toInt then Some(t) else None
    case t => Some(t)

  private def parseRule2(line: String)(p: Map[String, Range]): (Option[(Map[String, Range], String)], Option[Map[String, Range]]) = line match
    case s"$k>$a:$t" =>
      val v = a.toInt
      val r = p(k)
      (if r.last > v then Some(p.updated(k, v + 1 to r.last) -> t) else None,
        if r.head <= v then Some(p.updated(k, r.head to v)) else None)
    case s"$k<$a:$t" =>
      val v = a.toInt
      val r = p(k)
      (if r.head < v then Some(p.updated(k, r.head until v) -> t) else None,
        if r.last >= v then Some(p.updated(k, v to r.last)) else None)
    case t => (Some(p -> t), None)

  @tailrec
  private def decide(map: Map[String, IndexedSeq[Map[String, Int] => Option[String]]],
                     part: Map[String, Int], curr: String): Boolean = curr match
    case "A" => true
    case "R" => false
    case v => decide(map, part, map(v).flatMap(r => r(part)).head)

  private def decide(map: Map[String, IndexedSeq[Map[String, Range] => (Option[(Map[String, Range], String)], Option[Map[String, Range]])]],
                     partRange: Map[String, Range], curr: String): Long =
    if curr == "A" then
      partRange.values.map(r => r.last - r.head + 1L).product
    else if curr == "R" then 0 else
      val wf = map(curr)
      var wfi = 0
      var r: Option[Map[String, Range]] = Some(partRange)
      var sum = 0L
      while r.nonEmpty do
        val (m, nm) = wf(wfi)(r.get)
        if m.nonEmpty then sum += decide(map, m.get._1, m.get._2)
        r = nm
        wfi += 1
      sum

  override def part1(input: IndexedSeq[String]): String =
    val Seq(wfS, partsS) = SeqUtil.splitBySeparator(input, _.isEmpty)
    val parts = partsS.map(parsePart)
    val map = parseMap(wfS, parseRule1)
    parts.filter(p => decide(map, p, "in")).map(_.values.sum).sum.toString

  override def part2(input: IndexedSeq[String]): String =
    val Seq(wfS, partsS) = SeqUtil.splitBySeparator(input, _.isEmpty)
    val map = parseMap(wfS, parseRule2)
    decide(map, Map("x" -> (1 to 4000), "m" -> (1 to 4000), "a" -> (1 to 4000), "s" -> (1 to 4000)), "in").toString
end D19
