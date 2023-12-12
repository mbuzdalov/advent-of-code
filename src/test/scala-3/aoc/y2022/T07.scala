package aoc.y2022

import aoc.TestingUtil

class T07 extends TestingUtil:
  private val input =
    """$ cd /
      |$ ls
      |dir a
      |14848514 b.txt
      |8504156 c.dat
      |dir d
      |$ cd a
      |$ ls
      |dir e
      |29116 f
      |2557 g
      |62596 h.lst
      |$ cd e
      |$ ls
      |584 i
      |$ cd ..
      |$ cd ..
      |$ cd d
      |$ ls
      |4060174 j
      |8033020 d.log
      |5626152 d.ext
      |7214296 k
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D07.part1(input) shouldEqual "95437"
    
  it should "be correct for sample 2" in:
    D07.part2(input) shouldEqual "24933642"
end T07
