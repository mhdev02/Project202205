const request = require("request");
const cheerio = require("cheerio");
const fs = require("fs");
const Redis = require('ioredis');

const redis1 = new Redis();
const redis = redis1.duplicate();

let lists = ["\n", "\n\n", ":", "\t", "\t\t", "Copyright", "ⓒ", "All", "rights", "reserved.", "등록일자", "발행인", "편집인", "전체", "지면",
  "로그인", "5월", "보기", "보도자료", "온라인광고", "사이트맵", "디지털초판", "개인정보취급방침"];

let crawl = async function (req, res) {
  
  const url1 = req.body.url1;
  const url2 = req.body.url2;
  let contents1;
  let contents2;

  let html1 = await doRequest(url1);
  let $ = cheerio.load(html1);
  contents1 = $('body').text();

  let html2 = await doRequest(url2);
  let $1 = cheerio.load(html2);
  contents2 = $1('body').text();

  for (let i = 0; i < lists.length; i++) {
    var regex = new RegExp(lists[i], "gi")
    contents1 = contents1.replace(regex, ' ');
    contents2 = contents2.replace(regex, ' ');
  }

  await redis.publish('work', JSON.stringify(contents1 + "+++" + contents2));

  await redis.hgetall('keywords', (err, values) => {
    let timestamp = new Date();
    let filename = timestamp.getMonth() + '' + timestamp.getDate() + '' + timestamp.getHours() + ''
      + timestamp.getMinutes() + '' + timestamp.getSeconds();
    writeFile(`./data/${filename}.txt`, values.work);
  })

  res.status(201).send('ok');
}

let readFile = async function (req, res) {
  let timestamp = req.params.timestamp;
  let filename = `./data/${timestamp}.txt`;

  if (fs.existsSync(filename)) {
    return fs.readFile(filename, 'utf8', (err, data) => {
      if (err) {
        res.status(404).send('not found');
      } else {
        res.status(200).send({ data });
      }
    })
  }
  res.status(404).send('not found');
}

async function writeFile(filename, body) {
  fs.writeFile(filename, body, (err) => {
    if (err) {
      console.log('error occured while writing')
    } else {
      console.log('saved!');

    }
  });
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

module.exports = {
  crawl,
  readFile
}