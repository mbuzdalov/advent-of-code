package aoc.y2025

import aoc.TestingUtil

class T03 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D03.part1(testInput) shouldEqual "357"

  it should "be correct for sample 2" in:
    D03.part2(testInput) shouldEqual "3121910778619"
  
  it should "solve main input 1" in:
    D03.part1(mainInput) shouldEqual "17109"
    
  it should "solve main input 2" in:
    D03.part2(mainInput) shouldEqual "169347417057382"
    