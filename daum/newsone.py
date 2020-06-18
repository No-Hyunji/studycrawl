# 다음에서 뉴스 한건의 기사와 내용을 수집


import requests
from bs4 import BeautifulSoup

url = 'https://entertain.v.daum.net/v/20200616074802221'
resp = requests.get(url)

# resp에 staus_code가 200이면 성공, 나머지는 실해
if resp.status_code == 200:
    print('Success')
else:
    print('Wrong URL')

# requests는 소스코드만 전부 가져오는거고 거기서 원하는 내용은 추출불가!!
# 원하는 내용만 추출하려면 beauti~~를 사용해야 함
# beautifulsoup에 input으로 resp의 값(웹사이트의 소스코드 전체)를 전달
# soup에 웹사이트의 소스코드 전체가 저장
# soup.select()를 이용하여 원하는 정보만 추출
soup = BeautifulSoup(resp.text, 'html.parser')
title = soup.select('h3.tit_view') # (태그+선택자)
# select 복수로 받아들여 리스트로 받아들임
# soup.select()는 무조건 return을 list type으로 반환
# [val1, val2, val3, ... ]
# ex) contents[1]

print(title[0].text)
# .text하면 에러남. 리스튼데 어떻게 텍스트로 바꾸냐 [0]붙여주면 됨
# 본문 가져와 보세요!
contents = soup.select('div#harmonyContainer p')
print('▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒')

text = ''
for i in contents:
    text += i.text


print(text)
# list 1건씩 꺼내서 더하면~~~~~~~~~~~~~






