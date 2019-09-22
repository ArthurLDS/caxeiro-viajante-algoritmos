package tsp.model

class City {

    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var matrixDistances: Array<DoubleArray> = arrayOf()
    var name: String = ""
    private var euclidiana: Boolean = false
    var index: Int? = null

    constructor(nome: String, latitude: Double, longitude: Double) {
        this.name = nome
        this.longitude = longitude
        this.latitude = latitude
        this.euclidiana = true
    }

    constructor(i: Int, matrizDistancias: Array<DoubleArray>) {
        this.name = "" + i
        this.index = i
        this.matrixDistances = matrizDistancias
        this.euclidiana = false
    }

    fun calculateDistance(city: City): Double {
        return if (euclidiana) {
            val deltaLongitude = city.longitude - this.longitude
            val deltaLatitude = city.latitude - this.latitude
            Math.sqrt(deltaLongitude * deltaLongitude + deltaLatitude * deltaLatitude)
        } else {
            matrixDistances[this.index!!][city.index!!]
        }
    }

    override fun toString(): String {
        return name
    }

}