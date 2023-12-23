package aoc.y2023

import aoc.TestingUtil

class T23 extends TestingUtil:
  private val input =
    """#.#####################
      |#.......#########...###
      |#######.#########.#.###
      |###.....#.>.>.###.#.###
      |###v#####.#v#.###.#.###
      |###.>...#.#.#.....#...#
      |###v###.#.#.#########.#
      |###...#.#.#.......#...#
      |#####.#.#.#######.#.###
      |#.....#.#.#.......#...#
      |#.#####.#.#.#########v#
      |#.#...#...#...###...>.#
      |#.#.#v#######v###.###v#
      |#...#.>.#...>.>.#.###.#
      |#####v#.#.###v#.#.###.#
      |#.....#...#...#.#.#...#
      |#.#########.###.#.#.###
      |#...###...#...#...#.###
      |###.###.#.###v#####v###
      |#...#...#.#.>.>.#.>.###
      |#.###.###.#.###.#.#v###
      |#.....###...###...#...#
      |#####################.#
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D23.part1(input) shouldEqual "94"

  it should "be correct for sample 2" in:
    D23.part2(input) shouldEqual "154"

  it should "solve part 1" in:
    D23.part1(mainInput) shouldEqual "2370"

  it should "solve part 2" in:
    D23.part2(mainInput) shouldEqual "6546"
end T23
