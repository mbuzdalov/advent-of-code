package aoc.y2025

import aoc.TestingUtil

class T03 extends TestingUtil:
  private val input =
    """987654321111111
      |811111111111119
      |234234234234278
      |818181911112111""".toLines
  
  "The solution" should "be correct for sample 1" in:
    D03.part1(input) shouldEqual "357"

  it should "be correct for sample 2" in:
    D03.part2(input) shouldEqual "3121910778619"
    