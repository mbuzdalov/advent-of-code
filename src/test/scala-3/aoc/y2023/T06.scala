package aoc.y2023

import aoc.TestingUtil

class T06 extends TestingUtil:
  private val input =
    """Time:      7  15   30
      |Distance:  9  40  200
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D06.part1(input) shouldEqual "288"

  it should "be correct for sample 2" in:
    D06.part2(input) shouldEqual "71503"

  it should "solve part 1" in:
    D06.part1(mainInput) shouldEqual "74698"

  it should "solve part 2" in:
    D06.part2(mainInput) shouldEqual "27563421"
end T06
