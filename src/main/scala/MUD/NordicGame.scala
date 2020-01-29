package MUD


object NordicGame {
    def main(args: Array[String]): Unit ={
        println("Welcome to NordicMud!\nWhat's your name?")
        val name = readLine
        println(s"Hello, $name. Type a 'help' for a list of available actions.")
        var input = "beginning"
        val items = List[Item]()
        val player = new Player(0, items)
        while(input.toLowerCase!="exit"){
            println("\nWhat would you like to do?")
            input = readLine 
            player.processCommand(input)
        }
        
    }
}