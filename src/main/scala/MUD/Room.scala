package MUD
import xml._
import org.w3c.dom.Node
class Room(val name: String, val desc: String, private var items: List[Item], private val exits: Array[Int]) {
    

  def description(): String = {
    var fullDesc = s"$name\n$desc\nexits: "
    for(i <- 0 until exits.length)
    {
      if(exits(i)!= -1)
        fullDesc = fullDesc + convertExits(i) + ", "
    }
    fullDesc= fullDesc.substring(0, fullDesc.length-2)
    fullDesc += "\nItems: "
    if(items.length != 0){
      for(i <- 0 until items.length){
        if(i!=items.length-1)
          fullDesc = fullDesc + items(i).name + ", "
        else
          fullDesc = fullDesc + items(i).name + "\n"
      }
    }
    else 
      fullDesc = fullDesc + "The room appears to be empty."
    fullDesc
  }

  def returnExits(index: Int): Int = exits(index)

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
      return "Error"
  }


 def getExit(dir: Int): Option[Room] = {
    if(exits(dir) != -1 )
      return Some(Room.rooms(dir))
    else 
      return None
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
  val rooms = readRooms()
  def readRooms(): Array[Room] = {
    val xdata = XML.loadFile("world.xml")
    (xdata \ "room").map(readRoom).toArray
  }

  def itemsFromNode(n: xml.Node): Item = {
    Item((n \ "@itemName").text, (n \ "@itemDescription").text)
  }
  def readRoom(n: xml.Node): Room = {
    val name = (n \ "@name").text
    val desc = (n \ "description").text.trim
    val exits = (n \ "exits").text.split(",").map(_.toInt).toArray
    val items = (n \ "item").map(itemsFromNode).toList
    new Room(name, desc, items, exits)
  }
}
