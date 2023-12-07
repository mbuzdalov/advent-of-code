package aoc.y2022

import aoc.TestingUtil

class T09 extends TestingUtil(D09):
  override def answer1: String = "13"
  override def input1: String =
    """R 4
      |U 4
      |L 3
      |D 1
      |R 4
      |D 1
      |L 5
      |R 2
      |""".stripMargin

  override def answer2: String = "36"
  override def input2: String =
    """R 5
      |U 8
      |L 8
      |D 3
      |R 17
      |D 10
      |L 25
      |U 20
      |""".stripMargin
end T09
