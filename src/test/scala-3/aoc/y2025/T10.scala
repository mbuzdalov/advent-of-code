package aoc.y2025

import aoc.TestingUtil

class T10 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D10.part1(testInput) shouldEqual "7"

  it should "be correct for sample 2" in:
    D10.part2(testInput) shouldEqual "33"

  it should "solve main input 1" in:
    D10.part1(mainInput) shouldEqual "441"

  it should "solve main input 2" in:
    D10.part2(mainInput) shouldEqual "18559"
