package aoc.y2022

import aoc.TestingUtil

class T05 extends TestingUtil:
  private val input =
    """    [D]
      |[N] [C]
      |[Z] [M] [P]
      | 1   2   3
      |
      |move 1 from 2 to 1
      |move 3 from 1 to 3
      |move 2 from 2 to 1
      |move 1 from 1 to 2
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D05.part1(input) shouldEqual "CMZ"

  it should "be correct for sample 2" in:
    D05.part2(input) shouldEqual "MCD"

  it should "solve part 1" in:
    D05.part1(mainInput) shouldEqual "QGTHFZBHV"
    
  it should "solve part 2" in:
    D05.part2(mainInput) shouldEqual "MGDMPSZTM"
end T05
