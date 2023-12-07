package aoc.y2023

import scala.annotation.tailrec

import algo.SeqUtil
import aoc.Runner

object D07 extends Runner:
  private val ordering1 = "AKQJT98765432"
  private val ordering2 = "AKQT98765432J"
  private def compareChars(ordering: String, ch1: Char, ch2: Char): Int = ordering.indexOf(ch2) - ordering.indexOf(ch1)

  private def compareHands(ordering: String, h1: String, h2: String): Int =
    @tailrec
    def compare(idx: Int): Int = if idx == h1.length then 0 else
      val cc = compareChars(ordering, h1(idx), h2(idx))
      if cc != 0 then cc else compare(idx + 1)
    compare(0)

  private def recordOrdering(ordering: String): Ordering[(String, Int, Int)] =
    (x: (String, Int, Int), y: (String, Int, Int)) =>
      if x._2 != y._2 then x._2 - y._2 else compareHands(ordering, x._1, y._1)

  private def handType1(hand: String): Int =
    val counts = scala.collection.mutable.HashMap[Char, Int]()
    for ch <- hand do counts.updateWith(ch):
      case Some(v) => Some(v + 1)
      case None => Some(1)
    if counts.size == 1 then 6
    else if counts.size == 2 && counts.values.count(_ == 4) == 1 then 5
    else if counts.size == 2 then 4
    else if counts.values.count(_ == 3) == 1 then 3
    else if counts.values.count(_ == 2) == 2 then 2
    else if counts.values.count(_ == 2) == 1 then 1
    else 0

  private def handType2(hand: String): Int =
    val counts = scala.collection.mutable.HashMap[Char, Int]()
    for ch <- hand.filter(_ != 'J') do counts.updateWith(ch):
      case Some(v) => Some(v + 1)
      case None => Some(1)
    if counts.isEmpty then 6 else
      val bestPair = counts.maxBy(_._2)
      counts.update(bestPair._1, bestPair._2 + hand.count(_ == 'J'))

      if counts.size == 1 then 6
      else if counts.size == 2 && counts.values.count(_ == 4) == 1 then 5
      else if counts.size == 2 then 4
      else if counts.values.count(_ == 3) == 1 then 3
      else if counts.values.count(_ == 2) == 2 then 2
      else if counts.values.count(_ == 2) == 1 then 1
      else 0

  private def classify(handType: String => Int)(line: String): (String, Int, Int) = line match
    case s"$hand $bid" => (hand, handType(hand), bid.toInt)

  override def part1(input: IndexedSeq[String]): String =
    val classified = input.map(classify(handType1)).sorted(using recordOrdering(ordering1))
    classified.zipWithIndex.map(p => (p._2 + 1) * p._1._3).sum.toString

  override def part2(input: IndexedSeq[String]): String =
    val classified = input.map(classify(handType2)).sorted(using recordOrdering(ordering2))
    classified.zipWithIndex.map(p => (p._2 + 1) * p._1._3).sum.toString
end D07
