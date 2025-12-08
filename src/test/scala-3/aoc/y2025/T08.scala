package aoc.y2025

import aoc.TestingUtil

class T08 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D08.part1(testInput) shouldEqual "40"

  it should "be correct for sample 2" in:
    D08.part2(testInput) shouldEqual "25272"

  it should "solve main input 1" in:
    D08.part1(mainInput) shouldEqual "181584"

  it should "solve main input 2" in:
    D08.part2(mainInput) shouldEqual "8465902405"
    