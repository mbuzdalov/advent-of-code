package aoc.y2022

import algo.SeqUtil
import aoc.Runner

object D16 extends Runner:
  private case class Record(name: String, flow: Int, others: IndexedSeq[String])

  private def constructAnswer(input: IndexedSeq[String], time: Int): Array[Int] =
    val records = input.map:
      case s"Valve $name has flow rate=$flow; tunnel leads to valve $etc" =>
        Record(name, flow.toInt, IndexedSeq(etc))
      case s"Valve $name has flow rate=$flow; tunnels lead to valves $etc" =>
        Record(name, flow.toInt, SeqUtil.tokenMap(etc, ", ", s => s))
    val ii = records.indices
    val rename = records.map(_.name).zipWithIndex.toMap
    val distances = Array.fill(records.size, records.size)(1000000000)
    for r <- records; o <- r.others do
      distances(rename(r.name))(rename(o)) = 1
    for k <- ii; i <- ii; j <- ii do
      distances(i)(j) = math.min(distances(i)(j), distances(i)(k) + distances(k)(j))
    val goodIndices = records.indices.filter(i => records(i).flow > 0)
    val n = goodIndices.size
    val nn = 0 until n
    val nn2 = 0 until (1 << n)
    val distShort = Array.tabulate(n, n)((i, j) => distances(goodIndices(i))(goodIndices(j)))
    val dp = Array.fill(time + 1, n, 1 << n)(0)
    val flows = Array.tabulate(n)(i => records(goodIndices(i)).flow)

    val result = Array.fill(1 << n)(0)

    // init
    for i <- nn do
      val timeActive = distances(rename("AA"))(goodIndices(i)) + 1
      if timeActive <= time then
        val newMask = 1 << i
        val newValue = (time - timeActive) * flows(i)
        dp(timeActive)(i)(newMask) = newValue
        result(newMask) = newValue

    // main DP
    for
      t <- 0 until time
      last <- nn
      mask <- nn2 if (mask & (1 << last)) != 0
      lastV = dp(t)(last)(mask) if lastV != 0
      next <- nn if (mask & (1 << next)) == 0
    do
      val timeActive = t + distShort(last)(next) + 1
      if timeActive <= time then
        val newValue = lastV + (time - timeActive) * flows(next)
        val newMask = mask ^ (1 << next)
        if dp(timeActive)(next)(newMask) < newValue then
          dp(timeActive)(next)(newMask) = newValue
          if result(newMask) < newValue then
            result(newMask) = newValue

    result

  override def part1(input: IndexedSeq[String]): String =
    val masks = constructAnswer(input, 30)
    masks.max.toString

  override def part2(input: IndexedSeq[String]): String =
    val masks = constructAnswer(input, 26)
    var result = 0
    for mask1 <- masks.indices do
      val max2 = masks.length - 1 - mask1
      var mask2 = max2
      var best2 = masks(mask2)
      while mask2 > 0 do
        mask2 = (mask2 - 1) & max2
        best2 = math.max(best2, masks(mask2))
      result = math.max(result, masks(mask1) + best2)
    result.toString
end D16
