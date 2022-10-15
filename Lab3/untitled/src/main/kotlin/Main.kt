fun main() {
    val itemController = ItemController(ItemService(ItemRepository))
    itemController.quiz(3)
}