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
Type: 'inv' or 'inventory'- display your inventory and get a detailed description of your items. 
Type: 'get ---' (where --- is the name of an item) - get an item from the room and add it to your inventory. 
Type: 'drop ---' (where --- is the name of an item) - drop an item from your inventory into the room.
Type: 'exit' - leave the game.
            """.trim)
            case "inv" | "inventory" => println(inventoryListing())
            case "look" => println(Room.rooms(location).description())
            case "get" =>
                Room.rooms(location).getItem(input(1).toLowerCase()) match{
                    case Some(item) =>
                        println("You picked up: " + item.returnName) 
                        addToInventory(item)
                    case None => println("That isn't the name of an item in this room.")
                }
            case "drop" => 
                getFromInventory(input(1).toLowerCase) match {
                    case Some(item) =>
                        println("You dropped: " + item.returnName)
                        inventory = inventory.filter(_ != item)
                        Room.rooms(location).dropItem(item)
                    case None => println("You can't drop an item you don't have.")
                }
            case "exit" => println("Goodbye")
            case _ => println("Don't Understand, try typing 'help' for a list of available actions.")
        }
    }


    def getFromInventory(itemName: String): Option[Item] = {
        inventory.find(_.name.toLowerCase == itemName.toLowerCase) match {
        case Some(item) => Some(item)
        case None => None
        }
    }

    def addToInventory(item: Item): Unit = inventory ::= item

    def inventoryListing(): String = 
    {
        if(inventory.length != 0){
            var inventoryList = ""
            for(i <- 0 until inventory.length){
                inventoryList = inventoryList+ inventory(i).returnName + ": " + inventory(i).returnDesc
            }
            inventoryList
        }
        else
            "You have nothing! That sucks."
    }
    def move(dir: String): Unit = {
        dir.toLowerCase match {
            case "north" | "n" =>
                Room.rooms(location).getExit(0) match {
                    case Some(room) =>
                        location = Room.rooms(location).returnExits(0)
                        println(Room.rooms(location).description())
                    case None => println("You can't go that way.")
            }
            case "south" | "s" =>
                Room.rooms(location).getExit(1) match {
                    case Some(room) =>
                        location = Room.rooms(location).returnExits(1)
                        println(Room.rooms(location).description())
                    case None => println("You can't go that way.")
            }
            case "east" | "e" =>
                Room.rooms(location).getExit(2) match {
                    case Some(room) =>
                        location = Room.rooms(location).returnExits(2)
                        println(Room.rooms(location).description())
                    case None => println("You can't go that way.")
            }
            case "west" | "w" =>
                Room.rooms(location).getExit(3) match {
                    case Some(room) =>
                        location = Room.rooms(location).returnExits(3)
                        println(Room.rooms(location).description())
                    case None => println("You can't go that way.")
            }
            case "up" | "u" =>
                Room.rooms(location).getExit(4) match {
                    case Some(room) =>
                        location = Room.rooms(location).returnExits(4)
                        println(Room.rooms(location).description())
                    case None => println("You can't go that way.")
            }
            case "down" | "d" =>
                Room.rooms(location).getExit(5) match {
                    case Some(room) =>
                        location = Room.rooms(location).returnExits(5)
                        println(Room.rooms(location).description())
                    case None => println("You can't go that way.")
            }
            case _ => println("Incorrect Input, try typing 'help'!")
        }
    }
}