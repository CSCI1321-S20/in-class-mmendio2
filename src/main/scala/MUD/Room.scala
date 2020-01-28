package MUD
import xml._
import org.w3c.dom.Node
class Room(val name: String, val desc: String, private var items: List[Item], private val exits: Array[Int]) {
    

  def description(): String = {
    var fullDesc = name + "\n" + desc + "\n" + "exits: "
    for(i <- 0 until exits.length){
      if(i != exits.length-1)
        fullDesc = convertExits(exits(i)) + ", "
      else
        fullDesc = convertExits(exits(i)) + "\n"        
    }
    for(i <- 0 until items.length){
      if(i != exits.length-1)
        fullDesc = items(i).name + ", "
      else
        fullDesc = items(i).name + "\n"
    }
    fullDesc
  }

  def convertExits(exit: Int): String = {
    if(exit == 0)
      return "north"
    if(exit == 1)
      return "south"
    if(exit == 2)
      return "east"
    if(exit == 3)
      return "west"
    if(exit == 4)
      return "up"
    if(exit == 5)
      return "down"
    else
      return "ERROR"
  }
      
  def getExit(dir: Int): Option[Room] = {
    if(exits.find(exits == dir) {
      return 
    }
  }

  def getItem(itemName: String): Option[Item] = {
    items.find(_.name.toLowerCase == itemName.toLowerCase) match {
      case Some(item) =>
        items = items.filter(_ != item)
        Some(item)
      case None => None
    }
  }

  def dropItem(item: Item): Unit = items ::= item
}

object Room {
  def readRooms(): Array[Room] = {
  val xdata = XML.loadFile("world.xml")
  (xdata \ "room").map(readRoom).toArray
  }

  def itemsFromNode(n: xml.Node): Item = {
    Item((n \ "@itemName").text, (n \ "@itemDescription").text)
  }
  def readRoom(n: xml.Node): Room = {
    val name = (n \ "@name").text
    val desc = (n \ "@description").text
    val exits = (n \ "@exitDirection").text.toArray
    val items = (n \ "@item").toList
    new Room(name, desc, items, exits)
  }
}
