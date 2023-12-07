package aoc.y2023

import aoc.TestingUtil

class T07 extends TestingUtil(D07):
  override def answer1: String = "6440"
  override def input1: String =
    """32T3K 765
      |T55J5 684
      |KK677 28
      |KTJJT 220
      |QQQJA 483
      |""".stripMargin

  override def answer2: String = "5905"
  override def input2: String = input1
end T07
