const express = require("express");
const fs = require("fs");
const controller = require("./crawlController");
const router = express.Router();

router.post("/crawl", controller.crawl);

module.exports = router;