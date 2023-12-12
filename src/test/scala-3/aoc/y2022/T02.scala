package aoc.y2022

import aoc.TestingUtil

class T02 extends TestingUtil:
  private val input =
    """A Y
      |B X
      |C Z
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D02.part1(input) shouldEqual "15"

  it should "be correct for sample 2" in:
    D02.part2(input) shouldEqual "12"

  it should "solve part 1" in:
    D02.part1(mainInput) shouldEqual "17189"
  
  it should "solve part 2" in:
    D02.part2(mainInput) shouldEqual "13490"
end T02
