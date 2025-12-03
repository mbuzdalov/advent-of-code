package aoc.y2025

import aoc.TestingUtil

class T01 extends TestingUtil:
  private val input =
    """L68
      |L30
      |R48
      |L5
      |R60
      |L55
      |L1
      |L99
      |R14
      |L82
      |""".toLines
  
  "The solution" should "be correct for sample 1" in:
    D01.part1(input) shouldEqual "3"

  it should "be correct for sample 2" in:
    D01.part2(input) shouldEqual "6"
  
  it should "solve main input 1" in:
    D01.part1(mainInput) shouldEqual "1191"
  
  it should "solve main input 2" in:
    D01.part2(mainInput) shouldEqual "6858"
    