package aoc.y2022

import aoc.TestingUtil

class T08 extends TestingUtil(D08):
  override def answer1: String = "21"
  override def input1: String =
    """30373
      |25512
      |65332
      |33549
      |35390
      |""".stripMargin

  override def answer2: String = "8"
  override def input2: String = input1
end T08
