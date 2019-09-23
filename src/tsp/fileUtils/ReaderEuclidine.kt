package tsp.fileUtils

import tsp.algorithm.AStar
import tsp.algorithm.Greedy
import tsp.model.AlgorithmType
import tsp.model.City
import java.io.BufferedReader
import java.io.FileReader
import java.util.*

object ReaderEuclidine : BaseReader() {
    var initialCities = ArrayList<City>()

    //@JvmStatic
    //fun main(args: Array<String>) {

    fun run(algorithmType: AlgorithmType) {

        val fileName = readFileName()

        try {
            val file = FileReader(fileName)
            val reader = BufferedReader(file)
            var line = reader.readLine()
            while (line != "EOF") {
                val split = line.trim { it <= ' ' }.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                initialCities.add(City(split[0], java.lang.Double.parseDouble(split[1]), java.lang.Double.parseDouble(split[2])))
                line = reader.readLine()
            }
            reader.close()
        } catch (e: Exception) {
            println("Um erro ocorreu na leitura do arquivo!")
            e.printStackTrace()
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


    }
}
