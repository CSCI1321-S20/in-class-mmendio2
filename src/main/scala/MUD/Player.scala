package MUD

class Player(private var location: Int, private var inventory: List[Item]) {
    def processCommand(command: String): Unit = {


        val input = command.split(" +", 2)
        input(0).toLowerCase() match {
            case "north" | "n" => move("north")
            case "south" | "s" => move("south")
            case "east" | "e" => move("east")
            case "west" | "w" => move("west")
            case "up" | "u" => move("up")
            case "down" | "d" => move("down")
            case "help" => println("""
Type: 'north,' 'south,' 'east,' 'west,' 'up,' or 'down' (or the first letter of the respective): move in that directions.
Type: 'look' - get the description of the room.
Type: 'inv' or 'inventory'- display your inventory. 
Type: 'get ---' (where --- is the name of an item) - get an item from the room and add it to your inventory. 
Type: 'drop ---' (where --- is the name of an item) - drop an item from your inventory into the room.
Type: 'exit' - leave the game.
            """.trim)
            case "inv" | "inventory" => println(inventoryListing())
            case "look" => println(Room.rooms(location).description())
            case "exit" => println("Goodbye")
            case _ => println("Don't Understand, try typing 'help' for a list of available actions.")
        }
    }


    def getFromInventory(itemName: String): Option[Item] = {
        inventory.find(_.name.toLowerCase == itemName.toLowerCase) match {
        case Some(item) =>
            inventory = inventory.filter(_ != item)
            Some(item)
        case None => None
        }
    }

    def addToInventory(item: Item): Unit = inventory ::= item

    def inventoryListing(): String = 
    {
        if(inventory.length != 0)
            inventory.mkString("You have: ", ", ", ".")
        else
            "You have nothing! That sucks."
    }
    def move(dir: String): Unit = 
        if(Room.rooms(location).getExit(dir))
        {
            location = Room.rooms(location)getExit(dir)
            println(Room.rooms(location).description())
        }
}