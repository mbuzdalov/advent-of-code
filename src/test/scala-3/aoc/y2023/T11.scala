package aoc.y2023

import aoc.TestingUtil

class T11 extends TestingUtil:
  private val input =
    """...#......
      |.......#..
      |#.........
      |..........
      |......#...
      |.#........
      |.........#
      |..........
      |.......#..
      |#...#.....
      |""".toLines

  "The solution" should "work with expansion rate 2" in:
    D11.solve(input, 2) shouldEqual "374"

  it should "work with expansion rate 10" in:
    D11.solve(input, 10) shouldEqual "1030"

  it should "work with expansion rate 100" in:
    D11.solve(input, 100) shouldEqual "8410"

  it should "solve part 1" in:
    D11.part1(mainInput) shouldEqual "9647174"

  it should "solve part 2" in:
    D11.part2(mainInput) shouldEqual "377318892554"
end T11
