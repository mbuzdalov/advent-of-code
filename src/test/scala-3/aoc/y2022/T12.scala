package aoc.y2022

import aoc.TestingUtil

class T12 extends TestingUtil(D12):
  override def answer1: String = "31"
  override def input1: String =
    """Sabqponm
      |abcryxxl
      |accszExk
      |acctuvwj
      |abdefghi
      |""".stripMargin

  override def answer2: String = "29"
  override def input2: String = input1
end T12
