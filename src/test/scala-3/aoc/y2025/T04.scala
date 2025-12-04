package aoc.y2025

import aoc.TestingUtil

class T04 extends TestingUtil:
  private val input =
    """..@@.@@@@.
    |@@@.@.@.@@
    |@@@@@.@.@@
    |@.@@@@..@.
    |@@.@@@@.@@
    |.@@@@@@@.@
    |.@.@.@.@@@
    |@.@@@.@@@@
    |.@@@@@@@@.
    |@.@.@@@.@.""".toLines
  
  "The solution" should "be correct for sample 1" in:
    D04.part1(input) shouldEqual "13"

  it should "be correct for sample 2" in:
    D04.part2(input) shouldEqual "43"

  it should "solve main input 1" in:
    D04.part1(mainInput) shouldEqual "1486"

  it should "solve main input 2" in:
    D04.part2(mainInput) shouldEqual "9024"
    