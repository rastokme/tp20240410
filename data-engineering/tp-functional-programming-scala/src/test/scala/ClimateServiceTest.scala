import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
    assert(ClimateService.isClimateRelated("global warming"))
    assert(ClimateService.isClimateRelated("This sentence contains IPCC"))
    assert(ClimateService.isClimateRelated("Another sentence with Global Warming"))
  }

  //@TODO
  test("parseRawData") {
    // inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  //@TODO
  test("filterDecemberData") {
    assert(true == false)
  }
}

test("parseRawData - parse valid data correctly") {
  val rawData = List((2000, 1, 350.5), (2000, 2, 355.2), (2000, 3, -100.0), (2000, 4, 360.7))
  val parsedData = ClimateService.parseRawData(rawData)
  assert(parsedData.flatten.length == 3) // Ensure only valid records are parsed
}

test("findDifference - calculate difference between max and min CO2 concentration") {
  val records = List(
    CO2Record(2000, 1, 350.5),
    CO2Record(2000, 2, 355.2),
    CO2Record(2000, 3, 360.7)
  )
  val difference = ClimateService.findDifference(records)
  assert(difference.contains(10.2)) // Expected difference: 360.7 - 350.5 = 10.2
}

