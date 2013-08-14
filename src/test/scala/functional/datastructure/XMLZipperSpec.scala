package functional.datastructure


import org.scalatest.FunSpec
import xml._
import org.scalatest.matchers._

/**
 * Created with IntelliJ IDEA.
 * User: Xiao-Ye.Wang
 * Date: 8/28/12
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
class XMLZipperSpec extends FunSpec {

  val catalog = scala.xml.Utility.trim(booksxml)

  describe("test down") {
    val loc1 = Location(catalog)
    it("should move down to it's first children") {
      assert(loc1.down.get.down.get.focus == <author>Gambardella, Matthew</author>)
      assert(loc1.down.get.down(2).get.focus == <title>XML Developer's Guide</title>)
      assert(loc1.down(12).get.down(4).get.focus == <price>49.95</price>)
      println(loc1.down(12).get.down(4).get.path)
    }
  }

  describe("test insertLeft") {
    val loc1 = Location(catalog).down(2).get

    it("should insert to the left of the current focus") {
      println(loc1.insertLeft(<testInsertLeft>testInsertLeft</testInsertLeft>))
    }


  }
  /* describe("when insert to the left side of a node") {
      it("should increase the silbing by one") {
        val loc2 = <level2_pre>test +@:</level2_pre> +@: loc1.goDown.right.get
        val loc2_ = loc2.right.get.goTop
        info(loc2)
        info(loc2_)
      }
    }
    describe("when insert at the right side of a node") {
      it ("should increase the right sibling by 1"){
        val loc4 = loc1.goDown.right.get :@+ <level2_post>test :@+ </level2_post>
        info(loc4.right.get.goTop)
      }
    }
    describe("when goTop is called") {
      it("should goes to the Top most") {
        val loc3 = loc1.goDown.right.get.goDown.right.get.goTop
        assert(loc1 == loc3.right.get)
        info(loc3)
      }
      it("should throw an exception if it's already Top") {

      }
    }
    describe("when deleteSingle is called" ) {
      it("should delete and then go to a parent node") {
        val loc = loc1.goDown.right.get.goDown.right.get.deleteSingle
        info(loc)
        val loc2 = loc.right.get.goTop
        info(loc2)
      }
    }
    describe("when delete is called") {
      it("should delete") {
        val loc = loc1.goDown.right.get.goDown.right.get.delete.right.get
        info(loc.goTop)
      }
    }
    describe("when moves up") {
      it("should move up") {
        val loc = loc1.goDown.right.get.goDown.right.get.goDown
        info("before moving " + loc)
        info("after moving " + loc.right.get.goUp.right)
      }}
    describe("when goUpRight is called") {
      it("should go up right") {
        val loc = loc1.goDown.right.get.goDown.right.get.goDown
        info(loc)
        info(loc.right.get.goUpRight)
      }
    }
    describe("when move") {
      it("should move"){
        val loc = loc1
        info("change label")
        info(Right(loc))
        val loc2 = loc.move {
          case e:Elem => e.copy(label = e.label.toUpperCase)
          case other => other}
        info(loc2)
        info("add attribute")
        val loc_ = loc1
        info(loc_.move {
          case e:Elem => e.copy(attributes = Attribute(None,"class",Text("somevalue"),Null))
          case s:SpecialNode => info("find Group Monster"); s
          case other => other})
        info("test books.xml")
        val _loc = Location(x1)
        val __loc = move(_loc, {
          //case e:Elem if(e.attributes.asAttrMap.get("id").isEmpty) => e %  Attribute(None,"class",Text("somevalue"),Null)
          case e:Elem if(e.attribute("id").isEmpty) => e.copy(attributes =  Attribute(None,"id",Text("somevalue"),Null))
          case other => other// other can be Text or other
        })
        //info(__loc)
      }

    }*/

  val booksxml = <catalog>
    <book id="bk101">
      <author>Gambardella, Matthew</author>
      <title>XML Developer's Guide</title>
      <genre>Computer</genre>
      <price>44.95</price>
      <publish_date>2000-10-01</publish_date>
      <description>An in-depth look at creating applications
        with XML.</description>
    </book>
    <book id="bk102">
      <author>Ralls, Kim</author>
      <title>Midnight Rain</title>
      <genre>Fantasy</genre>
      <price>5.95</price>
      <publish_date>2000-12-16</publish_date>
      <description>A former architect battles corporate zombies,
        an evil sorceress, and her own childhood to become queen
        of the world.</description>
    </book>
    <book id="bk103">
      <author>Corets, Eva</author>
      <title>Maeve Ascendant</title>
      <genre>Fantasy</genre>
      <price>5.95</price>
      <publish_date>2000-11-17</publish_date>
      <description>After the collapse of a nanotechnology
        society in England, the young survivors lay the
        foundation for a new society.</description>
    </book>
    <book id="bk104">
      <author>Corets, Eva</author>
      <title>Oberon's Legacy</title>
      <genre>Fantasy</genre>
      <price>5.95</price>
      <publish_date>2001-03-10</publish_date>
      <description>In post-apocalypse England, the mysterious
        agent known only as Oberon helps to create a new life
        for the inhabitants of London. Sequel to Maeve
        Ascendant.</description>
    </book>
    <book id="bk105">
      <author>Corets, Eva</author>
      <title>The Sundered Grail</title>
      <genre>Fantasy</genre>
      <price>5.95</price>
      <publish_date>2001-09-10</publish_date>
      <description>The two daughters of Maeve, half-sisters,
        battle one another for control of England. Sequel to
        Oberon's Legacy.</description>
    </book>
    <book id="bk106">
      <author>Randall, Cynthia</author>
      <title>Lover Birds</title>
      <genre>Romance</genre>
      <price>4.95</price>
      <publish_date>2000-09-02</publish_date>
      <description>When Carla meets Paul at an ornithology
        conference, tempers fly as feathers get ruffled.</description>
    </book>
    <book id="bk107">
      <author>Thurman, Paula</author>
      <title>Splish Splash</title>
      <genre>Romance</genre>
      <price>4.95</price>
      <publish_date>2000-11-02</publish_date>
      <description>A deep sea diver finds true love twenty
        thousand leagues beneath the sea.</description>
    </book>
    <book id="bk108">
      <author>Knorr, Stefan</author>
      <title>Creepy Crawlies</title>
      <genre>Horror</genre>
      <price>4.95</price>
      <publish_date>2000-12-06</publish_date>
      <description>An anthology of horror stories about roaches,
        centipedes, scorpions and other insects.</description>
    </book>
    <book id="bk109">
      <author>Kress, Peter</author>
      <title>Paradox Lost</title>
      <genre>Science Fiction</genre>
      <price>6.95</price>
      <publish_date>2000-11-02</publish_date>
      <description>After an inadvertant trip through a Heisenberg
        Uncertainty Device, James Salway discovers the problems
        of being quantum.</description>
    </book>
    <book id="bk110">
      <author>O'Brien, Tim</author>
      <title>Microsoft .NET: The Programming Bible</title>
      <genre>Computer</genre>
      <price>36.95</price>
      <publish_date>2000-12-09</publish_date>
      <description>Microsoft's .NET initiative is explored in
        detail in this deep programmer's reference.</description>
    </book>
    <book id="bk111">
      <author>O'Brien, Tim</author>
      <title>MSXML3: A Comprehensive Guide</title>
      <genre>Computer</genre>
      <price>36.95</price>
      <publish_date>2000-12-01</publish_date>
      <description>The Microsoft MSXML3 parser is covered in
        detail, with attention to XML DOM interfaces, XSLT processing,
        SAX and more.</description>
    </book>
    <book id="bk112">
      <author>Galos, Mike</author>
      <title>Visual Studio 7: A Comprehensive Guide</title>
      <genre>Computer</genre>
      <price>49.95</price>
      <publish_date>2001-04-16</publish_date>
      <description>Microsoft Visual Studio 7 is explored in depth,
        looking at how Visual Basic, Visual C++, C#, and ASP+ are
        integrated into a comprehensive development
        environment.</description>
    </book>
  </catalog>
}
