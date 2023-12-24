package aoc.y2023

import aoc.TestingUtil

class T24 extends TestingUtil:
  private val input =
    """19, 13, 30 @ -2, 1, -2
      |18, 19, 22 @ -1, -1, -2
      |20, 25, 34 @ -2, -2, -4
      |12, 31, 28 @ -1, -2, -1
      |20, 19, 15 @ 1, -5, -3
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D24.part1(input, 7, 27) shouldEqual 2L

  it should "be correct for sample 2" in:
    D24.part2(input) shouldEqual "47"

  it should "solve part 1" in:
    D24.part1(mainInput) shouldEqual "31921"

  it should "solve part 2" in:
    D24.part2(mainInput) shouldEqual "761691907059631"
end T24
