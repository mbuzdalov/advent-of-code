package aoc.y2022

import aoc.TestingUtil

class T13 extends TestingUtil(D13):
  override def answer1: String = "13"
  override def input1: String =
    """[1,1,3,1,1]
      |[1,1,5,1,1]
      |
      |[[1],[2,3,4]]
      |[[1],4]
      |
      |[9]
      |[[8,7,6]]
      |
      |[[4,4],4,4]
      |[[4,4],4,4,4]
      |
      |[7,7,7,7]
      |[7,7,7]
      |
      |[]
      |[3]
      |
      |[[[]]]
      |[[]]
      |
      |[1,[2,[3,[4,[5,6,7]]]],8,9]
      |[1,[2,[3,[4,[5,6,0]]]],8,9]
      |""".stripMargin

  override def answer2: String = "140"
  override def input2: String = input1
end T13
