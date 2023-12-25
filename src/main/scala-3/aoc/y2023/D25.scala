package aoc.y2023

import algo.{Loops, SeqUtil}
import aoc.Runner

object D25 extends Runner:
  private class GraphBuilder:
    private val init, nextV, nextE = scala.collection.mutable.ArrayBuffer[Int]()

    def addEdge(a: Int, b: Int): Unit =
      val max = math.max(a, b)
      while init.size <= max do init.addOne(-1)
      nextV.addOne(b)
      nextE.addOne(init(a))
      init(a) = nextV.size - 1
      nextV.addOne(a)
      nextE.addOne(init(b))
      init(b) = nextV.size - 1

    def result(): Graph = Graph(init.toArray, nextV.toArray, nextE.toArray)

  private class Graph(init: Array[Int], nextV: Array[Int], nextE: Array[Int]):
    private val enabled = new Array[Boolean](nextE.length >>> 1)
    private var timestampSource = 0
    private var nEnabled = 0

    def nVertices: Int = init.length
    def nEdges: Int = enabled.length

    def setEnabled(edge: Int, value: Boolean): Unit =
      if value != enabled(edge) then
        enabled(edge) = value
        if value then nEnabled += 1 else nEnabled -= 1

    def nEnabledEdges: Int = nEnabled

    def reset(): Unit =
      Loops.foreach(0, nEdges)(e => setEnabled(e, true))
      assert(nEnabled == nEdges)
      timestampSource = 0

    private inline def adjacent(vertex: Int, prev: Int)(inline fun: (Int, Int) => Any): Unit =
      var e = init(vertex)
      while e != -1 do
        val v = nextV(e)
        if v != prev && enabled(e >>> 1) then fun(e, v)
        e = nextE(e)

    def findAndDisableBridge(curr: Int, prev: Int, ts: Array[Int]): Int =
      if prev == -1 then
        timestampSource += 1
        ts(curr) = timestampSource
      var minTS = init.length * 2
      adjacent(curr, prev): (edge, next) =>
        if ts(next) > 0 then
          minTS = math.min(minTS, ts(next))
        else
          timestampSource += 1
          ts(next) = timestampSource
          val call = findAndDisableBridge(next, curr, ts)
          minTS = math.min(minTS, call)
          if call >= ts(next) then
            // bridge is found
            setEnabled(edge >>> 1, false)
      minTS

    def componentSize(curr: Int, prev: Int, ts: Array[Int]): Int =
      if ts(curr) == 1 then 0 else
        ts(curr) = 1
        var size = 1
        adjacent(curr, prev): (edge, next) =>
          size += componentSize(next, curr, ts)
        size

  override def part1(input: IndexedSeq[String]): String =
    val hash = scala.collection.mutable.HashMap[String, Int]()
    val graphB = new GraphBuilder
    input.foreach:
      case s"$src: $trg" =>
        val srcV = hash.getOrElseUpdate(src, hash.size)
        SeqUtil.tokens(trg, " ").foreach(str => graphB.addEdge(srcV, hash.getOrElseUpdate(str, hash.size)))
    val graph = graphB.result()
    val nVertices = graph.nVertices
    val nEdges = graph.nEdges
    val ts = new Array[Int](nVertices)
    val resultB = IndexedSeq.newBuilder[Int]
    val result =
      Loops.findFirst(0, nEdges): e1 =>
        Loops.findFirst(e1 + 1, nEdges): e2 =>
          graph.reset()
          graph.setEnabled(e1, false)
          graph.setEnabled(e2, false)
          java.util.Arrays.fill(ts, 0)
          graph.findAndDisableBridge(0, -1, ts)
          if graph.nEnabledEdges == nEdges - 3 then
            java.util.Arrays.fill(ts, 0)
            val c0 = graph.componentSize(0, -1, ts)
            Some(c0 * (nVertices - c0))
          else None
    result.get.toString

  override def part2(input: IndexedSeq[String]): String = "no part 2 on the last day"
end D25
