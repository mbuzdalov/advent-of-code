package aoc.y2023

import aoc.TestingUtil

class T12 extends TestingUtil:
  private val input =
    """???.### 1,1,3
      |.??..??...?##. 1,1,3
      |?#?#?#?#?#?#?#? 1,3,1,6
      |????.#...#... 4,1,1
      |????.######..#####. 1,6,5
      |?###???????? 3,2,1
      |""".stripMargin.toLines

  "nAssignments" should "process funny line #1" in:
    D12.nArrangements("?????.???", "1,1,3") shouldBe 6L

  "The solution" should "be correct for sample 1" in:
    D12.part1(input) shouldEqual "21"

  it should "be correct for sample 2" in:
    D12.part2(input) shouldEqual "525152"

  it should "solve part 1" in:
    D12.part1(mainInput) shouldEqual "7025"

  it should "solve part 2" in:
    D12.part2(mainInput) shouldEqual "11461095383315"
end T12
