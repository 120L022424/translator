# -*- coding: UTF-8 -*-
import http.client, urllib, json
import requests
import base64


def text2img(prompt):
    negative_prompt = "lowers, bad anatomy, bad hands, text, error, missing fingers, extra digit, fewer digits, cropped, wort quality, low quality, normal quality, jpeg artifacts, signature, watermark, username, blurry, bad feet"

    url = "https://51e35fcfb9915b66.gradio.app/api/predict/"

    payload = {
        "session_hash": "g68z8lzf5u",
        "data": [
            f"{prompt}",
            f"{negative_prompt}",
            "None",
            "None",
            20,
            "Euler a",
            False,
            False,
            1,
            1,
            7,
            -1,
            -1,
            0,
            0,
            0,
            False,
            512,
            512,
            False,
            False,
            0.7,
            "None",
            False,
            False,
            None,
            "",
            "Seed",
            "",
            "Steps",
            "",
            True,
            False
        ],
        "fn_index": 12
    }

    response = requests.post(url=url, json=payload)

    r = response.json()
    img_base64 = r["data"][0][0][22:]
    img_data = base64.b64decode(img_base64)
    with open(f'{prompt}.png', 'wb') as f:
        f.write(img_data)

    print(response.status_code)


def img2text(imgpath):
    tag = ''
    png = open(imgpath, 'rb')
    res = png.read()
    b64 = base64.b64encode(res)
    png.close()
    # 图片转base64

    conn = http.client.HTTPSConnection('apis.tianapi.com')  # 接口域名
    params = urllib.parse.urlencode({'key': 'f67fbc581754e0b21d031b5200130614', 'img': b64})
    headers = {'Content-type': 'application/x-www-form-urlencoded'}
    conn.request('POST', '/imgscan/index', params, headers)
    tianapi = conn.getresponse()
    result = tianapi.read()
    data = result.decode('utf-8')
    dict_data = json.loads(data)
    print(dict_data.get('code'))
    # 调用图像识别api

    for i in dict_data.get('result').get('list'):
        tag = tag + i.get('tag') + ','
    tag = tag.strip(',')
    print(tag)
