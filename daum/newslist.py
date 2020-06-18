# 다음 뉴스 한 페이지에서 뉴스기사와 내용을 수집(15건의 기사)


import requests

from bs4 import BeautifulSoup

url = 'https://news.daum.net/breakingnews/digital'

resp = requests.get(url)
soup = BeautifulSoup(resp.text, 'html.parser')

url_list = soup.select('ul.list_allnews a.link_txt')
print(url_list)

for i in url_list:
    url = i['href']
    resp = requests.get(url)
    soup = BeautifulSoup(resp.text, 'html.parser')
    title = soup.select('h3.tit_view')
    contents = soup.select('div#harmonyContainer p')

    text = ''
    for i in contents:
        text += i.text
    print('=============================================================')
    print(title[0].text)
    print('▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒')
    print(text)
