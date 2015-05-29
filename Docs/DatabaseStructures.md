#Database Structures:

#Retrieval:
Collection: "tweets"
See [here] (ProjectNAME/Docs/TweetExtractionStructure.md) for structure

#Cleaning:
Collection: "cleaning"
{ 
	ProgrammingLanguage : str,
	Date : date,
	Text --> BaseValue : int,
	Favorite : int,
	(Lang, Coordinates, Place, Geo, UserGeoEnabled, UserLang, UserLocation, UserTimeZone) --> Nation : str,
	RetweetCount : int,
	UserFriends : int,
	UserFollower : int,
}

#Analysis:
Collection: "analysis"
{
	Nation : str,
	ProgrammingLanguage : str,
	Date : date,
	(BaseValue, Favorite, RetweetCount, UserFriends, UserFollower) --> Value : long
}


#Presentation:
Collection: "stats"
{
	TimePeriod : date,
	Language : str,
	Nation : str,
	Value : long
}