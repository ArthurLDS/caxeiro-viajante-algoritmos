package tsp.fileUtils

import java.util.*

abstract class BaseReader {

    fun readFileName(): String {
        val input = Scanner(System.`in`)
        print("Digite o caminho do arquivo: ")
        val fileName = input.nextLine()
        input.close()
        return fileName
    }

}
