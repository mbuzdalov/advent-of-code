package aoc.y2023

import aoc.TestingUtil

class T14 extends TestingUtil:
  private val input =
    """O....#....
      |O.OO#....#
      |.....##...
      |OO.#O....O
      |.O.....O#.
      |O.#..O.#.#
      |..O..#O..O
      |.......O..
      |#....###..
      |#OO..#....
      |""".stripMargin.toLines

  "The solution" should "be correct for sample 1" in:
    D14.part1(input) shouldEqual "136"

  it should "be correct for sample 2" in:
    D14.part2(input) shouldEqual "64"

  it should "solve part 1" in:
    D14.part1(mainInput) shouldEqual "113486"

  it should "solve part 2" in:
    D14.part2(mainInput) shouldEqual "104409"
end T14
