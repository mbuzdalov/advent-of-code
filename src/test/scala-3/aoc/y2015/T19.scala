package aoc.y2015

import aoc.TestingUtil

class T19 extends TestingUtil:
  private val ePrefix =
    """e => H
      |e => O
      |""".toLines

  private val others =
    """H => HO
      |H => OH
      |O => HH
      |""".toLines

  "The solution" should "be correct for sample 1/1" in:
    D19.part1(others :+ "" :+ "HOH") shouldEqual "4"

  it should "be correct for sample 1/2" in :
    D19.part1(others :+ "" :+ "HOHOHO") shouldEqual "7"

  it should "be correct for sample 2/1" in:
    D19.part2(ePrefix ++ others :+ "" :+ "HOH") shouldEqual "3"

  it should "be correct for sample 2/2" in :
    D19.part2(ePrefix ++ others :+ "" :+ "HOHOHO") shouldEqual "6"

  it should "solve part 1" in:
    D19.part1(mainInput) shouldEqual "518"

  it should "solve part 2" in:
    D19.part2(mainInput) shouldEqual "200"
end T19
