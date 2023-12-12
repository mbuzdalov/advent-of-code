package aoc.y2023

import aoc.TestingUtil

class T09 extends TestingUtil:
  private val input =
    """0 3 6 9 12 15
      |1 3 6 10 15 21
      |10 13 16 21 30 45
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D09.part1(input) shouldEqual "114"

  it should "be correct for sample 2" in:
    D09.part2(input) shouldEqual "2"
end T09
