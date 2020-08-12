import requests
import bs4
import re
import json
from concurrent.futures import ThreadPoolExecutor,wait,ALL_COMPLETED
import time

def get_detail_pages():
    resp = requests.get("http://www.yymp3.com").content.decode("utf8")
    soup = bs4.BeautifulSoup(resp, "lxml")
    form = soup.select("#main")[0]
    a = form.select("a[target=_yymp3]")
    result = []
    for i in a:
        result.append(
            (len(a), "http://www.yymp3.com/"+i["href"], i.text.strip()),
        )
    return result


def download(url:str, name):
    resp = requests.get(url).content.decode("utf8")
    info = re.search("\\$song_data\\[0\\]=(.+)", resp).group(1)
    print(info)
    info = re.search(".+\|.+\|.+\|(.+)\|(.+)\|.+\|\|.+", info)
    singer, mp3_url = info.group(1), info.group(2)
    mp3_url = "http://ting6.yymp3.net:82/" + mp3_url.replace(".wma", ".mp3")
    mp3 = requests.get(mp3_url).content
    with open("mp3/"+name+".mp3", 'wb') as f:
        f.write(mp3)
    return {
        'name': name,
        'singer': singer,
        'url': mp3_url
    }


def generate_list():
    # 生成任务列表
    r = get_detail_pages()
    with open("list.json", 'wt') as f:
        f.write(json.dumps(r))

def download_mp3():
    # 读取任务列表，进行下载歌曲文件
    r = []
    with open("list.json") as f:
        r = json.load(f)

    infos = []
    with open("infos.json") as f:
        infos = json.load(f)


    def task(r, i, infos):
        info = download(*i)
        r.remove(i)
        infos.append(info)
        with open("list.json", 'wt') as f:
            f.write(json.dumps(r))
        with open("infos.json", 'wt') as f:
            f.write(json.dumps(infos))
        print(" " * 30, end='\r')
        print(info)
        print(f"剩余{len(r)}", end='')

    pool = ThreadPoolExecutor(max_workers=20)
    tasks = [pool.submit(task, r, i, infos) for i in r] 

    wait(tasks, return_when=ALL_COMPLETED)

def download_lyrics():
    # 读取任务列表，进行下载歌曲文件
    r = []
    with open("list.json") as f:
        r = json.load(f)
    
    def task(r, i):
        url = i[1]
        name = i[2]
        sid = url[url.rindex("/")+1:url.rindex(".")]
        mulu = str(int(sid) / 10000)
        mulu = mulu[:mulu.index(".")]
        lyrics_url = "http://www.yymp3.com/Songword/"+mulu+"/"+sid+".htm"
        print(lyrics_url)
        text = requests.get(lyrics_url).content.decode("utf8")
        soup = bs4.BeautifulSoup(text, "lxml")
        lrc = soup.select_one("#lrc")
        with open("lyrics/"+name+".txt", "wt", encoding="utf-8") as f:
            f.write(re.sub("(\[\d\d:\d\d.\d\d\])", "\n\\1", lrc.text).strip(" \n"))

        r.remove(i)
        with open("list.json", 'wt') as f:
            f.write(json.dumps(r))
        print(" " * 30, end='\r')
        print(name)
        print(f"剩余{len(r)}", end='')

    pool = ThreadPoolExecutor(max_workers=20)
    tasks = [pool.submit(task, r, i) for i in r] 
    # tasks = [pool.submit(task, r, r[0])]

    wait(tasks, return_when=ALL_COMPLETED)
if __name__ == "__main__":
    option = input("1.生成任务列表\n2.下载MP3\n3.下载歌词\n")
    if option == "1":
        generate_list()
    elif option == "2":
        download_mp3()
    elif option == "3":
        download_lyrics()