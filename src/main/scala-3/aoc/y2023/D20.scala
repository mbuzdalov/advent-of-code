package aoc.y2023

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.chaining.*

import algo.{Loops, SeqUtil}
import aoc.Runner

object D20 extends Runner:
  private trait Module:
    val targets = new mutable.ArrayBuffer[String]
    def name: String
    def value: Boolean
    def connect(sources: String): Unit = SeqUtil.tokens(sources, ", ").foreach(targets += _)
    def connectIncoming(source: String): Unit = {}
    def receive(source: String, level: Boolean, target: mutable.ArrayDeque[(String, String, Boolean)]): Unit

  private class Conjunction(val name: String) extends Module:
    private val lastPulses = mutable.HashMap[String, Boolean]()
    private var nPositive = 0

    def value: Boolean = lastPulses.size != nPositive

    override def connectIncoming(source: String): Unit = lastPulses.put(source, false)

    override def receive(source: String, level: Boolean, target: mutable.ArrayDeque[(String, String, Boolean)]): Unit =
      if lastPulses(source) then nPositive -= 1
      if level then nPositive += 1
      lastPulses(source) = level
      for t <- targets do target.addOne((name, t, value))

  private class FlipFlop(val name: String) extends Module:
    private var state = false
    def value: Boolean = state

    override def receive(source: String, level: Boolean, target: mutable.ArrayDeque[(String, String, Boolean)]): Unit =
      if !level then
        state = !state
        for t <- targets do target.addOne((name, t, state))

  private class Broadcaster(val name: String) extends Module:
    def value: Boolean = false
    override def receive(source: String, level: Boolean, target: mutable.ArrayDeque[(String, String, Boolean)]): Unit =
      for t <- targets do target.addOne((name, t, level))

  private class PeriodDetector(name: String):
    private val q = new Array[Boolean](8193)
    private var minPeriod = 1
    private var suppressMessage = false

    def maybePeriod: Option[BigInt] = if suppressMessage then Some(BigInt(minPeriod)) else None

    @tailrec
    private def isPeriod(p: Int, max: Int): Boolean =
      if max - p < 0 then true else q(max) == q(max - p) && isPeriod(p, max - 1)

    def accept(turn: Int, value: Boolean): Unit =
      q(turn) = value
      if turn >= minPeriod then
        if q(turn) != q(turn - minPeriod) then
          minPeriod += 1
          while !isPeriod(minPeriod, turn) do minPeriod += 1
          suppressMessage = false
      if turn > 2 * minPeriod && !suppressMessage then
        suppressMessage = true

  private def readMap(input: IndexedSeq[String]): scala.collection.mutable.HashMap[String, Module] =
    val map = scala.collection.mutable.HashMap[String, Module]()

    input.foreach:
      case s"broadcaster -> $list" => map.put("broadcaster", Broadcaster("broadcaster").tap(_.connect(list)))
      case s"%$name -> $list" => map.put(name, FlipFlop(name).tap(_.connect(list)))
      case s"&$name -> $list" => map.put(name, Conjunction(name).tap(_.connect(list)))

    for
      m <- map.values
      n <- m.targets
      if map.contains(n)
    do map(n).connectIncoming(m.name)

    map

  override def part1(input: IndexedSeq[String]): String =
    val map = readMap(input)
    val queue = new mutable.ArrayDeque[(String, String, Boolean)]()
    var nFalse, nTrue = 0
    Loops.foreach(0, 1000): t =>
      queue.addOne(("button", "broadcaster", false))
      while queue.nonEmpty do
        val (source, curr, value) = queue.removeHead()
        if value then nTrue += 1 else nFalse += 1
        if map.contains(curr) then
          map(curr).receive(source, value, queue)

    (nFalse.toLong * nTrue).toString

  override def part2(input: IndexedSeq[String]): String =
    val map = readMap(input)
    val queue = mutable.ArrayDeque[(String, String, Boolean)]()
    var nPresses = 0

    val pd = map.values.map(m => new PeriodDetector(m.name))

    while nPresses < 8192 do
      nPresses += 1
      queue.addOne(("button", "broadcaster", false))
      while queue.nonEmpty do
        val (source, curr, value) = queue.removeHead()
        if map.contains(curr) then
          map(curr).receive(source, value, queue)
      map.values.zip(pd).foreach(p => p._2.accept(nPresses, p._1.value))

    pd.flatMap(_.maybePeriod).reduce((a, b) => a * b / a.gcd(b)).toString
end D20
