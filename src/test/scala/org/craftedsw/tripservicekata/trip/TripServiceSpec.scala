package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.infrastructure.UnitSpec
import org.craftedsw.tripservicekata.user.User

class TripServiceSpec extends UnitSpec {

  "When user is not logged return UserNotLoggedInException" in {
    val subject: TripService = new TripService(() => null)

    intercept[UserNotLoggedInException](subject.getTripsByUser(new User))
  }

  "When user for trip with not friends return empty trips" in {
    val subject: TripService = new TripService( () => new User() )

    subject.getTripsByUser(new User) shouldBe List.empty
  }

  "When user logged and user for trip are friends" in {
    val loggedUser = new User()
    val userForTrip = new User()
    userForTrip.addFriend(loggedUser)
    userForTrip.addTrip(new Trip)

    val subject: TripService = new TripService(
      () => Option(loggedUser),
      user => { user.trips() }
    )

    subject.getTripsByUser(userForTrip) shouldBe userForTrip.trips()
  }


}
