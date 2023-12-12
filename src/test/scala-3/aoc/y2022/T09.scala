package aoc.y2022

import aoc.TestingUtil

class T09 extends TestingUtil:
  private val input1 =
    """R 4
      |U 4
      |L 3
      |D 1
      |R 4
      |D 1
      |L 5
      |R 2
      |""".toLines

  private val input2 =
    """R 5
      |U 8
      |L 8
      |D 3
      |R 17
      |D 10
      |L 25
      |U 20
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D09.part1(input1) shouldEqual "13"

  it should "be correct for sample 2" in:
    D09.part2(input2) shouldEqual "36"

  it should "solve part 1" in:
    D09.part1(mainInput) shouldEqual "6190"
    
  it should "solve part 2" in:
    D09.part2(mainInput) shouldEqual "2516"
end T09
