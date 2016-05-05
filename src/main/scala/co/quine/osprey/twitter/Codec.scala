package co.quine.osprey.twitter

import argonaut._, Argonaut._

object Codec {

  case class User(created_at: String,
                  description: Option[String],
                  favourites_count: Int,
                  followers_count: Int,
                  friends_count: Int,
                  geo_enabled: Boolean,
                  id: Long,
                  listed_count: Int,
                  location: Option[String],
                  name: Option[String],
                  profile_banner_url: String,
                  profile_image_url: String,
                  protekted: Boolean,
                  screen_name: String,
                  status: Option[Tweet],
                  statuses_count: Int,
                  time_zone: Option[String],
                  url: Option[String],
                  verified: Boolean)

  case class Tweet(coordinates: Option[Coordinates],
                   created_at: String,
                   entities: Option[Entities],
                   favorite_count: Int,
                   id: Long,
                   in_reply_to_status_id: Option[Long],
                   in_reply_to_user_id: Option[Long],
                   quoted_status_id: Option[Long],
                   retweet_count: Int,
                   retweeted_status: Option[Tweet],
                   text: String,
                   user: Option[User])

  case class Coordinates(longitude: Double, latitude: Double)

  case class Entities(hashtags: Option[Seq[Hashtag]],
                      media: Option[Seq[Media]],
                      urls: Option[Seq[Url]],
                      user_mentions: Option[Seq[Mention]])

  case class Hashtag(indices: Seq[Int], text: String)

  case class Media(expanded_url: String,
                   source_status_id: Option[Long],
                   mType: String)

  case class Url(expanded_url: String)

  case class Mention(name: String, id: Long)

  case class UserIds(previous_cursor: Long, ids: Seq[Long], next_cursor: Long)

  case class UserList(previous_cursor: Long, next_cursor: Long, users: Seq[User])

  implicit def UserDecodeJson: DecodeJson[User] = {
    DecodeJson(c => for {
      created_at <- (c --\ "created_at").as[String]
      description <- (c --\ "description").as[Option[String]]
      favourites_count <- (c --\ "favourites_count").as[Int]
      followers_count <- (c --\ "followers_count").as[Int]
      friends_count <- (c --\ "friends_count").as[Int]
      geo_enabled <- (c --\ "geo_enabled").as[Boolean]
      id <- (c --\ "id").as[Long]
      listed_count <- (c --\ "listed_count").as[Int]
      location <- (c --\ "location").as[Option[String]]
      name <- (c --\ "name").as[Option[String]]
      profile_banner_url <- (c --\ "profile_banner_url").as[String]
      profile_image_url <- (c --\ "profile_image_url").as[String]
      protekted <- (c --\ "protected").as[Boolean]
      screen_name <- (c --\ "screen_name").as[String]
      status <- (c --\ "status").as[Option[Tweet]]
      statuses_count <- (c --\ "statuses_count").as[Int]
      time_zone <- (c --\ "time_zone").as[Option[String]]
      url <- (c --\ "url").as[Option[String]]
      verified <- (c --\ "verified").as[Boolean]
    } yield User(
        created_at,
        description,
        favourites_count,
        followers_count,
        friends_count,
        geo_enabled,
        id,
        listed_count,
        location,
        name,
        profile_banner_url,
        profile_image_url,
        protekted,
        screen_name,
        status,
        statuses_count,
        time_zone,
        url,
        verified))
  }

  implicit def TweetDecodeJson: DecodeJson[Tweet] = {
    DecodeJson(c => for {
      coordinates <- (c --\ "coordinates").as[Option[Coordinates]]
      created_at <- (c --\ "created_at").as[String]
      entities <- (c --\ "entities").as[Option[Entities]]
      favorite_count <- (c --\ "favorite_count").as[Int]
      id <- (c --\ "id").as[Long]
      in_reply_to_status_id <- (c --\ "in_reply_to_status_id").as[Option[Long]]
      in_reply_to_user_id <- (c --\ "in_reply_to_user_id").as[Option[Long]]
      quoted_status_id <- (c --\ "quoted_status_id").as[Option[Long]]
      retweet_count <- (c --\ "retweet_count").as[Int]
      retweeted_status <- (c --\ "retweeted_status").as[Option[Tweet]]
      text <- (c --\ "text").as[String]
      user <- (c --\ "user").as[Option[User]]
    } yield Tweet(
        coordinates,
        created_at,
        entities,
        favorite_count,
        id,
        in_reply_to_status_id,
        in_reply_to_user_id,
        quoted_status_id,
        retweet_count,
        retweeted_status,
        text,
        user))
  }

  implicit def CoordinatesDecodeJson: DecodeJson[Coordinates] = {
    DecodeJson(c => for {
      longlat <- (c --\ "coordinates").as[Seq[Double]]
    } yield longlat match { case Seq(long, lat) => Coordinates(long, lat) })
  }

  implicit def EntitiesDecodeJson: DecodeJson[Entities] = {
    DecodeJson(c => for {
      hashtags <- (c --\ "hashtags").as[Option[Seq[Hashtag]]]
      media <- (c --\ "media").as[Option[Seq[Media]]]
      urls <- (c --\ "urls").as[Option[Seq[Url]]]
      user_mentions <- (c --\ "user_mentions").as[Option[Seq[Mention]]]
    } yield Entities(hashtags, media, urls, user_mentions))
  }

  implicit def HashtagDecodeJson: DecodeJson[Hashtag] = {
    DecodeJson(c => for {
      indices <- (c --\ "indices").as[Seq[Int]]
      text <- (c --\ "text").as[String]
    } yield Hashtag(indices, text))
  }

  implicit def MediaDecodeJson: DecodeJson[Media] = {
    DecodeJson(c => for {
      expanded_url <- (c --\ "expanded_url").as[String]
      source_status_id <- (c --\ "source_status_id").as[Option[Long]]
      mType <- (c --\ "type").as[String]
    } yield Media(expanded_url, source_status_id, mType))
  }

  implicit def UrlDecodeJson: DecodeJson[Url] = {
    DecodeJson(c => for {
      expanded_url <- (c --\ "expanded_url").as[String]
    } yield Url(expanded_url))
  }

  implicit def MentionDecodeJson: DecodeJson[Mention] = {
    DecodeJson(c => for {
      name <- (c --\ "name").as[String]
      id <- (c --\ "id").as[Long]
    } yield Mention(name, id))
  }

  implicit def UserIdsDecodeJson: DecodeJson[UserIds] = {
    DecodeJson(c => for {
      previous_cursor <- (c --\ "previous_cursor").as[Long]
      ids <- (c --\ "ids").as[Seq[Long]]
      next_cursor <- (c --\ "next_cursor").as[Long]
    } yield UserIds(previous_cursor, ids, next_cursor))
  }

  implicit def UserListDecodeJson: DecodeJson[UserList] = {
    DecodeJson(c => for {
      previous_cursor <- (c --\ "previous_cursor").as[Long]
      next_cursor <- (c --\ "next_cursor").as[Long]
      users <- (c --\ "users").as[Seq[User]]
    } yield UserList(previous_cursor, next_cursor, users))
  }

}