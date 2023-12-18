package aoc.y2023

import aoc.TestingUtil

class T18 extends TestingUtil:
  private val input =
    """R 6 (#70c710)
      |D 5 (#0dc571)
      |L 2 (#5713f0)
      |D 2 (#d2c081)
      |R 2 (#59c680)
      |D 2 (#411b91)
      |L 5 (#8ceee2)
      |U 2 (#caa173)
      |L 1 (#1b58a2)
      |U 2 (#caa171)
      |R 2 (#7807d2)
      |U 3 (#a77fa3)
      |L 2 (#015232)
      |U 2 (#7a21e3)
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D18.part1(input) shouldEqual "62"

  it should "be correct for sample 2" in:
    D18.part2(input) shouldEqual "952408144115"

  it should "solve part 1" in:
    D18.part1(mainInput) shouldEqual "48795"

  it should "solve part 2" in:
    D18.part2(mainInput) shouldEqual "40654918441248"
end T18
