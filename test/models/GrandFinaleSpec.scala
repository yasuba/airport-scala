package models

import org.scalatest.{BeforeAndAfterEach, FunSpec, Matchers}
import org.mockito.Mockito._
import scala.collection.mutable.ListBuffer


class GrandFinaleSpec extends FunSpec with Matchers with BeforeAndAfterEach {

  val airport = new Airport(planes = ListBuffer())
  val gatwick = spy(airport)

  val britishAirways = new Plane
  val airFrance = new Plane
  val lufthansa = new Plane
  val quantas = new Plane
  val cathayPacific = new Plane
  val americanAirlines = new Plane

  val planes = List(britishAirways, airFrance, lufthansa, quantas, cathayPacific, americanAirlines)

  override def beforeEach(): Unit = {
    when(gatwick.weatherReport).thenReturn("sunny")
  }

  describe("The Grand Finale") {

    it("all planes can land and all planes can take off") {

      planes.foreach(plane =>
        gatwick.arrivals(plane))
      assert(gatwick.isFull)
      assert(!britishAirways.isFlying)

      planes.foreach(plane =>
        gatwick.departures(plane))
      assert(britishAirways.isFlying)
    }
  }

}
