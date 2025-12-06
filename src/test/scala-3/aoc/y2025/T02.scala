package aoc.y2025

import aoc.TestingUtil

class T02 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D02.part1(testInput) shouldEqual "1227775554"

  it should "be correct for sample 2" in:
    D02.part2(testInput) shouldEqual "4174379265"
  
  it should "solve main input 1" in:
    D02.part1(mainInput) shouldEqual "24043483400"
  
  it should "solve main input 2" in:
    D02.part2(mainInput) shouldEqual "38262920235"
    