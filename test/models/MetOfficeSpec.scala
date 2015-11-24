package models

import org.scalatest.mock.MockitoSugar
import org.scalatest.{OneInstancePerTest, ShouldMatchers, FunSpec}

import scala.collection.mutable.ListBuffer


class MetOfficeSpec extends FunSpec with ShouldMatchers with MockitoSugar with OneInstancePerTest {

  describe("the weather") {

    val heathrow = new Airport(ListBuffer())

    it("should randomly generate the weather") {
      heathrow.weatherReport should (equal("sunny") or equal ("windy"))
    }
  }

}
