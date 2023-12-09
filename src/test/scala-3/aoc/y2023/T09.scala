package aoc.y2023

import aoc.TestingUtil

class T09 extends TestingUtil(D09):
  override def answer1: String = "114"
  override def answer2: String = "2"
  override def input1: String =
    """0 3 6 9 12 15
      |1 3 6 10 15 21
      |10 13 16 21 30 45
      |""".stripMargin

  override def input2: String = input1
end T09
