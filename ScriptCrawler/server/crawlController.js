const request = require("request");
const cheerio = require("cheerio");
const Redis = require('ioredis');

const redis1 = new Redis(process.env.REDIS_PORT, process.env.REDIS_IP);
const redis2 = redis1.duplicate();

let fiteringList = ["\n", "\n\n", ":", "\t", "\t\t", "Copyright", "ⓒ", "All", "rights", "reserved.", "등록일자", "발행인", "편집인", "전체", "지면",
  "로그인", "보기", "보도자료", "온라인광고", "사이트맵", "디지털초판", "개인정보취급방침", ",", "if", "=", "==", "undefined", "{", "}", "Date()", "expires",
  "var", "\\\"https", "\\*", "width", "height", "}else{", "function", "$.ajax", ">", "type", "error", "document.cookie", ';', "\\+", "\\)", 
  "\\(", "false", "“", "else", "!", "''", "\\$", "\\/", "\/", "\\ ", ".attr", ".text", ".eq", ".addClass", ".on", "\\?", ".removeClass", "li", "none", "window", 
  "display", ".ready", ".length", "document.location.href", "jQuery", "parseInt", ".toGMTString", ".get", "'", ">", "<"];

let callCount = 0;

setInterval(() => initializeCallCount(), 15000);

let crawl = async function (req, res) {

  callCount++;
  if (callCount >= 21) {
    return res.status(429).send("You can send only 20 requests every 15 seconds");
  };

  try {
    const url1 = req.body.url1;
    const url2 = req.body.url2;

    if (!url1 || !url2) {
      return res.status(400).send("You need to put two valid URLs - url1, url2");
    }

    let contents1;
    let contents2;

    let html1 = await doRequest(url1);
    let $ = cheerio.load(html1);
    contents1 = $('body').text();

    let html2 = await doRequest(url2);
    let $1 = cheerio.load(html2);
    contents2 = $1('body').text();

    for (let i = 0; i < fiteringList.length; i++) {
      var regex = new RegExp(fiteringList[i], "gi")
      contents1 = contents1.replace(regex, ' ');
      contents2 = contents2.replace(regex, ' ');
    }

    await redis1.publish('work', JSON.stringify(contents1 + "+++" + contents2));

    await redis2.hgetall('keywords', (err, values) => {
      res.status(201).send(values.work);
    })
  } catch (e) {
    console.log(e);
    res.status(500).send("An error occured. The server requires to check a problem.")
  }
}

function doRequest(url) {
  return new Promise(function (resolve, reject) {
    request(url, function (error, response, body) {
      if (!error && response.statusCode == 200) {
        resolve(body);
      } else {
        reject(error);
      }
    });
  });
}

function initializeCallCount() {
  callCount = 0;
}

module.exports = {
  crawl
}