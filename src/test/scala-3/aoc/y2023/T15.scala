package aoc.y2023

import aoc.TestingUtil

class T15 extends TestingUtil:
  private val input =
    """rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
      |""".stripMargin.toLines

  "The solution" should "be correct for sample 1" in:
    D15.part1(input) shouldEqual "1320"

  it should "be correct for sample 2" in:
    D15.part2(input) shouldEqual "145"

  it should "solve part 1" in:
    D15.part1(mainInput) shouldEqual "514281"

  it should "solve part 2" in:
    D15.part2(mainInput) shouldEqual "244199"
end T15
