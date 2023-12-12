package aoc.y2022

import aoc.TestingUtil

class T14 extends TestingUtil:
  private val input =
    """498,4 -> 498,6 -> 496,6
      |503,4 -> 502,4 -> 502,9 -> 494,9
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D14.part1(input) shouldEqual "24"

  it should "be correct for sample 2" in:
    D14.part2(input) shouldEqual "93"

  it should "solve part 1" in:
    D14.part1(mainInput) shouldEqual "578"
  
  it should "solve part 2" in:
    D14.part2(mainInput) shouldEqual "24377"
end T14
