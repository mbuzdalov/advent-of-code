package aoc.y2023

import aoc.TestingUtil

class T06 extends TestingUtil(D06):
  override def answer1: String = "288"
  override def input1: String =
    """Time:      7  15   30
      |Distance:  9  40  200
      |""".stripMargin

  override def answer2: String = "71503"
  override def input2: String = input1
end T06
