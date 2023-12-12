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
end T11
