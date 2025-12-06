package aoc.y2025

import aoc.TestingUtil

class T05 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D05.part1(testInput) shouldEqual "3"

  it should "be correct for sample 2" in:
    D05.part2(testInput) shouldEqual "14"

  it should "solve main input 1" in:
    D05.part1(mainInput) shouldEqual "635"

  it should "solve main input 2" in:
    D05.part2(mainInput) shouldEqual "369761800782619"
    