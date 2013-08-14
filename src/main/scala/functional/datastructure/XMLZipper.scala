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
 * Node <: NodeSeq <: NodeSeq
 * @param focus
 * @param path
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
   * this delete method will delete the current focus node and moves up
   * this will be efficient if you just want to delete a single none
   * @return
   */
  def deleteSingle = path match {
    case Top => None
    case Context(l, p, r) =>
      val children = l.reverse ++ r
      Some(Location(focus match {
        case e: Elem => e.copy(child = children)
      }, p.path))
  }

  /**
   * if the current node is deleted, the next right sibling will be used, if it's empty then using the closest left sibling, if it's the single node then calls deleteSingle
   * @return
   */

  def delete = path match {
    case Top => None
    case Context(l, p, r) =>
      if (l.isEmpty && r.isEmpty) Some(Location(focus match {
        case e: Elem => e.copy(child = l ++ r)
      }, p.path))
      else
        r.headOption map {
          head => Location(head, Context(l, p, r.tail))
        } orElse (
          l.lastOption map {
            last => Location(last, Context(l.init, p, r))
          })
  }

  /*
  def update(n:Node) = {print("the current Location for updating " + this);this.copy(focus = n)}

  */
  /**
   * get the nearest upright node
   * @return
   */
  /*
   def goUpRight:Either[Throwable,Location] = path match {
     case Top => Left(new Exception("already the top most"))
     case _ => goUp.fold(
       fail => Right(this),// go up only failed at Top node
       succ => succ.goRight.fold(
         fail=>  succ.goUpRight, // went up, but can't go right
         succ => Right(succ) // went up and at the right of previous up
       )
     )
   }
   */
  /**
   * move will do a depth-first traversal starting from the current focus and straight to the right most sibling.
   * the result of the move will be the right most sibling location
   * that means if you move from the Top, then all the nodes are traversed
   * @return
   */
  /*

   def move(f: Node => Node):Either[Throwable,Location] = map(f).goDown.fold( // depth first
     fail => goRight.fold( // if can't go further then go right
       fail => goUpRight.fold(
         fail => Right(this),//goUpRight can only fail when it's Top. the base case
         succ => succ.move(f)), // if can't go right, go upright, get the nearest upright
       succ => succ.move(f) // if can go right, then recursive the operation to the next node
       ) ,
     succ => succ.move(f)
    )


   def move(f: Node => Node):Either[Throwable,Location] = {
     val tempLoc = map(f).goDown
     println(tempLoc.toString)
     tempLoc.fold( // depth first
     fail => goRight.fold( // if can't go further then go right
       fail => goUpRight.fold(
         fail => Right(this),//goUpRight can only fail when it's Top. the base case
         succ => succ.move(f)), // if can't go right, go upright, get the nearest upright
       succ => succ.move(f) // if can go right, then recursive the operation to the next node
     ) ,
     succ => succ.move(f)
   )
   }
   def map(f : Node => Node) = update(f(focus))

   def updateWith(f: Node => Node) = this.copy(focus =  f(focus))*/

  override def toString() = {
    "focus:" + focus + " | path:" + path
  }
}

object Location {
  def apply(node: Node): Location = Location(node, Top)
}

