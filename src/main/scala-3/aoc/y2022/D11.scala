package aoc.y2022

import scala.collection.mutable

import algo.SeqUtil
import aoc.Runner

object D11 extends Runner:
  private class Monkey(divisor: Int, upFun: BigInt => BigInt, val modulo: BigInt, onTrue: Int, onFalse: Int, init: Seq[BigInt]):
    private val queue = new mutable.ArrayDeque[BigInt]().addAll(init)
    var nProcessed: Long = 0
    def process(others: IndexedSeq[Monkey], takeModulo: BigInt): Unit =
      while queue.nonEmpty do
        nProcessed += 1
        val item0 = upFun(queue.removeHead()) / divisor
        val item = if takeModulo != null then item0 % takeModulo else item0
        others(if item % modulo == 0 then onTrue else onFalse).queue.addOne(item)

  private object Monkey:
    private def parseArg(a: String): BigInt => BigInt = if a == "old" then x => x else x => a.toLong

    def parse(divisor: Int)(input: IndexedSeq[String]): Monkey =
      val starting = input(1) match
        case s"  Starting items: $items" => SeqUtil.tokenMap(items, " ,", v => BigInt(v))
      val operation: BigInt => BigInt = input(2) match
        case s"  Operation: new = $arg1 $op $arg2" =>
          val fun1 = parseArg(arg1)
          val fun2 = parseArg(arg2)
          x => if op == "+" then fun1(x) + fun2(x) else fun1(x) * fun2(x)
      val test = input(3) match
        case s"  Test: divisible by $div" => div.toLong
      val onTrue = input(4) match
        case s"    If true: throw to monkey $trueMonkey" => trueMonkey.toInt
      val onFalse = input(5) match
        case s"    If false: throw to monkey $falseMonkey" => falseMonkey.toInt
      Monkey(divisor, operation, test, onTrue, onFalse, starting)

  private def solve(input: IndexedSeq[String], divisor: Int, rounds: Int, takeModulo: Boolean): Long =
    val monkeys = SeqUtil.splitBySeparator(input, _.isEmpty).map(Monkey.parse(divisor))
    val theModulo = if takeModulo then monkeys.map(_.modulo).product else null
    for round <- 0 until rounds do
      for m <- monkeys do
        m.process(monkeys, theModulo)
    monkeys.map(_.nProcessed).sorted.takeRight(2).product

  override def part1(input: IndexedSeq[String]): String = solve(input, 3, 20, false).toString
  override def part2(input: IndexedSeq[String]): String = solve(input, 1, 10000, true).toString
end D11
