package aoc.y2022

import aoc.Runner

object D10 extends Runner:
  private class State(logger: (Int, Int) => Unit):
    private var time = 1
    private var value = 1

    logger(time, value)

    def execute(line: String): Unit =
      time += 1
      logger(time, value)
      line match
        case s"addx $v" =>
          time += 1
          value += v.toInt
          logger(time, value)
        case _ =>

  override def part1(input: IndexedSeq[String]): String =
    var answer = 0L
    val state = new State((time, value) => if time % 40 == 20 then answer += time.toLong * value)
    input.foreach(state.execute)
    answer.toString

  private def printFunction(builder: StringBuilder)(time: Int, value: Int): Unit =
    builder += (if math.abs((time - 1) % 40 - value) <= 1 then '#' else '.')
    if time % 40 == 0 then builder += '\n'

  override def part2(input: IndexedSeq[String]): String =
    val builder = StringBuilder()
    val state = new State(printFunction(builder))
    input.foreach(state.execute)
    builder.result().init
end D10
