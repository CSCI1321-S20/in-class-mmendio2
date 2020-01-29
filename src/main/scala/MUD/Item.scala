package MUD

case class Item(name: String, desc: String){
    def returnName: String = name
    def returnDesc: String = desc
}