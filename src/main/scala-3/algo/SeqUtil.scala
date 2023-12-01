package algo

object SeqUtil:
  /**
   * Splits a sequence using a separator predicate
   * @param seq the sequence to be split
   * @param separator the separator predicate that returns true if an element is a separator
   * @tparam T the type of all the elements
   * @return the sequence of substrings of the original sequence that do not contain separators
   */
  def splitBySeparator[T](seq: Seq[T], separator: T => Boolean): Seq[Seq[T]] =
    val builder = IndexedSeq.newBuilder[Seq[T]]
    val current = IndexedSeq.newBuilder[T]
    for elem <- seq do
      if separator(elem) then
        builder += current.result()
        current.clear()
      else
        current += elem
    val last = current.result()
    if last.nonEmpty then builder += last
    builder.result()

end SeqUtil
