package tsp.fileUtils

import tsp.algorithm.AStar
import tsp.algorithm.Greedy
import tsp.model.AlgorithmType
import tsp.model.City
import java.io.BufferedReader
import java.io.FileReader
import java.util.*

object ReaderUpperRow : BaseReader() {

    private var distanceCities: Array<DoubleArray>? = null
    var initialCities = ArrayList<City>()

    //@JvmStatic
    //fun main(args: Array<String>) {

    fun run(algorithmType: AlgorithmType) {

        val fileName = readFileName()

        var file = FileReader(fileName)
        var reader = BufferedReader(file)
        var line = reader.readLine()

        try {
            var counter = 0
            while (line != "EOF") {
                counter++
                line = reader.readLine()
            }
            distanceCities = Array(counter + 1) { DoubleArray(counter + 1) }
            reader.close()

            file = FileReader(fileName)
            reader = BufferedReader(file)
            line = reader.readLine()
            var indexLine = 0
            while (line != "EOF") {
                val lineSplit = line.trim { it <= ' ' }.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val splitDouble = DoubleArray(lineSplit.size)
                for (i in splitDouble.indices) {
                    splitDouble[i] = java.lang.Double.parseDouble(lineSplit[i])
                }
                var aux = 0
                if (splitDouble.isNotEmpty()) {
                    for (j in distanceCities!!.indices) {
                        while (aux < splitDouble.size) {
                            if (distanceCities!![indexLine][j] == 0.0 && indexLine == j) {
                                break
                            } else if (distanceCities!![indexLine][j] != 0.0) {
                                break
                            } else {
                                distanceCities!![indexLine][j] = splitDouble[aux]
                                distanceCities!![j][indexLine] = splitDouble[aux]
                                aux++
                            }
                        }
                    }
                }
                line = reader.readLine()
                indexLine++
            }
            reader.close()
            for (i in distanceCities!!.indices) {
                initialCities.add(City(i, distanceCities!!))
            }

            when (algorithmType) {
                AlgorithmType.GREEDY -> {
                    val greedy = Greedy(initialCities)
                    greedy.calculateRoute()
                }
                AlgorithmType.A_STAR -> {
                    val aStar = AStar(initialCities)
                    aStar.calculateRoute()
                }
            }

            val aStar = AStar(initialCities)
            aStar.calculateRoute()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}