package aoc.y2022

import aoc.TestingUtil

class T01 extends TestingUtil:
  private val input =
    """1000
      |2000
      |3000
      |
      |4000
      |
      |5000
      |6000
      |
      |7000
      |8000
      |9000
      |
      |10000
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D01.part1(input) shouldEqual "24000"
  
  it should "be correct for sample 2" in:
    D01.part2(input) shouldEqual "45000"
end T01
