package aoc.y2023

import aoc.TestingUtil

class T03 extends TestingUtil:
  private val input =
    """467..114..
      |...*......
      |..35..633.
      |......#...
      |617*......
      |.....+.58.
      |..592.....
      |......755.
      |...$.*....
      |.664.598..
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D03.part1(input) shouldEqual "4361"

  it should "be correct for sample 2" in:
    D03.part2(input) shouldEqual "467835"

  it should "solve part 1" in:
    D03.part1(mainInput) shouldEqual "556057"

  it should "solve part 2" in:
    D03.part2(mainInput) shouldEqual "82824352"
end T03
