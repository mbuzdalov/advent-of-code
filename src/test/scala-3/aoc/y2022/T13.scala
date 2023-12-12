package aoc.y2022

import aoc.TestingUtil

class T13 extends TestingUtil:
  private val input =
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
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D13.part1(input) shouldEqual "13"
  
  it should "be correct for sample 2" in:
    D13.part2(input) shouldEqual "140"
end T13
