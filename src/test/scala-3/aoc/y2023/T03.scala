package aoc.y2023

import aoc.TestingUtil

class T03 extends TestingUtil(D03):
  override def answer1: String = "4361"
  override def input1: String =
    """467..114..
      |...*......
      |..35..633.
      |......#...
      |617*......
      |.....+.58.
      |..592.....
      |......755.
      |...$.*....
      |.664.598..
      |""".stripMargin

  override def answer2: String = "467835"
  override def input2: String = input1
end T03
