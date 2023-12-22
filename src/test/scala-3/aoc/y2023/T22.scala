package aoc.y2023

import aoc.TestingUtil

class T22 extends TestingUtil:
  private val input =
    """1,0,1~1,2,1
      |0,0,2~2,0,2
      |0,2,3~2,2,3
      |0,0,4~0,2,4
      |2,0,5~2,2,5
      |0,1,6~2,1,6
      |1,1,8~1,1,9
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D22.part1(input) shouldEqual "5"

  it should "be correct for sample 2" in:
    D22.part2(input) shouldEqual "7"

  it should "solve part 1" in:
    D22.part1(mainInput) shouldEqual "495"

  it should "solve part 2" in:
    D22.part2(mainInput) shouldEqual "76158"
end T22
