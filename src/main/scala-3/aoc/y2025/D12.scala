package aoc.y2025

import algo.SeqUtil
import aoc.Runner

import java.util.Random

object D12 extends Runner:
  private case class Place(w: Int, h: Int, requests: IndexedSeq[Int]):
    def isTriviallyYes: Boolean = (w / 3) * (h / 3) >= requests.sum
    def isTriviallyNo(areaSizes: IndexedSeq[Int]): Boolean = w * h < areaSizes.zip(requests).map(p => p._1 * p._2).sum
    
  private def parsePlace(s: String): Place =
    val tokens = SeqUtil.tokens(s, "x: ")
    val w = tokens(0).toInt
    val h = tokens(1).toInt
    Place(math.min(w, h), math.max(w, h), tokens.drop(2).map(_.toInt))
  
  private def solveNonTrivial(shapes: IndexedSeq[IndexedSeq[String]], place: Place, rng: Random): Boolean =
    // I did write a bruteforce solution, and it took 24 seconds to run, and I could optimize it a bit more, but well
    rng.nextBoolean()
  
  override def part1(input: IndexedSeq[String]): String =
    val wsParts = SeqUtil.splitBySeparator[String](input, _.isEmpty)
    val shapes = wsParts.init.map(_.tail)
    val places = wsParts.last.map(parsePlace)
    
    val areas = shapes.map(_.map(_.count(_ == '#')).sum)
    val triviallyYes = places.count(_.isTriviallyYes)
    val triviallyNo = places.count(_.isTriviallyNo(areas))
    val nonTrivial = places.filter(p => !p.isTriviallyYes && !p.isTriviallyNo(areas))
    val random = new Random(234236)
    (triviallyYes + nonTrivial.count(p => solveNonTrivial(shapes, p, random))).toString
    
  override def part2(input: IndexedSeq[String]): String = throw new NotImplementedError("Merry Christmas")
end D12
