package co.quine.osprey

import akka.actor._
import resources._

sealed trait Operation
case class TwitterOperation(uuid: String, resource: TwitterResource, client: ActorRef, retries: Int = 0) extends Operation

