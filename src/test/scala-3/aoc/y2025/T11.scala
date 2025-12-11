package aoc.y2025

import aoc.TestingUtil

class T11 extends TestingUtil:
  "The solution" should "be correct for sample 1" in:
    D11.part1(testInput) shouldEqual "5"

  private val testInput2 =
    s"""svr: aaa bbb
       |aaa: fft
       |fft: ccc
       |bbb: tty
       |tty: ccc
       |ccc: ddd eee
       |ddd: hub
       |hub: fff
       |eee: dac
       |dac: fff
       |fff: ggg hhh
       |ggg: out
       |hhh: out
       |""".toLines
  
  it should "be correct for sample 2" in:
    D11.part2(testInput2) shouldEqual "2"

  it should "solve main input 1" in:
    D11.part1(mainInput) shouldEqual "523"

  it should "solve main input 2" in:
    D11.part2(mainInput) shouldEqual "517315308154944"
