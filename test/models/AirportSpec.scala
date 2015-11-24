package models

import org.scalatest.{BeforeAndAfterEach, FunSpec, Matchers, OneInstancePerTest}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import scala.collection.mutable.ListBuffer

class AirportSpec extends FunSpec with Matchers with MockitoSugar with OneInstancePerTest with BeforeAndAfterEach {

  val airport = new Airport(planes = ListBuffer())
  val gatwick = spy(airport)
  val plane = mock[Plane]
  val plane2 = mock[Plane]

  def fillAirport(gatwick: Airport) = {
    1 to gatwick.DefaultCapacity foreach { _ => gatwick.arrivals(plane) }
  }

  override def beforeEach() = {
    when(gatwick.weatherReport).thenReturn("sunny")
  }

  describe("during arrivals and departures") {
    it("should allow planes to land in good weather") {
      gatwick.arrivals(plane)
      gatwick.planes.length should equal(1)
    }

    it("should know that when a plane has landed it's flight status has changed to landed") {
      gatwick.arrivals(plane)
      when(plane.land).thenReturn(false)
      assert(!plane.flying)

    }

    it("should allow planes to take off in sunny weather") {
      gatwick.arrivals(plane)
      gatwick.departures(plane)
      gatwick.planes.length should equal(0)
    }

    it("should know that when a plane has taken off it's flight status has changed to flying") {
      gatwick.arrivals(plane)
      gatwick.departures(plane)
      when(plane.fly).thenReturn(true)
      verify(plane).fly
    }
  }

  describe("traffic control") {
    it("should know when it is full") {
      fillAirport(gatwick)
      assert(gatwick.isFull)
    }

    it("should force planes to take off when it is full") {
      fillAirport(gatwick)
      gatwick.arrivals(plane2)
      when(plane.fly).thenReturn(true)
      verify(plane, times(6)).fly
    }
  }

  describe("regarding the weather") {

    def beforeEach() = {
      when(gatwick.weatherReport).thenReturn("windy")
    }

    it("should not allow planes to take off in strong winds") {
      intercept[Exception] {
        gatwick.departures(plane)
      }
    }

    it("should not allow planes to land in strong winds") {
      intercept[Exception] {
        gatwick.arrivals(plane)
      }
    }

  }

}
