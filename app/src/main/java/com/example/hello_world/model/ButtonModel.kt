package com.example.hello_world.model

class ButtonModel(val name: String) {

    companion object {
        private var lastContactId = 1
        var listOfButtons = ArrayList<ButtonModel>()

        fun addButton(): ArrayList<ButtonModel> {
            listOfButtons.add(ButtonModel(setButtonName(lastContactId)))
            ++lastContactId
            return listOfButtons
        }

        private fun setButtonName(id: Int): String {
            val input = "ноль раз два три четыре пять шесть семь восемь девять десять"
            val names = input.split(" ").map {
                it.trim()
            }
            if (id >= names.size) return "кнопка номер $id"
            return "кнопка номер " + names[id]
        }
    }
}