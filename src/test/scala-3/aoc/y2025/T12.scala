package aoc.y2025

import aoc.TestingUtil

class T12 extends TestingUtil:
  "The solution" should "be correct for sample" in:
    D12.part1(testInput) shouldEqual "2"

  it should "solve main input" in:
    D12.part1(mainInput) shouldEqual "531"
