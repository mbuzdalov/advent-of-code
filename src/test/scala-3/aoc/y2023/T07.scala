package aoc.y2023

import aoc.TestingUtil

class T07 extends TestingUtil:
  private val input =
    """32T3K 765
      |T55J5 684
      |KK677 28
      |KTJJT 220
      |QQQJA 483
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D07.part1(input) shouldEqual "6440"

  it should "be correct for sample 2" in:
    D07.part2(input) shouldEqual "5905"

  it should "solve part 1" in:
    D07.part1(mainInput) shouldEqual "249390788"

  it should "solve part 2" in:
    D07.part2(mainInput) shouldEqual "248750248"
end T07
