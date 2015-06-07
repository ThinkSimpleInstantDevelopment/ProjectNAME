# python standard library
import base64
import json
import logging

# 3rd party
import requests
from logentries import LogentriesHandler


def logger():
    '''
    set the logentries logging service
    '''
    log = logging.getLogger('getTweets')
    log.setLevel(logging.INFO)
    log.addHandler(LogentriesHandler('21e95b59-8391-4f27-bf6f-d81894860920'))
    return log

'''
get the token to make requests to twitter from client id and secret
return the access token needed to make requests
'''
def get_token(client_id, client_secret):
    #client_id = TWITTER_CLIENT_ID
    #client_id = 'Kbvd9oQNAF6cr9XULzoRu5b1n'
    #client_secret = TWITTER_CLIENT_SECRET
    #client_secret = 'nWoujP4PSOlIN1TrIpQ09lKXIDAyiJToddEcWuh6UyEnE'
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
    '''
    Return a list of tweets (dictionaries)
    '''
    url = 'https://api.twitter.com/1.1/search/tweets.json'
    resp = requests.get(
        url,
        headers={'Authorization': 'Bearer {}'.format(token)},
        params={'q': what, 'count':'100'}
    )
    resp.raise_for_status()
    data = resp.json()
    return data['statuses']

'''
save raw tweets on json files
'''
def save_tweets(tweets):
    count = 0
    for tweet in tweets:
        count += 1
        f_destination = open('rawTweet'+str(count), 'w+')
        json.dump(cleanedTweet, f_destination)
        f_destination.close()

'''
save customized tweets on json files
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
create a list of customized tweets from a list of raw tweets
'''
def resize_cicle(tweets):
    data_list = []
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
        data_list.append(data)
    return data_list

'''
get the programming languages to search from a programmingLanguages.md in the same directory
'''
def get_prog_lang():
    file = './programmingLanguages.md'
    reader = open(file, 'r').read()
    langList = reader.split(',')
    return langList

'''
save a list of tweet in mongoDB
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

if __name__ == '__main__':
    import os
    import sys
    log = logger()
    #print('getting token...')
    log.info('getting token...')
    token = get_token('Kbvd9oQNAF6cr9XULzoRu5b1n','dNJOsK3gcOiJJmrVdA1jKAt2usjzauQwXpiQpPv9CJCpUfo67W'
        #os.environ['TWITTER_APP_ID'],
        #os.environ['TWITTER_APP_SECRET']
    )
    prog_lang_list = get_prog_lang()
    tweetTotal = 0
    for prog_lang in prog_lang_list:
        #print('getting ' + prog_lang + ' tweets...')
        log.info('getting ' + prog_lang + ' tweets...')
        tweets = search_tweets(prog_lang, token)
        custom_tweets = resize_cicle(tweets)
        #print(' saving'+prog_lang+' tweets...')
        log.info('saving '+prog_lang+' tweets...')
<<<<<<< HEAD
    tweetNum = len(custom_tweets)
    tweetTotal += tweetNum
        save_in_db(custom_tweets)
        print(prog_lang + ' ok')
=======
        tweetNum = len(custom_tweets)
        tweetTotal += tweetNum
    save_in_db(custom_tweets)
    print(prog_lang + ' ok')
>>>>>>> retrieval
    log.info(''+ str(tweetNum) +' '+ prog_lang +  ' tweets added')
    print('done')
    log.info('done, totally added '+ str(tweetTotal) +' tweets')
