package aoc.y2022

import aoc.TestingUtil

class T04 extends TestingUtil(D04):
  override def answer1: String = "2"
  override def input1: String =
    """2-4,6-8
      |2-3,4-5
      |5-7,7-9
      |2-8,3-7
      |6-6,4-6
      |2-6,4-8
      |""".stripMargin

  override def answer2: String = "4"
  override def input2: String = input1
end T04
