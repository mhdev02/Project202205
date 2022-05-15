const Redis = require('ioredis');
const { intersection } = require("./intersection");

const redis = new Redis();
const duplicatedRedis = redis.duplicate();

  duplicatedRedis.subscribe('work', (err, message) => {
    if (err) console.error(err.message);  

  });  

  duplicatedRedis.on("message", (channel, msg) => {
      msg = msg.split("+++");
      redis.hset('keywords', channel, JSON.stringify(intersection(msg[0].split(" "), msg[1].split(" ")))); // hset key field value
  })
