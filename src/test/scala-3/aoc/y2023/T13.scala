package aoc.y2023

import aoc.TestingUtil

class T13 extends TestingUtil:
  private val input =
    """#.##..##.
      |..#.##.#.
      |##......#
      |##......#
      |..#.##.#.
      |..##..##.
      |#.#.##.#.
      |
      |#...##..#
      |#....#..#
      |..##..###
      |#####.##.
      |#####.##.
      |..##..###
      |#....#..#
      |""".stripMargin.toLines

  "The solution" should "be correct for sample 1" in:
    D13.part1(input) shouldEqual "405"

  it should "be correct for sample 2" in:
    D13.part2(input) shouldEqual "400"

  it should "solve part 1" in:
    D13.part1(mainInput) shouldEqual "30802"

  it should "solve part 2" in:
    D13.part2(mainInput) shouldEqual "37876"
end T13
