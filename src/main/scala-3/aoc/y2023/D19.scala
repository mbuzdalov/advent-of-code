package aoc.y2023

import scala.annotation.tailrec

import algo.SeqUtil
import aoc.Runner

object D19 extends Runner:
  private case class Part(x: Int, m: Int, a: Int, s: Int):
    def rating: Int = x + m + a + s

  private case class PartRange(xMin: Int, xMax: Int, mMin: Int, mMax: Int, aMin: Int, aMax: Int, sMin: Int, sMax: Int):
    def size: Long = (xMax - xMin + 1L) * (mMax - mMin + 1L) * (aMax - aMin + 1L) * (sMax - sMin + 1L)

  private def parsePart(line: String): Part = line match
    case s"{x=$x,m=$m,a=$a,s=$s}" => Part(x.toInt, m.toInt, a.toInt, s.toInt)

  private def parseRule(line: String): Part => Option[String] = line match
    case s"x>$a:$t" => p => if p.x > a.toInt then Some(t) else None
    case s"x<$a:$t" => p => if p.x < a.toInt then Some(t) else None
    case s"m>$a:$t" => p => if p.m > a.toInt then Some(t) else None
    case s"m<$a:$t" => p => if p.m < a.toInt then Some(t) else None
    case s"a>$a:$t" => p => if p.a > a.toInt then Some(t) else None
    case s"a<$a:$t" => p => if p.a < a.toInt then Some(t) else None
    case s"s>$a:$t" => p => if p.s > a.toInt then Some(t) else None
    case s"s<$a:$t" => p => if p.s < a.toInt then Some(t) else None
    case t => _ => Some(t)

  private def parseRule2(line: String): PartRange => (Option[(PartRange, String)], Option[PartRange]) = line match
    case s"x>$a:$t" =>
      val v = a.toInt
      p => if p.xMin > v then (Some(p, t), None) else  if p.xMax <= v then (None, Some(p)) else (Some(p.copy(xMin = v + 1), t), Some(p.copy(xMax = v)))
    case s"x<$a:$t" =>
      val v = a.toInt
      p => if p.xMax < v then (Some(p, t), None) else if p.xMin >= v then (None, Some(p)) else (Some(p.copy(xMax = v - 1), t), Some(p.copy(xMin = v)))
    case s"m>$a:$t" =>
      val v = a.toInt
      p => if p.mMin > v then (Some(p, t), None) else  if p.mMax <= v then (None, Some(p)) else (Some(p.copy(mMin = v + 1), t), Some(p.copy(mMax = v)))
    case s"m<$a:$t" =>
      val v = a.toInt
      p => if p.mMax < v then (Some(p, t), None) else if p.mMin >= v then (None, Some(p)) else (Some(p.copy(mMax = v - 1), t), Some(p.copy(mMin = v)))
    case s"a>$a:$t" =>
      val v = a.toInt
      p => if p.aMin > v then (Some(p, t), None) else  if p.aMax <= v then (None, Some(p)) else (Some(p.copy(aMin = v + 1), t), Some(p.copy(aMax = v)))
    case s"a<$a:$t" =>
      val v = a.toInt
      p => if p.aMax < v then (Some(p, t), None) else if p.aMin >= v then (None, Some(p)) else (Some(p.copy(aMax = v - 1), t), Some(p.copy(aMin = v)))
    case s"s>$a:$t" =>
      val v = a.toInt
      p => if p.sMin > v then (Some(p, t), None) else  if p.sMax <= v then (None, Some(p)) else (Some(p.copy(sMin = v + 1), t), Some(p.copy(sMax = v)))
    case s"s<$a:$t" =>
      val v = a.toInt
      p => if p.sMax < v then (Some(p, t), None) else if p.sMin >= v then (None, Some(p)) else (Some(p.copy(sMax = v - 1), t), Some(p.copy(sMin = v)))
    case t => p => (Some(p, t), None)

  @tailrec
  private def decide(map: scala.collection.mutable.HashMap[String, IndexedSeq[Part => Option[String]]],
                     part: Part, curr: String): Boolean =
    map(curr).flatMap(r => r(part)).head match
      case "A" => true
      case "R" => false
      case next => decide(map, part, next)

  private def decide(map: scala.collection.mutable.HashMap[String, IndexedSeq[PartRange => (Option[(PartRange, String)], Option[PartRange])]],
                     partRange: PartRange, curr: String): Long =
    if curr == "A" then partRange.size else if curr == "R" then 0 else
      val wf = map(curr)
      var wfi = 0
      var r: Option[PartRange] = Some(partRange)
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
    val map = scala.collection.mutable.HashMap[String, IndexedSeq[Part => Option[String]]]()
    wfS.foreach:
      case s"$name{$chain}" => map.put(name, SeqUtil.tokenMap(chain, ",", parseRule))
    parts.filter(p => decide(map, p, "in")).map(_.rating).sum.toString

  override def part2(input: IndexedSeq[String]): String =
    val Seq(wfS, partsS) = SeqUtil.splitBySeparator(input, _.isEmpty)
    val map = scala.collection.mutable.HashMap[String, IndexedSeq[PartRange => (Option[(PartRange, String)], Option[PartRange])]]()
    wfS.foreach:
      case s"$name{$chain}" => map.put(name, SeqUtil.tokenMap(chain, ",", parseRule2))
    decide(map, PartRange(1, 4000, 1, 4000, 1, 4000, 1, 4000), "in").toString
end D19
