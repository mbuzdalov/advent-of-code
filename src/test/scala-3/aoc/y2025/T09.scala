package aoc.y2025

import aoc.TestingUtil

class T09 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D09.part1(testInput) shouldEqual "50"

  it should "be correct for sample 2" in:
    D09.part2(testInput) shouldEqual "24"

  it should "solve main input 1" in:
    D09.part1(mainInput) shouldEqual "4761736832"

  it should "solve main input 2" in:
    D09.part2(mainInput) shouldEqual "1452422268"
