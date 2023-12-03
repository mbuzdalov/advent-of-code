package aoc.y2022

import aoc.TestingUtil

class T05 extends TestingUtil(D05):
  override def answer1: String = "CMZ"
  override def input1: String =
    """    [D]
      |[N] [C]
      |[Z] [M] [P]
      | 1   2   3
      |
      |move 1 from 2 to 1
      |move 3 from 1 to 3
      |move 2 from 2 to 1
      |move 1 from 1 to 2
      |""".stripMargin

  override def answer2: String = "MCD"
  override def input2: String = input1
end T05
