# python standard library
import base64
import json

# 3rd party
import requests
'''
get the token to make requests to twitter from client id and secret
'''
def get_token(client_id, client_secret):
    client_id = "Kbvd9oQNAF6cr9XULzoRu5b1n"
    client_secret = "dNJOsK3gcOiJJmrVdA1jKAt2usjzauQwXpiQpPv9CJCpUfo67W"
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

# errore 429 all'esaurimento delle richieste per i 15min
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
        params={'q': what, 'count':'1'}
    )
    resp.raise_for_status()
    data = resp.json()
    return data['statuses']

'''
save raw tweets on a json file
'''
def save_tweets(tweets):
    count = 0
    for tweet in tweets:
        count += 1
        f_destination = open('rawTweet'+str(count), 'w+')
        json.dump(cleanedTweet, f_destination)
        f_destination.close()

'''
save customized tweets on a json file
'''
def save_tweets_on_jsonfile(tweets):
    count = 0
    for tweet in tweets:
        count += 1
        cleanedTweet = resize(tweet)
        destination = 'cleanedTweet'+str(count)
        f_destination = open(destination, 'w+')
    json.dump(cleanedTweet, f_destination)
    f_destination.close()


'''
create a customized tweet from a row one
'''
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
    json_data = bson.dumps(data)
    return data

'''
create a list of customized tweets from raw tweets
'''
def resize_cicle(tweets):
    json_data_list = []
    for tweet in tweets:
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
	json_data_list.append(json_data)
    return json_data_list

'''
convert raw datas in bson
'''
def to_bson(datas):
    from bson import BSON
    bson_data_list = []
    for data in datas:
        bson_data = BSON.encode(data)
        bson_data_list.append(bson_data)
    return bson_data_list

'''
returns a list of strings from a file
'''
def get_prog_lang(file):
    file = './programmingLanguages.md'
    reader = open(file, 'r').read()
    langList = reader.split(',')
    return langList

'''
save datas in mongoDB
'''
def save_in_db(tweetList):
    from pymongo import MongoClient
    client = MongoClient()
    #client.test_database
    Database = (MongoClient('localhost', 27017), u'test')
    db = client['test']
    collection = db['retrieval']
    posts = db.posts
    db.test.insert_many(tweetList)
    print(db.test.count())
    #curs = db.test.find({})
    #print curs
    #for record in curs:
    #    print record

if __name__ == '__main__':
    import os
    import sys
    print('getting token...')
    token = get_token(
        '3187020155-vjTxFyiyS2I13fDb1pLn2sFBjuLu0DIfvsyTAHi','nWoujP4PSOlIN1TrIpQ09lKXIDAyiJToddEcWuh6UyEnE'
        #os.environ['TWITTER_APP_ID'],
        #os.environ['TWITTER_APP_SECRET']
    )
    prog_lang_list = get_prog_lang('~Desktop\tweets\programmingLanguages.md')
    for prog_lang in prog_lang_list:
        print('getting' + prog_lang + 'tweets...')
        tweets = search_tweets(prog_lang, token)
        custom_tweets = resize_cicle(tweets)
        bson_tweets = to_bson(tweets)
        print('saving'+prog_lang+'tweets...')
        save_in_db(bson_tweets)
        print(prog_lang + 'ok')
    print('done')
