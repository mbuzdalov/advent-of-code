package aoc.y2023

import aoc.TestingUtil

class T01 extends TestingUtil(D01):
  override def answer1: String = "142"
  override def input1: String =
    """1abc2
      |pqr3stu8vwx
      |a1b2c3d4e5f
      |treb7uchet
      |""".stripMargin

  override def answer2: String = "281"
  override def input2: String =
    """two1nine
      |eightwothree
      |abcone2threexyz
      |xtwone3four
      |4nineeightseven2
      |zoneight234
      |7pqrstsixteen
      |""".stripMargin
end T01