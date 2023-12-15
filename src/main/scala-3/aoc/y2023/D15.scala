package aoc.y2023

import algo.SeqUtil
import aoc.Runner

object D15 extends Runner:
  private def hash(str: String): Int = str.foldLeft(0)((h, c) => ((h + c) * 17) & 255)
  private class Entry:
    var focus: Int = -1
    var prev, next: Entry = this

  private def process(table: Array[Entry], rep: scala.collection.mutable.HashMap[String, Entry])(str: String): Unit =
    str match
      case s"$label-" =>
        rep.get(label) match
          case None =>
          case Some(v) =>
            v.prev.next = v.next
            v.next.prev = v.prev
            rep.remove(label)
      case s"$label=$focus" =>
        val hv = hash(label)
        rep.get(label) match
          case Some(v) =>
            v.focus = focus.toInt
          case None =>
            val entry = new Entry
            rep.put(label, entry)
            entry.focus = focus.toInt
            entry.next = table(hv)
            entry.prev = table(hv).prev
            entry.prev.next = entry
            entry.next.prev = entry
  override def part1(input: IndexedSeq[String]): String =
    SeqUtil.tokenMap(input.mkString(""), ",", hash).sum.toString

  override def part2(input: IndexedSeq[String]): String =
    val table = Array.fill(256)(new Entry)
    val representatives = scala.collection.mutable.HashMap[String, Entry]()
    SeqUtil.tokenMap(input(0), ",", process(table, representatives))
    var result = 0L
    for h <- table.indices do
      var t = table(h).next
      var idx = 1
      while t != table(h) do
        result += (h + 1L) * idx * t.focus
        idx += 1
        t = t.next
    result.toString
end D15
