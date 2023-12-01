package aoc.y2022

import aoc.TestingUtil

class T02 extends TestingUtil(D02):
  override def answer1: String = "15"
  override def input1: String =
    """A Y
      |B X
      |C Z
      |""".stripMargin

  override def answer2: String = "12"
  override def input2: String = input1
end T02
