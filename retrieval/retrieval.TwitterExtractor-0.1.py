# python standard library
import base64
import json

# 3rd party
import requests

def get_token(client_id, client_secret):
    credentials = '{}:{}'.format(client_id, client_secret)
    credentials_b64 = base64.b64encode(credentials.encode())
    resp = requests.post(
        'https://api.twitter.com/oauth2/token',
        headers={
            'Authorization': 'Basic {}'.format(credentials_b64.decode())
        },
        data={'grant_type': 'client_credentials'}
    )
    if resp.status_code == 200:
        data = resp.json()
        return data['access_token']
    else:
        raise ValueError(
            'error in request, code={} body={}'.format(
                resp.status_code, resp.text
            )
        )


def search_tweets(what, token):
    #what = 'java'
    '''
    Returns:
      a list of tweets (dictionaries)
    '''
    url = 'https://api.twitter.com/1.1/search/tweets.json'
    resp = requests.get(
        url,
        headers={'Authorization': 'Bearer {}'.format(token)},
        params={'q': what, 'lang': 'it', 'count':'4'}
    )
    resp.raise_for_status()
    data = resp.json()
    return data['statuses']


def save_tweets(tweets):
    count = 0
    for tweet in tweets:
        count += 1
        cleanedTweet = resize(tweet)
        f_destination = open('ggTweet'+str(count), 'w+')
        json.dump(cleanedTweet, f_destination)
        f_destination.close()

def resize(tweet):
    data = {}
    data['id_str'] = tweet['id_str']
    data['programming_language'] = str(prog_lang)
    data['created_at'] = tweet['created_at']
    data['text'] = tweet['text']
    data['favorite_count'] = tweet['favorite_count']
    data['lang'] = tweet['lang']
    data['coordinates'] = tweet['coordinates']
    data['retweet_count'] = tweet['retweet_count']
    data['place'] = tweet['place']
    data['geo'] = tweet['geo']
    hashtags = tweet['entities']['hashtags']
    hashtag_list = []
    for hashtag in hashtags:
        hashtag_list.append(hashtag['text'])
        data['hashtags'] = hashtag_list
    data['user_geo_Enabled'] = tweet['user']['geo_enabled']
    data['user_friends_count'] = tweet['user']['friends_count']
    data['user_lang'] = tweet['user']['lang']
    data['user_location'] = tweet['user']['location']
    data['user_time_zone'] = tweet['user']['time_zone']
    data['user_followers_count'] = tweet['user']['followers_count']
    json_data = json.dumps(data)
    return data

#ritorna una lista di stringhe contenente tutti i linguaggi presenti nel file programmingLanguages.md
def get_prog_lang(file):
    file = '.\programmingLanguages.md'
    reader = open(file, 'r').read()
    langList = reader.split(',')
    return langList

def save_in_db(tweetList):
    import pymongo
    #impostazioni predefinite
    client = MongoClient('mongodb://localhost:27017/')
    db = client.test_database
    collection = db.test_collection
    result = posts.insert_many(tweetList)
    result.inserted_ids

if __name__ == '__main__':
    import os
    import sys
    print('getting token...')
    token = get_token(
        os.environ['TWITTER_APP_ID'],
        os.environ['TWITTER_APP_SECRET']
    )
    print('getting tweets...')
    tweets = search_tweets(sys.argv[1], token)
    prog_lang = str(sys.argv[1])
    print('saving tweets...')
    save_tweets(tweets)
    print('OK!')
