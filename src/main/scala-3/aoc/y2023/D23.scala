package aoc.y2023

import scala.collection.mutable

import algo.Loops
import aoc.Runner

object D23 extends Runner:
  private val dRow = Array(+1, 0, -1, 0)
  private val dCol = Array(0, +1, 0, -1)
  private val allowed1 = Map('.' -> Seq(0, 1, 2, 3), '>' -> Seq(1), 'v' -> Seq(0), '<' -> Seq(3), '^' -> Seq(2))
  private val allowed2 = ".>v<^".map(k => k -> Seq(0, 1, 2, 3)).toMap

  private def isNode(input: IndexedSeq[String], row: Int, col: Int): Boolean = input(row)(col) != '#' && {
    var count = 0
    Loops.foreach(0, 4): d =>
      if input(row + dRow(d))(col + dCol(d)) != '#' then count += 1
    count > 2
  }

  private case class Edge(target: (Int, Int), distance: Int)

  private def floodConnect(allowed: Map[Char, Seq[Int]])(input: IndexedSeq[String], node: (Int, Int), nodes: Set[(Int, Int)]): Seq[Edge] =
    val queue = mutable.ArrayDeque[(Int, Int)]()
    val dist = mutable.HashMap[(Int, Int), Int]()
    queue.addOne(node)
    dist.put(node, 0)
    val result = IndexedSeq.newBuilder[Edge]
    while queue.nonEmpty do
      val curr = queue.removeHead()
      for d <- allowed(input(curr._1)(curr._2)) do
        val next = (curr._1 + dRow(d), curr._2 + dCol(d))
        if nodes.contains(next) && next != node then
          result += Edge(next, dist(curr) + 1)
        else if !dist.contains(next) &&
          next._1 >= 0 && next._1 < input.size &&
          next._2 >= 0 && next._2 < input(next._1).length &&
          input(next._1)(next._2) != '#'
        then
          queue.addOne(next)
          dist.put(next, dist(curr) + 1)
    result.result()

  private def traverse(graph: Map[(Int, Int), Seq[Edge]], start: (Int, Int), finish: (Int, Int), distances: mutable.HashMap[(Int, Int), Int]): Unit =
    if finish == start then
      distances.put(start, 0)
    else if !distances.contains(start) then
      distances.put(start, -1)
      var result = 0
      for edge <- graph(start) do
        traverse(graph, edge.target, finish, distances)
        result = math.max(result, distances(edge.target) + edge.distance)
      distances.put(start, result)

  private def traverse2(graph: Map[(Int, Int), Seq[Edge]], start: (Int, Int), finish: (Int, Int), used: mutable.Set[(Int, Int)]): Int =
    if finish == start then
      0
    else if !used.contains(start) then
      used.add(start)
      var result = -2000000000
      for edge <- graph(start) do
        result = math.max(result, edge.distance + traverse2(graph, edge.target, finish, used))
      used.remove(start)
      result
    else -2000000000

  override def part1(input: IndexedSeq[String]): String =
    val nRows = input.size
    val nCols = input(0).length
    val startCol = input(0).indexOf('.')
    val endCol = input(nRows - 1).indexOf('.')
    val nodesBuilder = Set.newBuilder[(Int, Int)]
    nodesBuilder += (0 -> startCol)
    nodesBuilder += ((nRows - 1) -> endCol)
    Loops.foreach(1, nRows - 1): row =>
      Loops.foreach(1, nCols - 1): col =>
        if isNode(input, row, col) then nodesBuilder += (row -> col)
    val nodes = nodesBuilder.result()
    val graph = nodes.map(n => (n, floodConnect(allowed1)(input, n, nodes))).toMap
    val distances = mutable.HashMap[(Int, Int), Int]()
    traverse(graph, (0, startCol), (nRows - 1, endCol), distances)
    distances(0 -> startCol).toString

  override def part2(input: IndexedSeq[String]): String =
    val nRows = input.size
    val nCols = input(0).length
    val startCol = input(0).indexOf('.')
    val endCol = input(nRows - 1).indexOf('.')
    val nodesBuilder = Set.newBuilder[(Int, Int)]
    nodesBuilder += (0 -> startCol)
    nodesBuilder += ((nRows - 1) -> endCol)
    Loops.foreach(1, nRows - 1): row =>
      Loops.foreach(1, nCols - 1): col =>
        if isNode(input, row, col) then nodesBuilder += (row -> col)
    val nodes = nodesBuilder.result()
    val graph = nodes.map(n => (n, floodConnect(allowed2)(input, n, nodes))).toMap
    traverse2(graph, (0, startCol), (nRows - 1, endCol), mutable.HashSet[(Int, Int)]()).toString

end D23
