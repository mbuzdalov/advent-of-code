package aoc.y2022

import aoc.TestingUtil

class T08 extends TestingUtil:
  private val input =
    """30373
      |25512
      |65332
      |33549
      |35390
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D08.part1(input) shouldEqual "21"

  it should "be correct for sample 2" in:
    D08.part2(input) shouldEqual "8"

  it should "solve part 1" in:
    D08.part1(mainInput) shouldEqual "1827"
  
  it should "solve part 2" in:
    D08.part2(mainInput) shouldEqual "335580"
end T08
