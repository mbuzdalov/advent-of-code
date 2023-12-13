package aoc.y2023

import algo.SeqUtil
import aoc.Runner

object D13 extends Runner:
  private def isMirrored[T](left: Seq[T], right: Seq[T]): Boolean =
    val length = math.min(left.size, right.size)
    left.takeRight(length) == right.take(length).reverse

  private def mirroring(input: IndexedSeq[String]): (Set[Int], Set[Int]) =
    val horizontal = (1 until input.size).filter(i => isMirrored(input.take(i), input.drop(i)))
    val vertical = (1 until input(0).length).filter(i => input.forall(line => isMirrored(line.take(i), line.drop(i))))
    (vertical.toSet, horizontal.toSet)

  private def alternativeMirroring(input: IndexedSeq[String]): (Set[Int], Set[Int]) =
    val old = mirroring(input)
    val smudges = for
      r <- input.indices
      c <- input(0).indices
      i0 = input.updated(r, input(r).updated(c, if input(r)(c) == '#' then '.' else '#'))
      m = mirroring(i0)
      m2 = (m._1.removedAll(old._1), m._2.removedAll(old._2))
      if m2._1.nonEmpty || m2._2.nonEmpty
    yield m2
    smudges.head

  private def solve(mirroring: IndexedSeq[String] => (Set[Int], Set[Int]))(input: IndexedSeq[String]): Int =
    val solution = mirroring(input)
    if solution._1.nonEmpty then solution._1.head else solution._2.head * 100

  override def part1(input: IndexedSeq[String]): String =
    SeqUtil.splitBySeparator(input, _.isEmpty).map(solve(mirroring)).sum.toString
  override def part2(input: IndexedSeq[String]): String =
    SeqUtil.splitBySeparator(input, _.isEmpty).map(solve(alternativeMirroring)).sum.toString
end D13
