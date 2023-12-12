package aoc.y2023

import aoc.TestingUtil

class T08 extends TestingUtil:
  private val input1 =
    """RL
      |
      |AAA = (BBB, CCC)
      |BBB = (DDD, EEE)
      |CCC = (ZZZ, GGG)
      |DDD = (DDD, DDD)
      |EEE = (EEE, EEE)
      |GGG = (GGG, GGG)
      |ZZZ = (ZZZ, ZZZ)
      |""".toLines

  private val input2 =
    """LLR
      |
      |AAA = (BBB, BBB)
      |BBB = (AAA, ZZZ)
      |ZZZ = (ZZZ, ZZZ)
      |""".toLines

  private val input3 =
    """LR
      |
      |11A = (11B, XXX)
      |11B = (XXX, 11Z)
      |11Z = (11B, XXX)
      |22A = (22B, XXX)
      |22B = (22C, 22C)
      |22C = (22Z, 22Z)
      |22Z = (22B, 22B)
      |XXX = (XXX, XXX)
      |""".toLines

  "The solution" should "be correct for sample 1/1" in:
    D08.part1(input1) shouldEqual "2"

  it should "be correct for sample 1/2" in:
    D08.part1(input2) shouldEqual "6"

  it should "be correct for sample 2" in:
    D08.part2(input3) shouldEqual "6"
end T08
