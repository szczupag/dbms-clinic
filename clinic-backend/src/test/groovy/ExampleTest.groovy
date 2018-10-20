import spock.lang.Specification

class ExampleTest extends Specification {
  def "test" () {
    given:
    def a = 1

    when:
    a = 2

    then:
    a == 2
  }
}
