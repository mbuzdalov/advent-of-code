package aoc.y2022

import aoc.TestingUtil

class T04 extends TestingUtil:
  private val input =
    """2-4,6-8
      |2-3,4-5
      |5-7,7-9
      |2-8,3-7
      |6-6,4-6
      |2-6,4-8
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D04.part1(input) shouldEqual "2"

  it should "be correct for sample 2" in:
    D04.part2(input) shouldEqual "4"

  it should "solve part 1" in:
    D04.part1(mainInput) shouldEqual "584"
    
  it should "solve part 2" in:
    D04.part2(mainInput) shouldEqual "933"
end T04
