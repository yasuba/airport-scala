package models

import scala.collection.mutable.ListBuffer
import scala.util.Random

class Airport(var planes: ListBuffer[Plane]) {

  val DefaultCapacity = 6

  def weatherReport: String = {
    val weather = Array("sunny", "windy")
    val random = new Random
    weather(random.nextInt(weather.size))
  }

  @throws(classOf[Exception])
  def arrivals(plane: Plane) = {
    if (weatherReport == "windy") throw new Exception("All flights have been rerouted due to adverse weather conditions.")
    plane.land
    planes += plane
    if (planes.length > DefaultCapacity) scramble()
  }

  def departures(plane: Plane) = {
    if (weatherReport == "windy") throw new Exception("All flights cancelled due to adverse weather conditions.")
    plane.fly
    planes -= plane
  }

  def isFull = planes.length == DefaultCapacity

  def scramble() = {
    println("scrambled")
    planes.foreach(plane => plane.fly)
    planes.clear()
  }

}

