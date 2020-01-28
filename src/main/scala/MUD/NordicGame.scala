package MUD


object NordicGame {
    def main(args: Array[String]): Unit ={
        println("Welcome to NordicMud! \n what's your name?")
        val name = readLine()
        println(s"Hello, $name")
        val inventory = List[Item]()
        val player = new Player(1, inventory)
        
    }
}