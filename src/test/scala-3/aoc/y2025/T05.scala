package aoc.y2025

import aoc.TestingUtil

class T05 extends TestingUtil:
  private val input =
    """3-5
    |10-14
    |16-20
    |12-18
    |
    |1
    |5
    |8
    |11
    |17
    |32""".toLines
  
  "The solution" should "be correct for sample 1" in:
    D05.part1(input) shouldEqual "3"

  it should "be correct for sample 2" in:
    D05.part2(input) shouldEqual "14"

  it should "solve main input 1" in:
    D05.part1(mainInput) shouldEqual "635"

  it should "solve main input 2" in:
    D05.part2(mainInput) shouldEqual "369761800782619"
    