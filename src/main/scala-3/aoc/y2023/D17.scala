package aoc.y2023

import algo.Loops
import aoc.Runner

object D17 extends Runner:
  private val dRow = Array(+1, 0, -1, 0)
  private val dCol = Array(0, +1, 0, -1)

  case class State(row: Int, col: Int, dir: Int, same: Int)
  case class StatePair(state: State, dist: Int) extends Ordered[StatePair]:
    override def compare(that: StatePair): Int = -Integer.compare(dist, that.dist)

  private def solve(input: IndexedSeq[String], maxSame: Int, minDiff: Int): Int =
    val nRows = input.size
    val nCols = input(0).length
    val h = scala.collection.mutable.HashMap[State, Int]()
    val q = scala.collection.mutable.PriorityQueue[StatePair]()
    h.put(State(0, 0, 0, 0), 0)
    h.put(State(0, 0, 1, 0), 0)

    q.addOne(StatePair(State(0, 0, 0, 0), 0))
    q.addOne(StatePair(State(0, 0, 1, 0), 0))

    while q.nonEmpty do
      val curr = q.dequeue()
      if curr.dist == h(curr.state) then
        val nr = curr.state.row + dRow(curr.state.dir)
        val nc = curr.state.col + dCol(curr.state.dir)
        if nr >= 0 && nc >= 0 && nr < nRows && nc < nCols then
          val nd = curr.dist + input(nr)(nc) - '0'
          Loops.foreach(0, 4): d =>
            if d != ((curr.state.dir + 2) & 3)
              && (d != curr.state.dir || curr.state.same <= maxSame - 2)
              && (d == curr.state.dir || curr.state.same >= minDiff - 1)
            then
              val newState = State(nr, nc, d, if d == curr.state.dir then curr.state.same + 1 else 0)
              if !h.contains(newState) || h(newState) > nd then
                h.put(newState, nd)
                q.addOne(StatePair(newState, nd))

    h.filter(p => p._1.row == nRows - 1 && p._1.col == nCols - 1 && p._1.same >= minDiff).values.min

  override def part1(input: IndexedSeq[String]): String = solve(input, 3, 0).toString
  override def part2(input: IndexedSeq[String]): String = solve(input, 10, 4).toString
end D17
