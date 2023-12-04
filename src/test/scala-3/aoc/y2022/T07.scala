package aoc.y2022

import aoc.TestingUtil

class T07 extends TestingUtil(D07):
  override def answer1: String = "95437"
  override def input1: String =
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
      |""".stripMargin

  override def answer2: String = "24933642"
  override def input2: String = input1
end T07
