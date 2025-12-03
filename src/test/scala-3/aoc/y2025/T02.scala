package aoc.y2025

import aoc.TestingUtil

class T02 extends TestingUtil:
  private val input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124".toLines
  
  "The solution" should "be correct for sample 1" in:
    D02.part1(input) shouldEqual "1227775554"

  it should "be correct for sample 2" in:
    D02.part2(input) shouldEqual "4174379265"
  
  it should "solve main input 1" in:
    D02.part1(mainInput) shouldEqual "24043483400"
  
  it should "solve main input 2" in:
    D02.part2(mainInput) shouldEqual "38262920235"
    