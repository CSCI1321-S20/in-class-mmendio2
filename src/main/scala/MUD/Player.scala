package MUD

class Player(private var location: Int, private var inventory: List[Item]) {
    def processCommand(command: String): Unit = {
        if(command.toLowerCase!="exit"){
            command.toLowerCase match {
                case "look" => ??? 
                case "inv" || "iventory" => inventoryListing()
                case "help" => println("north, south, east, west, up, or down (or the first letter): move in that directions\nlook: get the description of the room\ninv/inventory: display your inventory\nget item: get an item from the room and add it to your inventory
\ndrop an item from your inventory into the room.\nexit: leave the game.")
                case "north" || "n" => move("north")
                case "south" || "s" => move("south")
                case "east" || "e" => move("east")
                case "west" || "w" => move("west")
                case "up" || "u" => move("up")
                case "down" || "d" => move("down")
            
            }
            println("What would you like to do?")
            val newCommand = readLine()
            processCommand(newCommand)
        }
        else 
            println("Bye!")
    }

    def getFromInventory(itemName: String): Option[Item] = {
        inventory.find(_.name.toLowerCase == itemName.toLowerCase) match {
        case Some(item) =>
            items = items.filter(_ != item)
            Some(item)
        case None => None
        }
    }

    def addToInventory(item: Item): Unit = inventory ::= item

    def inventoryListing(): String = inventory.mkString("You have: ", ", ", ".")

    def move(dir: String): Unit = ???
}