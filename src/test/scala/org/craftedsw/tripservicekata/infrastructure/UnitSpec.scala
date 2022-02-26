package org.craftedsw.tripservicekata.infrastructure

import org.scalatest._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

abstract class UnitSpec extends AnyFreeSpec
  with Matchers
  with OptionValues
  with Inside
  with Inspectors
