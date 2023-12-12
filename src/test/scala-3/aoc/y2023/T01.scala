package aoc.y2023

import aoc.TestingUtil

class T01 extends TestingUtil:
  private val input1 =
    """1abc2
      |pqr3stu8vwx
      |a1b2c3d4e5f
      |treb7uchet
      |""".toLines 

  private val input2 =
    """two1nine
      |eightwothree
      |abcone2threexyz
      |xtwone3four
      |4nineeightseven2
      |zoneight234
      |7pqrstsixteen
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D01.part1(input1) shouldEqual "142"
  
  it should "be correct for sample 2" in:
    D01.part2(input2) shouldEqual "281"
end T01
