package aoc.y2023

import aoc.TestingUtil

class T17 extends TestingUtil:
  private val input =
    """2413432311323
      |3215453535623
      |3255245654254
      |3446585845452
      |4546657867536
      |1438598798454
      |4457876987766
      |3637877979653
      |4654967986887
      |4564679986453
      |1224686865563
      |2546548887735
      |4322674655533
      |""".toLines

  private val input2 =
    """111111111111
      |999999999991
      |999999999991
      |999999999991
      |999999999991
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D17.part1(input) shouldEqual "102"

  it should "be correct for sample 2/1" in:
    D17.part2(input) shouldEqual "94"

  it should "be correct for sample 2/2" in:
    D17.part2(input2) shouldEqual "71"

  //  it should "solve part 1" in:
//    D17.part1(mainInput) shouldEqual "1260"
//
//  it should "solve part 2" in:
//    D17.part2(mainInput) shouldEqual ""
end T17
