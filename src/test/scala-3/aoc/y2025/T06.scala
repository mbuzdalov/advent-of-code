package aoc.y2025

import aoc.TestingUtil

class T06 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D06.part1(testInput) shouldEqual "4277556"

  it should "be correct for sample 2" in:
    D06.part2(testInput) shouldEqual "3263827"

  it should "solve main input 1" in:
    D06.part1(mainInput) shouldEqual "4693159084994"

  it should "solve main input 2" in:
    D06.part2(mainInput) shouldEqual "11643736116335"
    