package algo

import java.util.StringTokenizer

object SeqUtil:
  /**
   * Splits a sequence using a separator predicate
   * @param seq the sequence to be split
   * @param separator the separator predicate that returns true if an element is a separator
   * @tparam T the type of all the elements
   * @return the sequence of substrings of the original sequence that do not contain separators
   */
  def splitBySeparator[T](seq: Seq[T], separator: T => Boolean): IndexedSeq[IndexedSeq[T]] =
    val builder = IndexedSeq.newBuilder[IndexedSeq[T]]
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

  /**
   * Splits the given string `str` into tokens using separator chars given as `sep`,
   * then applies a conversion `map` to each of them and returns the result in a sequence.
   * 
   * Internally this uses `StringTokenizer` to tokenize the string, with its usual conventions applied. 
   * 
   * @param str the string to tokenize.
   * @param sep the separator chars.
   * @param map the mapping function,
   * @tparam T the type of the mapping function result.
   * @return the sequence of mapped tokens.
   */
  def tokenMap[T](str: String, sep: String, map: String => T): IndexedSeq[T] =
    val tokenizer = StringTokenizer(str, sep)
    IndexedSeq.fill(tokenizer.countTokens())(map(tokenizer.nextToken()))

  /**
   * Splits the given string `str` into tokens using separator chars given as `sep`,
   * and returns the result in a sequence.
   *
   * Internally this uses `StringTokenizer` to tokenize the string, with its usual conventions applied. 
   *
   * @param str the string to tokenize.
   * @param sep the separator chars.
   * @return the sequence of tokens.
   */
  def tokens(str: String, sep: String): IndexedSeq[String] =
    val tokenizer = StringTokenizer(str, sep)
    IndexedSeq.fill(tokenizer.countTokens())(tokenizer.nextToken())
end SeqUtil
