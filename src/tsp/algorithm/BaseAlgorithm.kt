package tsp.algorithm

import tsp.model.City
import java.util.*

abstract class BaseAlgorithm(initialCities: ArrayList<City>) {

    val remainingCities: MutableList<City> = mutableListOf()
    val pathRoute: MutableList<City> = mutableListOf()
    var pathCost: Double = 0.0

    init {
        this.remainingCities.addAll(initialCities)
    }

    abstract fun calculateNextCity()

    fun calculateRoute() {
        val randomIndex = 5//Random().nextInt(remainingCities.size)
        pathRoute.add(remainingCities[randomIndex])
        remainingCities.removeAt(randomIndex)

        println("==> Cidade inicial selecionda: ${pathRoute[0]}")

        while (!remainingCities.isNullOrEmpty()) {
            calculateNextCity()
        }

        pathCost += pathRoute[pathRoute.size - 1].calculateDistance(pathRoute[0])
        printResultReport()
    }


    fun printResults(lastCityVisited: City, indexCity: Int) {
        pathCost += lastCityVisited.calculateDistance(remainingCities[indexCity])
        pathRoute.add(remainingCities[indexCity])
        remainingCities.removeAt(indexCity)
        println("${remainingCities.size} cidades restantes: ${Arrays.toString(remainingCities.toTypedArray())}" +
                "\nRota atual: ${Arrays.toString(pathRoute.toTypedArray())}" +
                "\nCusto parcial: ${String.format("%.3f", pathCost)}  km")
        println()
    }

    private fun printResultReport() {
        println("\nRota ideal: ${Arrays.toString(pathRoute.toTypedArray())}")
        println("Custo total em quilometros: ${String.format("%.3f", pathCost)} km")
    }


}