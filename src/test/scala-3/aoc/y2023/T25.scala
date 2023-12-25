package aoc.y2023

import aoc.TestingUtil

class T25 extends TestingUtil:
  private val input =
    """jqt: rhn xhk nvd
      |rsh: frs pzl lsr
      |xhk: hfx
      |cmg: qnr nvd lhk bvb
      |rhn: xhk bvb hfx
      |bvb: xhk hfx
      |pzl: lsr hfx nvd
      |qnr: nvd
      |ntq: jqt hfx bvb xhk
      |nvd: lhk
      |lsr: lhk
      |rzs: qnr cmg lsr rsh
      |frs: qnr lhk lsr
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D25.part1(input) shouldEqual "54"

  it should "solve part 1" in:
    D25.part1(mainInput) shouldEqual "600225"
end T25
