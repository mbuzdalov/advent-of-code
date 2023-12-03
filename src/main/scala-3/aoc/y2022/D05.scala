
package aoc.y2022

import aoc.Runner

object D05 extends Runner:
  private case class Move(from: Int, to: Int, n: Int)

  private def parseState(input: Seq[String]): Seq[String] =
    val n = input.last.count(_ != ' ')
    var state = IndexedSeq.fill(n)("")
    for line <- input.reverse.tail do
      for i <- 0 until n do
        if 1 + i * 4 < line.length then
          val char = line(1 + i * 4)
          if char != ' ' then
            state = state.updated(i, state(i) + char)
    state

  private def parseMove(line: String): Move = line match
    case s"move $n from $from to $to" => Move(from.toInt - 1, to.toInt - 1, n.toInt)

  private def applyMove(atOnce: Boolean)(state: Seq[String], move: Move): Seq[String] =
    val (newFrom, moved) = state(move.from).splitAt(state(move.from).length - move.n)
    val newMoved = if atOnce then moved else moved.reverse
    val newTo = state(move.to) + newMoved
    state.updated(move.from, newFrom).updated(move.to, newTo)

  private def parseInput(input: Seq[String]): (Seq[String], Seq[Move]) =
    val emptyIndex = input.indexOf("")
    val state = parseState(input.take(emptyIndex))
    val moves = input.drop(emptyIndex + 1).map(parseMove)
    (state, moves)

  private def solve(input: Seq[String], atOnce: Boolean): String =
    val (state, moves) = parseInput(input)
    val lastState = moves.foldLeft(state)(applyMove(atOnce))
    lastState.map(_.last).mkString("")

  override def part1(input: Seq[String]): String = solve(input, atOnce = false)
  override def part2(input: Seq[String]): String = solve(input, atOnce = true)
end D05
