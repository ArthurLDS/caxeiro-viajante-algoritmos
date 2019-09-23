package tsp

import tsp.fileUtils.ReaderEuclidine
import tsp.fileUtils.ReaderUpperRow
import tsp.model.AlgorithmType
import tsp.model.FileType
import java.util.*

object MainApplication {

    @JvmStatic
    fun main(args: Array<String>) {

        var fileType = 0

        while (fileType != 1 && fileType != 2) {
            val input = Scanner(System.`in`)
            println("=====================")
            println("Formato do Arquivo: ")
            println("1 - Euclidino")
            println("2 - Upper Row")
            print("Selecione um dos formatos listados acima: ")
            fileType = input.nextInt()
        }

        val fileTypeSelected = FileType.values().first { it.type == fileType }

        var algorithmType = 0

        while (algorithmType != 1 && algorithmType != 2) {
            val input = Scanner(System.`in`)
            println("=====================")
            println("Tipos de algoritmos: ")
            println("1 - Algoritmo Guloso")
            println("2 - Algoritmo A Estrela")
            print("Selecione um dos algoritmos listados acima: ")
            println("=====================")
            algorithmType = input.nextInt()
        }

        val algorithmSelected = AlgorithmType.values().first { it.type == algorithmType }

        val startTime = System.nanoTime()

        when (fileTypeSelected) {
            FileType.EUCLIDINE -> {
                ReaderEuclidine.run(algorithmSelected)
            }
            FileType.UPPER_ROW -> {
                ReaderUpperRow.run(algorithmSelected)
            }
        }

        val endTime = System.nanoTime()
        val duration = (endTime - startTime)/1000000

        println("\nTEMPO TOTAL DE EXECUCAO: $duration milisegundos")

    }

}