package functional.datastructure

import scala.xml._

/**
 * A Path can either be a Top node or any other nodes other than the current focus node
 */
sealed trait Path

object Top extends Path

/**
 * the hole node of within a path, the so called a one-hole-context
 * @param left the left siblings of the current focus node
 * @param p , the parent Node
 * @param right the right siblings of the current focus node
 */
case class Context(left: NodeSeq, p: Location, right: NodeSeq) extends Path

/**
 * A location in the tree addresses a subtree, together with its path.
 * A location consists of a focus node and its path, representing its surrounding context
 * Node <: NodeSeq <: Seq[Node]
 * @param focus the subtree under current node
 * @param path the parent path, with a hole
 */
case class Location(focus: Node, path: Path) {
  // navigation primitives

  /**
   * going down means, the Context will move down to the child of the ContextNode
   * @return
   */
  def down: Option[Location] = down(1)

  /**
   * go down directly to the nth child, n starts with 1
   * @param n
   * @return
   */
  def down(n: Int): Option[Location] = {
    val children = this.focus.child
    val (l, r) = children splitAt n
    if (n > children.length) None else Some(Location(l.last, Context(l.init, this, r)))
  }

  def left: Option[Location] = path match {
    case Top => None
    case Context(l, p, r) => l.lastOption map {
      last => Location(last, Context(l.init, p, last +: r))
    }
  }

  def right: Option[Location] = path match {
    case Top => None
    case Context(l, p, r) => r.headOption map {
      head => Location(head, Context(l :+ head, p, r.tail))
    }
  }

  def up: Option[Location] = path match {
    case Top => None
    case Context(l, p, r) =>
      val children: Seq[Node] = l :+ focus ++ r flatten
      val pFocus: Node = p.focus match {
        case e: Elem => e.copy(child = children)
        case g: Group => Group(children)
        case s: SpecialNode => s
      }
      Some(Location(pFocus, p.path))
  }

  /**
   * insert at the left side of the focus
   * @param node
   * @return
   */
  def insertLeft(node: Node) = path match {
    case Top => None
    case Context(l, p, r) => Some(Location(focus, Context(l :+ node, p, r)))
  }

  /**
   * insert at the right side of the focus
   * @param node
   * @return
   */
  def insertRight(node: Node) = path match {
    case Top => None
    case Context(l, p, r) => Some(Location(focus, Context(l, p, node +: r)))
  }

  /**
   * if the current node is deleted, the next right sibling will be used,
   * if it's empty then using the closest left sibling, if it's the single node then moves up
   * @return
   */

  def delete = path match {
    case Top => None
    case Context(l, p, r) =>
      if (l.isEmpty && r.isEmpty)
        Some(Location(p.focus match {case e : Elem => e.copy(child = Seq())}, p.path))
      else if (l.isEmpty)
        r.headOption map (Location(_, Context(l, p, r.tail)))
      else l.lastOption map (Location(_, Context(l.init, p, r)) )
  }

  def depthFirst = ???

  def breathFirst = ???

  def update(n:Node) = this.copy(focus = n)

  def toXMLString = ???

  override def toString() = {
    "focus:" + focus + " | path:" + path
  }
}

object Location {
  def apply(node: Node): Location = Location(node, Top)
}

