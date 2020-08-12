import json


infos = []
with open("infos.json", 'rt') as f:
    infos = json.load(f)

sqls = []
for info in infos:
    name = info["name"]
    singer = info["singer"]
    url = f"http://120.79.182.149:10001/mp3/{name}.mp3"
    imageUrl = ""
    lyric = ""
    with open(f"lyrics/{name}.txt", encoding="utf8") as f:
        lyric = f.read()
    lyric = lyric.replace("\n", "<br>").replace("'", r"\'")
    sql = f"insert into song values(null, '{url}', '{name}', '{imageUrl}', '{lyric}', '{singer}', 0, 0);"
    sqls.append(sql)

with open("insert.sql", "wt") as f:
    for i in sqls:
        f.write(i+"\n")