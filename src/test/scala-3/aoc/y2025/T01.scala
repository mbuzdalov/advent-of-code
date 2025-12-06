package aoc.y2025

import aoc.TestingUtil

class T01 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D01.part1(testInput) shouldEqual "3"

  it should "be correct for sample 2" in:
    D01.part2(testInput) shouldEqual "6"
  
  it should "solve main input 1" in:
    D01.part1(mainInput) shouldEqual "1191"
  
  it should "solve main input 2" in:
    D01.part2(mainInput) shouldEqual "6858"
    