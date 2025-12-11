package aoc.y2025

import algo.SeqUtil
import aoc.Runner

import scala.collection.mutable.HashMap as MuMap

object D11 extends Runner:
  private def parseLine(s: String): (String, IndexedSeq[String]) =
    val tok = SeqUtil.tokens(s, ": ")
    (tok.head, tok.tail)

  private def dfs(graph: Map[String, IndexedSeq[String]], node: String, dest: String, dist: MuMap[String, Long]): Long =
    if node == dest then 1 else dist.get(node) match
      case Some(v) => v
      case None => graph.get(node) match
        case None => 0
        case Some(seq) =>
          val result = seq.map(n => dfs(graph, n, dest, dist)).sum
          dist.put(node, result)
          result
  
  override def part1(input: IndexedSeq[String]): String =
    val graph = input.map(parseLine).toMap
    dfs(graph, "you", "out", new MuMap()).toString
    
  override def part2(input: IndexedSeq[String]): String =
    val graph = input.map(parseLine).toMap
    val svr2fft = dfs(graph, "svr", "fft", new MuMap())
    val svr2dac = dfs(graph, "svr", "dac", new MuMap())
    val fft2dac = dfs(graph, "fft", "dac", new MuMap())
    val dac2fft = dfs(graph, "dac", "fft", new MuMap())
    val fft2out = dfs(graph, "fft", "out", new MuMap())
    val dac2out = dfs(graph, "dac", "out", new MuMap())
    (svr2fft * fft2dac * dac2out + svr2dac * dac2fft * fft2out).toString
end D11
