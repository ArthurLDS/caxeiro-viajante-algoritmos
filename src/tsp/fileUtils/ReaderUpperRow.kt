package tsp.fileUtils

import tsp.algorithm.AStar
import tsp.model.City
import java.io.BufferedReader
import java.io.FileReader
import java.util.*

object ReaderUpperRow: BaseReader() {

    private var distanceCities: Array<DoubleArray>? = null
    var initialCities = ArrayList<City>()

    @JvmStatic
    fun main(args: Array<String>) {

        val fileName = readFileName()

        try {
            val file = FileReader(fileName)
            val reader = BufferedReader(file)
            var line = reader.readLine()
            var counter = 0
            while (line != "EOF") {
                counter++
                line = reader.readLine()
            }
            distanceCities = Array(counter + 1) { DoubleArray(counter + 1) }
            reader.close()
        } catch (e: Exception) {
            println("Erro durante a leitura do arquivo!")
            e.printStackTrace()
        }

        try {
            val arquivo = FileReader(fileName)
            val leitor = BufferedReader(arquivo)
            var linha = leitor.readLine()
            var indexLinha = 0
            while (linha != "EOF") {
                val split = linha.trim { it <= ' ' }.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val splitDouble = DoubleArray(split.size)
                for (i in splitDouble.indices) {
                    splitDouble[i] = java.lang.Double.parseDouble(split[i])
                }
                var aux = 0
                if (splitDouble.isNotEmpty()) {
                    for (j in distanceCities!!.indices) {
                        while (aux < splitDouble.size) {
                            if (distanceCities!![indexLinha][j] == 0.0 && indexLinha == j) {
                                break
                            } else if (distanceCities!![indexLinha][j] != 0.0) {
                                break
                            } else {
                                distanceCities!![indexLinha][j] = splitDouble[aux]
                                distanceCities!![j][indexLinha] = splitDouble[aux]
                                aux++
                            }
                        }
                    }
                }
                linha = leitor.readLine()
                indexLinha++
            }
            leitor.close()
            for (i in distanceCities!!.indices) {
                initialCities.add(City(i, distanceCities!!))
            }

            val aStar = AStar(initialCities)
            aStar.calculateRoute()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}