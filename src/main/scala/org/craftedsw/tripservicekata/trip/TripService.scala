package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.{User, UserSession}

class TripService(getLoggedUser: () => Option[User],
                  findTripsByUser: User => List[Trip]) {

  def this(getLoggedUser: () => User) = this(
    () => Option(getLoggedUser()),
    TripDAO.findTripsByUser
  )

  def this() = this(
    () => Option(UserSession.getLoggedUser),
    TripDAO.findTripsByUser
  )

  def getTripsByUser(user: User): List[Trip] = {
    getLoggedUser().fold(
      ifEmpty = throw new UserNotLoggedInException
    ) { loggedInUser =>
      user.isFriend(loggedInUser) match {
        case true => findTripsByUser(user)
        case false => List.empty[Trip]
      }
    }
  }

}
