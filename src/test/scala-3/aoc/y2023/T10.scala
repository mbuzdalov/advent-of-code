package aoc.y2023

import aoc.TestingUtil

class T10 extends TestingUtil:
  private val input1 =
    """-L|F7
      |7S-7|
      |L|7||
      |-L-J|
      |L|-JF
      |""".toLines

  private val input2 =
    """7-F7-
      |.FJ|7
      |SJLL7
      ||F--J
      |LJ.LJ
      |""".toLines

  private val input3 =
    """...........
      |.S-------7.
      |.|F-----7|.
      |.||.....||.
      |.||.....||.
      |.|L-7.F-J|.
      |.|..|.|..|.
      |.L--J.L--J.
      |...........
      |""".toLines

  private val input4 =
    """.F----7F7F7F7F-7....
      |.|F--7||||||||FJ....
      |.||.FJ||||||||L7....
      |FJL7L7LJLJ||LJ.L-7..
      |L--J.L7...LJS7F-7L7.
      |....F-J..F7FJ|L7L7L7
      |....L7.F7||L7|.L7L7|
      |.....|FJLJ|FJ|F7|.LJ
      |....FJL-7.||.||||...
      |....L---J.LJ.LJLJ...
      |""".toLines

  private val input5 =
    """FF7FSF7F7F7F7F7F---7
      |L|LJ||||||||||||F--J
      |FL-7LJLJ||||||LJL-77
      |F--JF--7||LJLJ7F7FJ-
      |L---JF-JLJ.||-FJLJJ7
      ||F|F-JF---7F7-L7L|7|
      ||FFJF7L7F-JF7|JL---7
      |7-L-JL7||F7|L7F-7F7|
      |L.L7LFJ|||||FJL7||LJ
      |L7JLJL-JLJLJL--JLJ.L
      |""".toLines

  "The solution" should "be correct for sample 1/1" in:
    D10.part1(input1) shouldEqual "4"

  it should "be correct for sample 1/2" in:
    D10.part1(input2) shouldEqual "8"

  it should "be correct for sample 2/1" in:
    D10.part2(input3) shouldEqual "4"

  it should "be correct for sample 2/2" in:
    D10.part2(input4) shouldEqual "8"

  it should "be correct for sample 2/3" in:
    D10.part2(input5) shouldEqual "10"

  it should "solve part 1" in:
    D10.part1(mainInput) shouldEqual "7102"

  it should "solve part 2" in:
    D10.part2(mainInput) shouldEqual "363"
end T10
