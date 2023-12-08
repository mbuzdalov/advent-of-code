package aoc.y2023

import aoc.Runner

object D08 extends Runner:
  private class LRMap(input: IndexedSeq[String]):
    private val seq = input(0)
    private val mapLeft, mapRight = scala.collection.mutable.HashMap[String, String]()
    input.drop(2).foreach:
      case s"$node = ($left, $right)" =>
        mapLeft.update(node, left)
        mapRight.update(node, right)
    def apply(time: Int, str: String): String = (if seq(time % seq.length) == 'L' then mapLeft else mapRight)(str)
    def keys: Iterable[String] = mapLeft.keys

  private def repeat(start: String, map: LRMap, predicate: String => Boolean): Int =
    var curr = start
    var time = 0
    while !predicate(curr) do
      curr = map(time, curr)
      time += 1
    time

  override def part1(input: IndexedSeq[String]): String = repeat("AAA", LRMap(input), _ == "ZZZ").toString
  override def part2(input: IndexedSeq[String]): String =
    val map = LRMap(input)
    val hits = map.keys.filter(_.last == 'A').map(str => BigInt(repeat(str, map, _.last == 'Z')))
    // Well, the input just has no pre-cycle anywhere at all!
    hits.reduce((a, b) => a * b / a.gcd(b)).toString()
end D08
