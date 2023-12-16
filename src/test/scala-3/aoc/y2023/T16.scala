package aoc.y2023

import aoc.TestingUtil

class T16 extends TestingUtil:
  private val input =
    """.|...\....
      !|.-.\.....
      !.....|-...
      !........|.
      !..........
      !.........\
      !..../.\\..
      !.-.-/..|..
      !.|....-|.\
      !..//.|....
      !""".stripMargin('!').split('\n').toIndexedSeq

  "The solution" should "be correct for sample 1" in:
    D16.part1(input) shouldEqual "46"

  it should "be correct for sample 2" in:
    D16.part2(input) shouldEqual "51"

  it should "solve part 1" in:
    D16.part1(mainInput) shouldEqual "6906"

  it should "solve part 2" in:
    D16.part2(mainInput) shouldEqual "7330"
end T16
