package models

import org.scalatest.{FunSpec, Matchers, OneInstancePerTest}

class PlaneSpec extends FunSpec with Matchers with OneInstancePerTest {

  private val plane = new Plane(true)

  describe("when plane is initialized") {
    it("should have a flying status") {
      plane.flying should equal(true)
    }
  }

  describe("when taking off and landing") {
    it("should have a status of landed") {
      plane.land
      plane.flying should equal(false)
    }

    it("should be able to take off after it's landed") {
      plane.land
      plane.fly
      plane.flying should equal(true)
    }
  }
}
