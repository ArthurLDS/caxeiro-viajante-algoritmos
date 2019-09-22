package tsp.algorithm

import tsp.model.City
import java.util.*

class AStar(initialCities: ArrayList<City>) : BaseAlgorithm(initialCities) {

    override fun calculateNextCity() {
        val lastCityVisited = pathRoute.last()
        var indexCity = 0
        var totalSumFn = 0.0
        for (i in remainingCities.indices) {
            if (i == 0) {
                indexCity = i
                totalSumFn = (pathCost + lastCityVisited.calculateDistance(remainingCities[i])
                        + pathRoute[0].calculateDistance(remainingCities[i]))
            } else {
                val fn = (pathCost + lastCityVisited.calculateDistance(remainingCities[i])
                        + pathRoute[0].calculateDistance(remainingCities[i]))
                if (fn < totalSumFn) {
                    indexCity = i
                    totalSumFn = (pathCost + lastCityVisited.calculateDistance(remainingCities[i])
                            + pathRoute[0].calculateDistance(remainingCities[i]))
                }
            }
        }

        printResults(lastCityVisited, indexCity)
    }
}
