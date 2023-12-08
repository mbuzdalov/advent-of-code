package aoc.y2022

import aoc.Runner

object D13 extends Runner:
  private def compareElems(l: Any, r: Any): Int =
    l match
      case lv: Int => r match
        case rv: Int => lv - rv
        case _ => compareElems(Seq(l), r)
      case lvs: Seq[_] => r match
        case rv: Int => compareElems(l, Seq(r))
        case rvs: Seq[_] =>
          if lvs.isEmpty && rvs.isEmpty then 0
          else if lvs.isEmpty then -1
          else if rvs.isEmpty then +1
          else
            val firstCmp = compareElems(lvs.head, rvs.head)
            if firstCmp != 0 then firstCmp else compareElems(lvs.tail, rvs.tail)

  private def parse(line: String): Any =
    if line(0) == '[' then
      val children = IndexedSeq.newBuilder[Any]
      var depth = 0
      var i = 1
      var last = 0
      while depth >= 0 do
        if line(i) == '[' then depth += 1
        if line(i) == ']' then depth -= 1
        if line(i) == ',' && depth == 0 || line(i) == ']' && depth < 0 then
          if last + 1 < i then  children += parse(line.substring(last + 1, i))
          last = i
        i += 1
      children.result()
    else line.toInt

  override def part1(input: IndexedSeq[String]): String =
    input.filter(_.nonEmpty).map(parse).grouped(2)
      .zipWithIndex.map(p => if compareElems(p._1(0), p._1(1)) < 0 then p._2 + 1 else 0).sum.toString

  override def part2(input: IndexedSeq[String]): String =
    val packets = input.filter(_.nonEmpty).map(parse)
    val p2 = Seq(Seq(2))
    val p6 = Seq(Seq(6))
    val i2 = packets.count(p => compareElems(p, p2) < 0)
    val i6 = packets.count(p => compareElems(p, p6) < 0)
    ((i2 + 1) * (i6 + 2)).toString
end D13
