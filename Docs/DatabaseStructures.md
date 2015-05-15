#Database Structures:

#Retrieval:
Collection: "tweets"
See [here] (ProjectNAME/Docs/TweetExtractionStructure.md) for structure

#Cleaning:
Collection: "cleaning01" --> "cleaning02" --> "cleaning03" each with less and less keys as soon as they are rendered useless.

#Analysis:
Collection: "analysis"


#Presentation:
Collection: "stats"
{
	TimePeriod : str,
	Language : str,
	Nation : str,
	Value : int
}