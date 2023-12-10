package aoc.y2023

import aoc.TestingUtil

class T10 extends TestingUtil(D10):
  override def answer1: String = "8"
  override def answer2: String = "8"
  override def input1: String =
    """..F7.
      |.FJ|.
      |SJ.L7
      ||F--J
      |LJ...
      |""".stripMargin

  override def input2: String =
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
      |""".stripMargin
end T10
