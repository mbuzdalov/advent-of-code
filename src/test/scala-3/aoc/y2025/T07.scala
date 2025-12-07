package aoc.y2025

import aoc.TestingUtil

class T07 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D07.part1(testInput) shouldEqual "21"

  it should "be correct for sample 2" in:
    D07.part2(testInput) shouldEqual "40"

  it should "solve main input 1" in:
    D07.part1(mainInput) shouldEqual "1573"

  it should "solve main input 2" in:
    D07.part2(mainInput) shouldEqual "15093663987272"
    