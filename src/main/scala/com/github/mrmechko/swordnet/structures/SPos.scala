package com.github.mrmechko.swordnet.structures

/**
 * Created by mechko on 6/19/15.
 */
sealed trait SPos {
  def asChar : Char
  def asString : String
  def asInt : Int
}

object SPos {
  val c2s : Map[Char, String] = Map('n'->"noun", 'v'->"verb", 'a'->"adjective", 'r'->"adverb", 's'->"satellite").withDefaultValue("other")
  val c2i : Map[Char, Int] = Map('n'->1, 'v'->2, 'a'->3, 'r'->4, 's'->5).withDefaultValue(6)
  val s2c : Map[String, Char] = c2s.map(_.swap).withDefaultValue('_')
  val i2c : Map[Int, Char] = c2i.map(_.swap).withDefaultValue('_')

  private case class SPosImpl(asChar : Char) extends SPos {
    override def toString: String = "SPos(%s)".format(c2s(asChar))
    override def asString: String = c2s(asChar)
    override def asInt: Int = c2i(asChar)
  }

  def apply(int : Int) : SPos = SPosImpl(i2c(int))
  def apply(string : String) : SPos = SPosImpl(s2c.getOrElse(string, string.head.toLower))
  def apply(char : Char) : SPos = SPosImpl(char.toLower)

}