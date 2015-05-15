# python standard library
import base64
import json

# 3rd party
import requests

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
        destination = 'tweet'+str(count)
        f_destination = open(destination, 'w+')
        json.dump(tweet, f_destination)
        f_destination.close()

if __name__ == '__main__':
    import os
    import sys
    print('getting token...')
    token = get_token(
        '3187020155-vjTxFyiyS2I13fDb1pLn2sFBjuLu0DIfvsyTAHi','nWoujP4PSOlIN1TrIpQ09lKXIDAyiJToddEcWuh6UyEnE'
        #os.environ['TWITTER_APP_ID'],
        #os.environ['TWITTER_APP_SECRET']
    )
    print('getting tweets...')
    tweets = search_tweets(sys.argv[1], token)
    print('saving tweets...')
    save_tweets(tweets)
    print('OK!')
